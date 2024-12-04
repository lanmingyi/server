package com.bright.system.util.compare;

/**
 * @MethodName
 * @Description TODO 常用比较方法封装
 * @Param null
 * @Return
 * @Author lmy
 * @Date 2021-6-2 15:54
 */
public class compareUtils {


    /**
     * @MethodName compareVersion
     * @Description TODO 版本号比较
     * @Param version1       1.1.0
     * @Param version2       1.2.0
     * @Return int        //第一个参数大返回正数，第二个参数大返回负数，一样返回0；
     * @Author 明成
     * @Date 2021-6-2 15:54
     */
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        int index = 0;
        //获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        //循环判断每位的大小
        while (index < minLen && (diff = Integer.parseInt(version1Array[index]) - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            //如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

}
