package com.bright.common.util;
import com.bright.common.constant.CommonConstant;

public class ConvertUtil {

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if (CommonConstant.STRING_NULL.equals(object)) {
            return (true);
        }
        return (false);
    }

    public static String getString(String s) {
        return (getString(s, ""));
    }

    /**
     * 转义成Unicode编码
     * @param s
     * @return
     */
	/*public static String escapeJava(Object s) {
		return StringEscapeUtils.escapeJava(getString(s));
	}*/

    public static String getString(Object object) {
        if (isEmpty(object)) {
            return "";
        }
        return (object.toString().trim());
    }

    public static String getString(int i) {
        return (String.valueOf(i));
    }

    public static String getString(float i) {
        return (String.valueOf(i));
    }

    public static String getString(String s, String defval) {
        if (isEmpty(s)) {
            return (defval);
        }
        return (s.trim());
    }

    public static String getString(Object s, String defval) {
        if (isEmpty(s)) {
            return (defval);
        }
        return (s.toString().trim());
    }

    public static long stringToLong(String str) {
        Long test = new Long(0);
        try {
            test = Long.valueOf(str);
        } catch (Exception e) {
        }
        return test.longValue();
    }
}
