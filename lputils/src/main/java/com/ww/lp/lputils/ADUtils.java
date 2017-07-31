package com.ww.lp.lputils;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.Toast;

import com.ww.lp.lputils.android.AndroidUtils;
import com.ww.lp.lputils.android.service.LMDownloadService;
import com.ww.lp.lputils.android.view.LMH5Activity;
import com.ww.lp.lputils.constants.Defines;
import com.ww.lp.lputils.entry.ad.AdConstants;
import com.ww.lp.lputils.entry.ad.AdInfo;

import java.net.URISyntaxException;
import java.util.Map;

import static com.ww.lp.lputils.android.view.LMH5Activity.DOWNLOAD_FILE_NAME;
import static com.ww.lp.lputils.android.view.LMH5Activity.DOWNLOAD_FILE_URL;

/**
 * Created by LinkedME06 on 13/01/2017.
 */

public class ADUtils {
    /**
     * 根据包名判断是否安装了APP
     *
     * @param pkgName 包名
     * @return true:安装 false:未安装
     */
    public static boolean isAppInstalled(Context context, String pkgName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException ignore) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 点击广告
     *
     * @param mContext      Context
     * @param optimalAdInfo 广告
     * @return true:唤起了app false:其他情况
     */
    public static boolean clickAd(Context mContext, AdInfo optimalAdInfo) {
        //广告点击事件，唤起APP或在浏览器中打开
        LogUtils.d("广告被点击");
        ADUtils.recordStatus(mContext, optimalAdInfo, AdConstants.AD_STATUS_CLICK);
        if (optimalAdInfo != null) {
            if (TextUtils.equals(optimalAdInfo.getHas_web_view(), "1")) {
                //如果需要在WebView中显示
                if (!TextUtils.isEmpty(optimalAdInfo.getH5_url())) {
                    //在webview或浏览器中打开，因无法界定是否唤起了app，所以此处并不上报状态
                    Intent intent = new Intent(mContext, LMH5Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(LMH5Activity.AD_INFO, optimalAdInfo);
                    intent.putExtras(bundle);
                    if (AndroidUtils.resolveIntent(mContext, intent)) {
                        mContext.startActivity(intent);
                    } else {
                        //无LMH5Activity，则在浏览器中打开
                        openUriInBrowser(mContext, optimalAdInfo.getH5_url());
                    }
                    return false;
                } else {
                    //无h5_url则直接打开APP或下载APP
                    Toast.makeText(mContext, "页面君走丢了...", Toast.LENGTH_SHORT).show();
                    String adStatus = openDownloadApp(
                            mContext, optimalAdInfo.getPkg_name(), optimalAdInfo.getUri_scheme(),
                            optimalAdInfo.getH5_url(), optimalAdInfo.getApk_url(), optimalAdInfo.getDownload_way());
                    ADUtils.recordStatus(mContext, optimalAdInfo, adStatus);
                    if (TextUtils.equals(AdConstants.AD_STATUS_OPEN, adStatus)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else if (TextUtils.equals(optimalAdInfo.getHas_web_view(), "0")) {
                //如果不需要在WebView中显示则直接打开APP或者下载APP
                String adStatus = openDownloadApp(
                        mContext, optimalAdInfo.getPkg_name(), optimalAdInfo.getUri_scheme(),
                        optimalAdInfo.getH5_url(), optimalAdInfo.getApk_url(), optimalAdInfo.getDownload_way());
                ADUtils.recordStatus(mContext, optimalAdInfo, adStatus);
                if (TextUtils.equals(AdConstants.AD_STATUS_OPEN, adStatus)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            ADUtils.recordStatus(mContext, optimalAdInfo, AdConstants.AD_STATUS_NOT_OPEN);
        }
        return false;
    }

    /**
     * 如果不需要在WebView中显示这直接打开APP或者下载APP
     *
     * @param mContext   Context
     * @param pkg_name   包名
     * @param h5_url     clickAd  h5_url
     * @param uri_scheme uri_scheme
     */
    public static
    @AdConstants.AD_STATUS
    String openDownloadApp(
            Context mContext, String pkg_name,
            String uri_scheme, String h5_url,
            String apk_url, String download_way) {
        if (ADUtils.isAppInstalled(mContext, pkg_name)) {
            //APP安装直接打开APP
            if (openApp(mContext, pkg_name, uri_scheme)) {
                return AdConstants.AD_STATUS_OPEN;
            } else {
                //未打开app，则尝试下载app
                return downloadApp(mContext, pkg_name, h5_url, apk_url, download_way);
            }
        } else {
            //未安装APP，属于召回，在浏览器中打开h5_url或者打开应用市场下载
            return downloadApp(mContext, pkg_name, h5_url, apk_url, download_way);
        }
    }

    /**
     * 打开APP
     *
     * @param mContext   Context
     * @param pkg_name   包名
     * @param uri_scheme uri_scheme
     */
    public static boolean openApp(Context mContext, String pkg_name, String uri_scheme) {
        if (!TextUtils.isEmpty(uri_scheme)) {
            //通过uri scheme唤起APP,无法通过uri scheme唤起app时，
            //考虑到老版本应用可能不支持该uri scheme，可能也不会统计，所以不尝试通过包名唤起
            try {
                return openAppWithPNUri(mContext, pkg_name, uri_scheme);
            } catch (ActivityNotFoundException e) {
                LogUtils.d("未安装APP " + pkg_name);
                e.printStackTrace();
            }
        } else {
            //直接打开APP
            return openAppWithPN(mContext, pkg_name);
        }
        return false;
    }

    /**
     * 下载APP
     *
     * @param mContext     Context
     * @param pkg_name     包名
     * @param h5_url       h5_url
     * @param download_way 下载方式
     */
    public static
    @AdConstants.AD_STATUS
    String downloadApp(Context mContext, String pkg_name, String h5_url, String apk_url, String download_way) {
        if (TextUtils.isEmpty(h5_url)) {
            if (TextUtils.isEmpty(apk_url)) {
                //打开应用市场下载
                try {
                    openUriInMarket(mContext, pkg_name);
                } catch (ActivityNotFoundException e) {
                    LogUtils.d("未安装应用市场 ");
                    e.printStackTrace();
                }
                return AdConstants.AD_STATUS_NOT_OPEN;
            } else {
                openH5OrApkUrl(mContext, apk_url, download_way);
                return AdConstants.AD_STATUS_DOWNLOAD;
            }

        } else {
            if (TextUtils.isEmpty(apk_url)) {
                // 如果apk地址为空，则打开h5地址，同时通知服务器未唤起app
                openH5OrApkUrl(mContext, h5_url, download_way);
                return AdConstants.AD_STATUS_NOT_OPEN;
            } else {
                // 打开h5页面的同时下载apk文件，通知服务器下载apk文件
                // 打开流程是先打开apk下载地址，再打开h5地址，会先加载后打开的地址
                // 如果先打开h5地址再打开apk地址，会导致无法显示h5地址
                openH5OrApkUrl(mContext, apk_url, download_way);
                openH5OrApkUrl(mContext, h5_url, download_way);
                return AdConstants.AD_STATUS_DOWNLOAD;
            }
        }
    }


    /**
     * 在浏览器中打开uri
     *
     * @param mContext  Context
     * @param uriString uri
     */
    public static void openUriInBrowser(Context mContext, String uriString) throws ActivityNotFoundException {
        // TODO: 13/01/2017 是否用默认浏览器打开
        if (TextUtils.isEmpty(uriString)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(uriString));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        mContext.startActivity(Intent.createChooser(intent, "请选择浏览器"));
    }

    /**
     * 使用默认浏览器打开链接，如果无默认浏览器则
     */
    public static void openUriInDefaultBrowser(Context mContext, String uriString) throws ActivityNotFoundException {
        if (TextUtils.isEmpty(uriString)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
        intent.setPackage("com.android.browser");
        PackageManager packageManager = mContext.getPackageManager();
        ResolveInfo info = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (info != null) {
            //如果有可接受该intent的APP则直接唤起APP
            mContext.startActivity(intent);
        } else {
            //未安装该浏览器
            openUriInBrowser(mContext, uriString);
        }
    }

    /**
     * 在应用市场中下载应用
     *
     * @param mContext Context
     * @param pkg_name 包名
     */
    public static void openUriInMarket(Context mContext, String pkg_name) throws ActivityNotFoundException {
        // TODO: 13/01/2017 采用指定应用市场下载APP
        String marketUri = "market://details?id=" + pkg_name;
        Intent intent = new Intent("android.intent.action.VIEW",
                Uri.parse(marketUri));
        mContext.startActivity(intent);
    }

    /**
     * 根据包名打开APP
     *
     * @param mContext Context
     * @param pkg_name 包名
     */
    public static boolean openAppWithPN(Context mContext, String pkg_name) {
        Intent resolveIntent = mContext.getPackageManager().getLaunchIntentForPackage(pkg_name);
        // 这里的packname就是从上面得到的目标apk的包名
        // 启动目标应用
        if (resolveIntent != null) {
            resolveIntent.putExtra(Defines.LMAD.LM_OPEN_FROM.getKey(), mContext.getPackageName());
            mContext.startActivity(resolveIntent);
            return true;
        }
        return false;
    }

    /**
     * 根据包名及Uri Scheme打开APP
     *
     * @param mContext  Context
     * @param pkg_name  包名
     * @param uriString uri scheme
     */
    public static boolean openAppWithPNUri(Context mContext, String pkg_name, String uriString)
            throws ActivityNotFoundException {
        try {
            Intent intent = Intent.parseUri(uriString, Intent.URI_INTENT_SCHEME);
            intent.putExtra(Defines.LMAD.LM_OPEN_FROM.getKey(), mContext.getPackageName());
            if (!TextUtils.isEmpty(pkg_name)) {
                intent.setPackage(pkg_name);
            }
            mContext.startActivity(intent);
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * WebView中下载APP
     *
     * @param mContext    Context
     * @param downloadUrl apk下载地址
     * @return true:
     */
    public static boolean downloadAppInBrowser(Context mContext, String downloadUrl) {
        if (TextUtils.isEmpty(downloadUrl)) {
            return false;
        }
        try {
            downloadUrl = StringUtils.removeAllBlankCharacter(downloadUrl);
            Uri httpUri = Uri.parse(downloadUrl);
            if (httpUri.getEncodedPath().endsWith(".apk")) {
                ADUtils.openUriInDefaultBrowser(mContext, downloadUrl);
                return true;
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }

        return false;
    }

    /**
     * 在WebView中下载apk
     *
     * @param mContext Context
     * @param url      下载地址
     * @param adInfo   广告信息
     */
    public static void downloadApkInWV(Context mContext, String url, AdInfo adInfo) {
        Map<String, String> appendParamMap = new ArrayMap<>();
        appendParamMap.put("lm_from", "android_webview");
        appendParamMap.put("lm_pkg_name", mContext.getPackageName());
        String appendUrl = UriUtils.appendParams(url, appendParamMap);
        if (ADUtils.isAppInstalled(mContext, adInfo.getPkg_name()) &&
                TextUtils.equals(adInfo.getApk_exist_open(), "1")) {
            //安装则直接打开APP 提活
            ADUtils.openAppWithPN(mContext, adInfo.getPkg_name());
            ADUtils.recordStatus(mContext, adInfo, AdConstants.AD_STATUS_OPEN);
        } else {
            //否则下载APP
            if (TextUtils.equals(adInfo.getDownload_way(), "1") &&
                    AndroidUtils.hasService(mContext, LMDownloadService.class.getName()) &&
                    PermissionUtils.selfPermissionGranted(
                            mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //下载方式是应用内下载，注册了下载服务，有写入sd卡的权限则在APP内下载
                downloadApkService(mContext, appendUrl);
            } else {
                //外置浏览器下载
                downloadAppInBrowser(mContext, appendUrl);
            }
        }
    }

    /**
     * 打开h5页面或下载apk文件
     *
     * @param mContext     Context
     * @param url          url
     * @param download_way 下载方式
     */
    public static void openH5OrApkUrl(Context mContext, String url, String download_way) {
        LogUtils.d("openH5OrApkUrl url = " + url);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        try {
            url = StringUtils.removeAllBlankCharacter(url);
            Uri httpUri = Uri.parse(url);
            boolean apkFile = httpUri.getEncodedPath().endsWith(".apk");
            if (apkFile && TextUtils.equals(download_way, "1") &&
                    AndroidUtils.hasService(mContext, LMDownloadService.class.getName()) &&
                    PermissionUtils.selfPermissionGranted(
                            mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //下载方式是应用内下载，注册了下载服务，有写入sd卡的权限则在APP内下载
                downloadApkService(mContext, url);
            } else {
                //外置浏览器打开h5_url
                try {
                    openUriInDefaultBrowser(mContext, url);
                } catch (ActivityNotFoundException e) {
                    LogUtils.d("未安装任何浏览器 ");
                    e.printStackTrace();
                }
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    /**
     * 下载apk的service
     *
     * @param mContext Context
     * @param url      下载地址
     */
    public static void downloadApkService(Context mContext, String url) {
        Intent intent = new Intent(mContext, LMDownloadService.class);
        intent.putExtra(DOWNLOAD_FILE_URL, url);
        intent.putExtra(DOWNLOAD_FILE_NAME, "lm" + System.currentTimeMillis() + ".apk");
        mContext.startService(intent);
    }

    public static void downloadApkAlert(
            final Context mContext, final WebView mWebView,
            final AdInfo adInfo, final String url) {
        if (TextUtils.equals(adInfo.getAlert_download_apk(), "1")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage("确定下载？");
            builder.setTitle("下载提示");
            builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ADUtils.downloadApkInWV(mContext, url, adInfo);
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).create();
            builder.show();
        } else {
            ADUtils.downloadApkInWV(mContext, url, adInfo);
        }
    }

    public static void webviewBack(Context mContext, WebView mWebView) {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            ((Activity) mContext).finish();
        }
    }

    /**
     * 记录状态
     *
     * @param mContext      Context
     * @param optimalAdInfo AdInfo
     * @param status        状态
     */
    public static void recordStatus(Context mContext, AdInfo optimalAdInfo, String status) {
        // 此处上报广告状态
    }

}
