package com.ww.lp.lputils;

import android.net.Uri;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import java.util.Map;

/**
 * Created by LinkedME06 on 14/01/2017.
 */

public class UriUtils {
    /**
     * 在http链接后面追加参数
     *
     * @param httpString      http链接字符串
     * @param appendParamsMap 需要追加的参数
     * @return 追加完后的http字符串
     */
    public static String appendParams(String httpString, Map<String, String> appendParamsMap) {
        try {
            httpString = StringUtils.removeAllBlankCharacter(httpString);
            Uri httpUri = Uri.parse(httpString);
            if (appendParamsMap != null) {
                String appendParams = StringUtils.convertArrayMapToString((ArrayMap<String, String>) appendParamsMap, "&");

                if (!TextUtils.isEmpty(httpUri.getEncodedQuery())) {
                    String encodeQuery = httpUri.getEncodedQuery() + "&" + appendParams;
                    httpString = httpString.replaceFirst(httpUri.getEncodedQuery(), encodeQuery);
                } else {
                    if (TextUtils.isEmpty(httpUri.getEncodedPath())) {
                        if (httpString.contains(httpUri.getEncodedAuthority() + "?")) {
                            String encodeAuthority = httpUri.getEncodedAuthority() + "?" + appendParams;
                            httpString = httpString.replaceFirst(httpUri.getEncodedAuthority() + "\\?", encodeAuthority);
                        } else {
                            String encodeAuthority = httpUri.getEncodedAuthority() + "?" + appendParams;
                            httpString = httpString.replaceFirst(httpUri.getEncodedAuthority(), encodeAuthority);
                        }
                    } else {
                        if (httpString.contains(httpUri.getEncodedPath() + "?")) {
                            String encodePath = httpUri.getEncodedPath() + "?" + appendParams;
                            httpString = httpString.replaceFirst(httpUri.getEncodedPath() + "\\?", encodePath);
                        } else {
                            String encodePath = httpUri.getEncodedPath() + "?" + appendParams;
                            httpString = httpString.replaceFirst(httpUri.getEncodedPath(), encodePath);
                        }
                    }
                }
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return httpString;
    }
}
