package com.ww.lp.lputils;

import android.support.v4.util.ArrayMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Json帮助类
 * Created by LinkedME06 on 16/01/2017.
 */

public class JsonUtils {

    /**
     * 将JsonObject字符串转化为Map对象
     *
     * @param jsonString jsonString
     * @return ArrayMap对象
     */
    public static Map<String, String> convertJsonStrToMap(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        return convertJsonToMap(jsonObject);
    }

    /**
     * 将JsonObjec对象转换为Map对象
     *
     * @param json JsonObject
     * @return ArrayMap对象
     */
    public static Map<String, String> convertJsonToMap(JSONObject json) throws JSONException {
        Map<String, String> retMap = new ArrayMap<>();
        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    private static Map<String, String> toMap(JSONObject object) throws JSONException {
        Map<String, String> map = new ArrayMap<>();
        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            String value = (String) object.get(key);
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                Object value = array.get(i);
                if (value instanceof JSONArray) {
                    value = toList((JSONArray) value);
                } else if (value instanceof JSONObject) {
                    value = toMap((JSONObject) value);
                }
                list.add(value);
            }
        }
        return list;
    }

}
