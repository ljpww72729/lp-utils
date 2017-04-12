package com.ww.lp.lputils;

import android.support.v4.util.ArrayMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Map相关工具类
 * Created by LinkedME06 on 01/04/2017.
 */

public class MapUtils {

    /**
     * 将ArrayMap对象转化为String，以separator分隔符分隔，键值对以=连接 形如：a=b&c=d
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

    /**
     * 将map对象转化为String，以separator分隔符分隔，键值对以=连接 形如：a=b&c=d
     *
     * @param convertMap 被转化的map对象
     * @param separator  分隔符
     */
    public static String convertMapToString(Map<String, String> convertMap, String separator) {
        String convertString = "";
        if (convertMap != null && convertMap.size() > 0) {
            Set<Map.Entry<String, String>> mapSet = convertMap.entrySet();
            Iterator<Map.Entry<String, String>> iterator = mapSet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> mapEntry = iterator.next();
                convertString += mapEntry.getKey() + "=" + mapEntry.getValue() + separator;
            }
            if (convertString.length() > 0) {
                convertString = convertString.substring(0, convertString.length() - 1);
            }
        }
        return convertString;
    }
}
