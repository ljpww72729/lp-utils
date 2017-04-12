package com.ww.lp.lputils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 应用帮助类
 *
 * Created by LinkedME06 on 12/04/2017.
 */

public class AppUtils {
    /**
     * 通过包名判断本机是否已经安装了APP
     *
     * @param context Context
     * @param pkgName 包名
     * @return true：已安装； false：未安装
     */
    public static boolean isAppInstalledByPkg(Context context, String pkgName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }
}
