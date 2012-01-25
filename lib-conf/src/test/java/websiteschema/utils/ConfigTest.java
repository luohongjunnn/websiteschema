/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteschema.utils;

import java.util.HashMap;
import java.util.Map;
import websiteschema.conf.Configure;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ray
 */
public class ConfigTest {

    @Test
    public void test() {
        Configure conf = new Configure("configure-site.ini");
        assertEquals(conf.getProperty("AnalyzerTips"), "abc");
    }

    /**
     * 从预先设定的prop中寻找是否需要替换。
     * 如果在prop有：abc = xyz
     * 如果value = ${abc}
     * 则value会被替换成xyz
     */
    @Test
    public void testFilter() {
        Configure conf0 = new Configure("configure-site.ini");
        assertEquals(conf0.getProperty("FiltedField"), "${abc}");
        //增加过滤
        Map<String, String> prop = new HashMap<String, String>();
        prop.put("abc", "xyz");
        Configure conf = new Configure("configure-site.ini", prop);
        assertEquals(conf.getProperty("FiltedField"), "xyz");
        assertEquals(conf.getProperty("TestField"), "CLS = xyz");
    }

    @Test
    public void testMultiLine() {
        Configure conf = new Configure("configure-site.ini");
        Map map = conf.getMapProperty("URLCharset", "CharsetMap");
        System.out.println(conf.getProperty("URLCharset", "CharsetMap"));
        assert(map.containsKey("x-gbk"));
        assertEquals(map.get("x-gbk"), "gbk");

        String value = conf.getProperty("Test", "key");
        System.out.println(value);
        assertEquals(value, "''''");

        assertEquals(conf.getProperty("Test", "abc"), "efg");
    }
}
