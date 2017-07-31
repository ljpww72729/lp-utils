package com.ww.lp.lputils.android.view;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.ww.lp.lputils.ADUtils;
import com.ww.lp.lputils.LogUtils;
import com.ww.lp.lputils.constants.Defines;
import com.ww.lp.lputils.entry.ad.AdConstants;
import com.ww.lp.lputils.entry.ad.AdInfo;


/**
 * Created by LinkedME06 on 11/01/2017.
 */

public class LMWebViewClient extends WebViewClient {

    private Activity mActivity;
    private RelativeLayout progressLayout;
    private AdInfo adInfo;

    public LMWebViewClient(Activity activity, RelativeLayout progressLayout, AdInfo adInfo) {
        this.mActivity = activity;
        this.progressLayout = progressLayout;
        this.adInfo = adInfo;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        //重写该方法是为了处理uri scheme,对于uri scheme则直接唤起APP
        //去掉回车、换行、tab
        String stray_spacing = "[\n\r\t\\p{Zl}\\p{Zp}\u0085]+";
        url = url.trim();
        url = url.replaceAll(stray_spacing, "");
        LogUtils.d("url ===== " + url);
        String rfc2396regex = "^(([a-zA-Z][a-zA-Z0-9\\+\\-\\.]*)://)(([^/?#]*)?([^?#]*)(\\?([^#]*))?)?(#(.*))?";
        String http_scheme_slashes = "^(https?://)/+(.*)";
        //(?i)后面的匹配不区分大小写
        String all_schemes_pattern = "(?i)^(http|https|ftp|mms|rtsp|wais)://.*";
        if (url.matches(all_schemes_pattern)) {
            // TODO: 14/01/2017 追加参数，标识是来自WebView的请求
            Uri httpUri = Uri.parse(url);
            if (httpUri.getEncodedPath().endsWith(".apk")) {
                ADUtils.downloadApkAlert(mActivity, view, adInfo, url);
                return true;
            }
            view.loadUrl(url);
            return false;
        }
        if (url.matches(rfc2396regex)) {
            try {
                Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);

                if (intent != null) {
                    //  view.stopLoading();
                    PackageManager packageManager = mActivity.getPackageManager();
                    ResolveInfo info = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
                    if (info != null) {
                        //如果有可接受该intent的APP则直接唤起APP
                        //intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        // TODO: 14/01/2017 追加参数标识从哪个APP唤起
                        intent.putExtra(Defines.LMAD.LM_OPEN_FROM.getKey(), mActivity.getPackageName());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mActivity.startActivity(intent);
                        ADUtils.recordStatus(mActivity, adInfo, AdConstants.AD_STATUS_OPEN);
                    } else {
                        //否则加载回调页面
                        String fallbackUrl = intent.getStringExtra("browser_fallback_url");

                        if (!TextUtils.isEmpty(fallbackUrl)) {
                            // 调用外置浏览器加载回调页面,建议外置浏览器加载回调页面
                            ADUtils.openUriInBrowser(mActivity, fallbackUrl);

                            //或者:在WebView中加载
//                                  view.loadUrl(fallbackUrl);
                        } else {
                            // view.loadUrl(url);
                            return super.shouldOverrideUrlLoading(view, url);
                        }
                    }

                    return true;
                }
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }
        }
        view.loadUrl(url);
        return false;

//                return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        progressLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        progressLayout.setVisibility(View.GONE);
    }


    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        view.loadDataWithBaseURL(null, "<html><head><title>页面错误</title></head><body><br /><br /><div>该页面无法加载~</div><div>将以下地址复制到浏览器中尝试打开</div><div>" + view.getUrl() + "</div></body></html>", "text/html", "utf-8", null);
        // TODO: 14/01/2017 页面加载失败时的展示

    }

}
