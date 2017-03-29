package com.ww.lp.lputils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

/**
 * 权限帮助类
 *
 * Created by lipeng on 29/03/2017.
 */

public class PermissionUtils {

    /**
     * 检查应用是否有某个权限
     *
     * @param context    {@link Context}
     * @param permission {@link java.security.Permission}
     * @return true:已分配该权限 false:无该权限
     */
    public static boolean selfPermissionGranted(Context context, String permission) {
        // Android 6.0 以前，全部默认授权
        boolean result = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ApplicationInfoUtils.getTargetSDKVersion(context) >= Build.VERSION_CODES.M) {
                // targetSdkVersion >= 23, 使用Context#checkSelfPermission
                result = ContextCompat.checkSelfPermission(context, permission)
                        == PackageManager.PERMISSION_GRANTED;
            } else {
                // targetSdkVersion < 23, 需要使用 PermissionChecker
                result = PermissionChecker.checkSelfPermission(context, permission)
                        == PermissionChecker.PERMISSION_GRANTED;
            }
        }
        return result;
    }
}
