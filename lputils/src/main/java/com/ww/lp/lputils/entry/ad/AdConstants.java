package com.ww.lp.lputils.entry.ad;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by LinkedME06 on 27/07/2017.
 */

public class AdConstants {

    /**
     * 广告类型
     */
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            AD_TYPE_BANNER,
            AD_TYPE_FLOATING
    })
    public @interface AD_TYPE {
    }

    public static final String AD_TYPE_BANNER = "banner";
    public static final String AD_TYPE_FLOATING = "floating";

    /**
     * 广告状态
     */
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            AD_STATUS_SHOW,
            AD_STATUS_CLICK,
            AD_STATUS_OPEN,
            AD_STATUS_NOT_OPEN,
            AD_STATUS_DOWNLOAD
    })
    public @interface AD_STATUS {
    }

    /**
     * 展示广告
     */
    public static final String AD_STATUS_SHOW = "11";
    /**
     * 点击广告
     */
    public static final String AD_STATUS_CLICK = "12";
    /**
     * 打开应用
     */
    public static final String AD_STATUS_OPEN = "13";
    /**
     * 未打开应用
     */
    public static final String AD_STATUS_NOT_OPEN = "14";
    /**
     * 下载应用
     */
    public static final String AD_STATUS_DOWNLOAD = "15";
}
