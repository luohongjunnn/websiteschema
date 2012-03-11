/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package websiteschema.utils;

/**
 *
 * @author ray
 */
public class StringUtil {

    /**
     * Unicode spaces http://www.cs.tut.fi/~jkorpela/chars/spaces.html
     * @param str
     * @return
     */
    public static String trim(String str) {
        String ret = str.replaceAll("[\t\r\n \u00A0\u180E\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u200B\u202F\u205F\u3000\uFEFF]+$", "").
                replaceAll("^[\t\r\n \u00A0\u180E\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u200B\u202F\u205F\u3000\uFEFF]+", "");
        return ret;
    }

    public static boolean isNotEmpty(String str) {
        return null != str && !"".equals(str);
    }

    public static void main(String[] args) {
        System.out.println(trim("\r \n  　ab c\r\n \n\n"));
    }
}