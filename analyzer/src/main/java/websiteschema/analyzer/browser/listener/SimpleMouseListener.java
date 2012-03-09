/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteschema.analyzer.browser.listener;

import com.webrenderer.swing.dom.IElement;
import com.webrenderer.swing.event.MouseEvent;
import com.webrenderer.swing.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.apache.log4j.Logger;
import websiteschema.analyzer.browser.SimpleBrowser;
import websiteschema.analyzer.context.BrowserContext;
import websiteschema.element.CSSProperties;
import websiteschema.element.Rectangle;
import websiteschema.element.StyleSheet;
import websiteschema.element.factory.RectangleFactory;
import websiteschema.element.XPathAttributes;
import websiteschema.element.factory.StyleSheetFactory;
import websiteschema.element.factory.XPathAttrFactory;
import websiteschema.utils.ElementUtil;
import websiteschema.utils.UrlLinkUtil;
import websiteschema.vips.extraction.VipsBlockExtractor;

/**
 *
 * @author ray
 */
public class SimpleMouseListener implements MouseListener, ActionListener {

    XPathAttributes attr = new XPathAttributes();
    Logger l = Logger.getRootLogger();
    BrowserContext context;
    IElement lastClickedElement;
    String lastStyle = null;
    SimpleBrowser simpleBrowser = null;

    public SimpleMouseListener(BrowserContext context, SimpleBrowser simpleBrowser) {
        attr.setUsingClass(true);
        attr.setUsingId(true);
        attr.setSpecifyAttr("name");
        attr.setUsingPosition(true);
        this.context = context;
        this.simpleBrowser = simpleBrowser;
    }

    @Override
    public void onClick(MouseEvent me) {
        l.debug("mouse onclick");
        if (null != context.getBrowser().getDocument()) {
            IElement ele = me.getTargetElement();
            if (null != ele) {
                drawRectangleInPage(ele);
                //计算所选节点的两种XPATH
                Rectangle rect = new RectangleFactory().create(ele);
                String xpath = XPathAttrFactory.getInstance().create(ele);
                attr = simpleBrowser.getXPathAttr();
                String xpath2 = XPathAttrFactory.getInstance().create(ele, attr);
                simpleBrowser.displaySelectedElement(xpath, xpath2); //将XPATH显示在UI上

                String text = ele.getInnerHTML();
                simpleBrowser.displayNodeValue(text);//将所选节点的html标签显示在UI上

                l.debug("Elememnt Type: " + ele.getTagName());
                if ("A".equals(ele.getTagName())) {
                    //如果所选节点是A，则将URL显示在UI上
                    String href = ele.getAttribute("href", 0);
                    URL url = UrlLinkUtil.getInstance().getURL(context.getBrowser().getURL(), href);
                    if (null != url) {
                        simpleBrowser.displaySelectedAnchor(url.toString());
                    }
                }
                context.getConsole().log("Tag Name: " + ele.getTagName() + " -- Node Type: " + ElementUtil.getInstance().getNodeType(ele) + " -- XPath: " + xpath);
                l.debug(rect);
                String referrer1 = context.getBrowser().getDocument().getReferrer();
                StyleSheet styleSheets = context.getStyleSheet(referrer1);
                CSSProperties css = new StyleSheetFactory().createCSSProperties(styleSheets, ele);
                l.debug("CSS Properties: " + css);

//            String text = ElementUtil.getInstance().getText(ele);
//            context.getConsole().log(text);

                VipsBlockExtractor be = new VipsBlockExtractor();
                be.setContext(context);
                String referrer2 = context.getBrowser().getDocument().getReferrer();
                be.setReferrer(referrer2);
                Rectangle rectBody = new RectangleFactory().create(context.getBrowser().getDocument().getBody());
                be.setPageSize(rectBody.getHeight() * rectBody.getWidth());
                be.analysisElement(ele);
            }
        }
    }

    private void drawRectangleInPage(IElement ele) {
        if (null != lastClickedElement) {
            if (null != lastStyle && !"".equals(lastStyle)) {
                lastClickedElement.setAttribute("style", lastStyle, 0);
            } else {
                lastClickedElement.removeAttribute("style", 0);
            }
        }
        lastStyle = ele.getAttribute("style", 0);
        String styleAdded = "background-color: LightSteelBlue;";
        if (null != lastStyle && !"".equals(lastStyle)) {
            ele.setAttribute("style", lastStyle + styleAdded, 0);
        } else {
            ele.setAttribute("style", styleAdded, 0);
        }
        lastClickedElement = ele;
    }

    @Override
    public void onDoubleClick(MouseEvent me) {
//        l.debug("mouse ondoubleclick");
    }

    @Override
    public void onMouseDown(MouseEvent me) {
//        l.debug("mouse onMouseDown " + me.isMetaDown());
//        if (me.isMetaDown()) {
//            JPopupMenu popup = createPopupMenu();
//            popup.show(me.getComponent(), me.getX(), me.getY());
//        }
    }

    @Override
    public void onMouseUp(MouseEvent me) {
//        l.debug("mouse onMouseUp");
    }

    public JPopupMenu createPopupMenu() {
        JMenuItem menuItem;

        //Create the popup menu.
        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("Popup item 1");
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Popup item 2");
        menuItem.addActionListener(this);
        popup.add(menuItem);

        return popup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        System.out.println("Selected: " + source.getText());
    }
}
