package com.ww.lp.lputils;

import android.support.v4.util.ArrayMap;

import java.util.Map;

/**
 * 数据源数据
 * Created by LinkedME06 on 01/04/2017.
 */

public class DataSource {

    /**
     * 获取ArrayMap对象数据
     *
     * @return Map
     */
    public static Map getTestArrayMap() {
        Map<String, String> arrayMap = new ArrayMap<>();
        arrayMap.put("k1", "v1");
        arrayMap.put("k2", "v2");
        arrayMap.put("k3", "v3");
        return arrayMap;
    }
}
