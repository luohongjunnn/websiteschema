/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteschema.cluster.analyzer.fields;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import websiteschema.cluster.analyzer.AnalyzerUtil;
import websiteschema.cluster.analyzer.BasicAnalysisResult;
import websiteschema.cluster.analyzer.IFieldAnalyzer;
import websiteschema.element.DocumentUtil;
import websiteschema.model.domain.cluster.Cluster;
import websiteschema.model.domain.cluster.FeatureInfo;
import websiteschema.model.domain.cluster.FeatureStatInfo;
import websiteschema.model.domain.cluster.Sample;
import websiteschema.utils.EditDistance;

/**
 *
 * @author ray
 */
public class TitleAnalyzer implements IFieldAnalyzer {

    String titleXPath = "";
    String titlePrefixString = "";
    String titleSuffixString = "";

    public String getFieldName() {
        return "TITLE";
    }

    public String[] getProperClusterType() {
        return new String[]{"DOCUMENT"};
    }

    public void init(Map<String, String> params) {
        titleXPath = params.containsKey("TitleXPath") ? params.get("TitleXPath") : "";
        titlePrefixString = params.containsKey("TitlePrefixString") ? params.get("TitlePrefixString") : "";
        titleSuffixString = params.containsKey("TitleSuffixString") ? params.get("TitleSuffixString") : "";
    }

    public Map<String, String> analyze(Cluster cluster, FeatureStatInfo statInfo, BasicAnalysisResult analysisResult, List<Sample> samples) {
        Set<String> validNodes = analysisResult.getValidNodes();
        AnalyzerUtil util = AnalyzerUtil.getInstance();
        Map<String, String> titleText = util.getText("html/head/title", samples);
        //将每篇文章的标题进行一次过滤
        for (String key : titleText.keySet()) {
            String title = titleText.get(key);
            title = trimTitle(title, analysisResult);
            titleText.put(key, title);
        }
        double sim = 0.4;
        String xpath = "";
        EditDistance ed = new EditDistance();
        for (String node : validNodes) {
            FeatureInfo info = statInfo.getFeatureInfo(node);
            //如果标签的出现频率大于2，则不可能是标题。
            //如果标签的包含文本的长度，平均大于200，则不可能是标题。
            if (info.getFrequence() <= 2 && info.getWeight() < 200) {
                Map<String, String> nodeText = util.getText(node, samples);
                double total = 0.0;
                double count = 0;
                for (String sample : nodeText.keySet()) {
                    String sampleText = nodeText.get(sample);
                    String title = titleText.get(sample);
                    if (null != sampleText && null != title && sampleText.length() < 200) {
                        total += ed.caculateSimilarityBetweenStrings(sampleText, title);
                        count += 1;
                    }
                }
                double arvgSim = count > 0 ? total / count : 0.000001;
                if (arvgSim > sim) {
                    sim = arvgSim;
                    xpath = node;
                }
            }
        }

        if (!"".equals(xpath)) {
            this.titleXPath = xpath;
        }

        return createResult();
    }

    private String trimTitle(String title, BasicAnalysisResult analysisResult) {
        Set<String> titlePrefix = analysisResult.getTitlePrefix();
        Set<String> titleSuffix = analysisResult.getTitleSuffix();

        for (String prefix : titlePrefix) {
            if (title.startsWith(prefix)) {
                title = title.substring(prefix.length());
                break;
            }
        }

        for (String suffix : titleSuffix) {
            if (title.endsWith(suffix)) {
                title = title.substring(0, title.length() - suffix.length());
                break;
            }
        }

        return title;
    }

    private Map<String, String> createResult() {
        Map<String, String> ret = new HashMap<String, String>();

        if (null != titleXPath && !"".equals(titleXPath)) {
            ret.put("TitleXPath", titleXPath);
        }

        if (null != titlePrefixString && !"".equals(titlePrefixString)) {
            if (null != titleSuffixString && !"".equals(titleSuffixString)) {
                ret.put("TitlePrefixString", titlePrefixString);
                ret.put("TitleSuffixString", titleSuffixString);
            }
        }
        return ret;
    }

    public Set<String> extract(Document doc) {
        if (!"".equals(titleXPath)) {
            return getTitle(titleXPath, doc);
        } else if (!"".equals(titlePrefixString) && !"".equals(titleSuffixString)) {
            return getTitle(titlePrefixString, titleSuffixString, doc);
        }

        return null;
    }

    private Set<String> getTitle(String xpath, Document doc) {
        Set<String> ret = new HashSet<String>();
        List<Node> nodes = DocumentUtil.getByXPath(doc, xpath);
        for (Node node : nodes) {
            String t = extractNodeText(node);
            if (null != t) {
                ret.add(t);
            }
        }
        return ret;
    }

    private String extractNodeText(Node node) {
        String ret = "";

        NodeList children = node.getChildNodes();
        for(int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if(child.getNodeType() == Node.TEXT_NODE) {
                ret += child.getNodeValue();
            }
        }

        return ret;
    }

    private Set<String> getTitle(String prefix, String suffix, Document doc) {
        Set<String> ret = new HashSet<String>();

        String text = DocumentUtil.getXMLString(doc);
        int start = text.indexOf(prefix);
        if (start >= 0) {
            int end = text.indexOf(suffix, start + prefix.length());
            String title = text.substring(start + prefix.length(), end);
            ret.add(title);
        }
        return ret;
    }
}