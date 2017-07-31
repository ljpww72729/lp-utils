package com.ww.lp.lputils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.ww.lp.lputils.entry.AppInfo;

import java.net.URISyntaxException;

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

    /**
     * 通过uri scheme获取对应的应用信息
     *
     * @param context    Context
     * @param uri_scheme Uri Scheme
     * @return AppInfo应用信息对象。null则无可处理该uri scheme的应用，反之存在
     */
    public static AppInfo getAppInfoByScheme(Context context, String uri_scheme) {
        AppInfo appInfo = null;
        Intent intent = null;
        try {
            intent = Intent.parseUri(uri_scheme, Intent.URI_INTENT_SCHEME);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
        ResolveInfo resolveInfo = context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo != null) {
            appInfo = new AppInfo();
            appInfo.setAppIconDrawable(resolveInfo.loadIcon(context.getPackageManager()));
            appInfo.setPackageName(resolveInfo.activityInfo.packageName);
            appInfo.setAppName((String) resolveInfo.loadLabel(context.getPackageManager()));
            //此处无法获取到icon的id，只能通过loadIcon获取icon的drawable
            appInfo.setAppIcon(resolveInfo.getIconResource());
            appInfo.setUriScheme(uri_scheme);
            appInfo.setInstalled(true);
        }
        return appInfo;
    }
}
