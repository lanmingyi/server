package com.brightcomplete.common.util.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 文件上传字符串过滤特殊字符
 * @author lmy
 */
public class StrAttackFilter {

    public static String filter(String str) throws PatternSyntaxException {
        // 清除掉所有特殊字符
        String regEx = "[`_《》~!@#$%^&*()+=|{}':;',\\[\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

//    public static void main(String[] args) {
//        String filter = filter("@#brightcomplete/《》【bo】￥%……&*（o）)))！@t<>,.,/?'\'~~`");
//        System.out.println(filter);
//    }
}
