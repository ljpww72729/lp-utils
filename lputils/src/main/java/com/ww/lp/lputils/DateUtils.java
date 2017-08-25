package com.ww.lp.lputils;

/**
 * Created by LinkedME06 on 25/08/2017.
 */

public class DateUtils {

    /**
     * 将时分秒转化成秒
     *
     * @param HHmmss 24小时格式，形如：18:24:32
     * @return 对应的秒数
     */
    public static long convertHHmmssToSeconds(String HHmmss) {
        long second = 0;
        String[] secondArray = HHmmss.split(":");
        for (int i = secondArray.length - 1; i > -1; i--) {
            second += Integer.valueOf(secondArray[i]) * Math.pow(60, (secondArray.length - 1 - i));
        }
        return second;
    }
}
