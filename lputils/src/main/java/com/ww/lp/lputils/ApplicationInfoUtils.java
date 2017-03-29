package com.ww.lp.lputils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 应用信息帮助类
 *
 * Created by lipeng on 29/03/2017.
 */

public class ApplicationInfoUtils {

    /**
     * 获取应用targetSDKVersion
     *
     * @param context {@link Context}
     * @return {@link Integer} success:targetSDKVersion fail:-1
     */
    public static int getTargetSDKVersion(Context context) {
        int targetSdkVersion = -1;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return targetSdkVersion;
    }

}
