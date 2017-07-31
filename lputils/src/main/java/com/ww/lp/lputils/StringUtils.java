package com.ww.lp.lputils;

import android.support.v4.util.ArrayMap;

/**
 * String 帮助类
 *
 * Created by LinkedME06 on 24/04/2017.
 */

public class StringUtils {

    /**
     * 将字符串首字母大写，字母的ascii编码前移从而转换为大写
     *
     * @param str String
     * @return 转换后的字符串
     */
    public static String capitalze(String str) {
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    /**
     * 可以替换大部分空白字符， 不限于空格\s 可以匹配空格、制表符、换页符等空白字符的其中任意一个
     *
     * @param originStr 需要被替换的空格
     * @return 去除空格后的字符串
     */
    public static String removeAllBlankCharacter(String originStr) {
        return originStr.replaceAll("\\s*", "");
    }

    /**
     * 将map对象转化为String，以separator分隔符分隔，键值对以=连接 形如：a=b&c=d
     * 主要用于请求对象的拼接
     *
     * @param convertMap 被转化的map对象
     * @param separator  分隔符
     */
    public static String convertArrayMapToString(ArrayMap<String, String> convertMap, String separator) {
        String convertString = "";
        if (convertMap != null && convertMap.size() > 0) {
            for (int i = 0; i < convertMap.size(); i++) {
                convertString += convertMap.keyAt(i) + "=" + convertMap.valueAt(i) + separator;
            }
            if (convertString.length() > 0) {
                convertString = convertString.substring(0, convertString.length() - 1);
            }
        }
        return convertString;
    }

}
