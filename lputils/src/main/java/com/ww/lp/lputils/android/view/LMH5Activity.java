package com.ww.lp.lputils.android.view;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ww.lp.lputils.ADUtils;
import com.ww.lp.lputils.android.AndroidUtils;
import com.ww.lp.lputils.entry.ad.AdConstants;
import com.ww.lp.lputils.entry.ad.AdInfo;


/**
 * <p>广告展示页面</p>
 *
 * Created by lipeng on 11/01/2017.
 */

public class LMH5Activity extends Activity {
    public static final String H5_URL = "h5_url";
    public static final String AD_INFO = "ad_info";

    private RelativeLayout rootView;
    private WebView webView;
    private WebViewClient webViewClient;
    AdInfo adInfo = null;
    private DownloadManager dm;
    private BroadcastReceiver receiver;
    public static final String DOWNLOAD_FILE_URL = "download_file_url";
    public static final String DOWNLOAD_FILE_NAME = "download_file_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        String h5_url = "";
        if (getIntent() != null && getIntent().getExtras() != null) {
            adInfo = getIntent().getExtras().getParcelable(AD_INFO);
            if (adInfo == null) {
                //防止外部调用该字段为空
                finish();
            }
            h5_url = adInfo.getH5_url();
        } else {
            finish();
        }
//        h5_url = "http://linkedme.cc:9099/browser/demo.apk";
        rootView = new RelativeLayout(this);

        //WebView
        webView = new WebView(this);
        rootView.addView(webView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        RelativeLayout progressLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams progressLayoutLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //正在加载视图
        ProgressBar loadingProgressBar = new ProgressBar(this);
        loadingProgressBar.setTag("lm_loading");
        RelativeLayout.LayoutParams loadingLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingLP.addRule(RelativeLayout.CENTER_IN_PARENT);
        TextView progressInfo = new TextView(this);
        progressInfo.setTag("lm_loading_info");
        progressInfo.setText("页面无法加载");
        progressInfo.setTextColor(Color.BLACK);
        progressInfo.setTextSize(AndroidUtils.convertSpToPixels(this, 20));
        progressInfo.setVisibility(View.GONE);
        progressLayout.addView(progressInfo, loadingLP);
        progressLayout.addView(loadingProgressBar, loadingLP);
        rootView.addView(progressLayout, progressLayoutLP);

        //关闭按钮
        final CloseView closeView = new CloseView(this);
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int closeViewWidth = AndroidUtils.convertDpToPixels(this, 34);
        int closeViewHeight = AndroidUtils.convertDpToPixels(this, 34);
        RelativeLayout.LayoutParams closeViewLP = new RelativeLayout.LayoutParams(closeViewWidth, closeViewHeight);
        closeViewLP.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        closeViewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        int closeViewMarginTop = AndroidUtils.convertDpToPixels(this, 7);
        int closeViewMarginRight = AndroidUtils.convertDpToPixels(this, 6);
        closeViewLP.setMargins(0, closeViewMarginTop, closeViewMarginRight, 0);
        rootView.addView(closeView, closeViewLP);

        setContentView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (adInfo != null && TextUtils.equals(adInfo.getEnable_webview_js(), "1")) {
            // TODO: 14/01/2017  服务器端可控制是否开启js，默认是开启的
            webView.getSettings().setJavaScriptEnabled(true);
        }
        webView.loadUrl(h5_url);
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(final String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri httpUri = Uri.parse(url);
                if (httpUri.getEncodedPath().endsWith(".apk")) {
                    ADUtils.downloadApkAlert(LMH5Activity.this, webView, adInfo, url);
                }
            }
        });
        webViewClient = new LMWebViewClient(this, progressLayout, adInfo);
        webView.setWebViewClient(webViewClient);
        if (TextUtils.equals(adInfo.getIs_auto_open(), "1") && ADUtils.isAppInstalled(this, adInfo.getPkg_name())) {
            //如果自动唤起APP并且安装了APP，则打开APP
            if (ADUtils.openApp(this, adInfo.getPkg_name(), adInfo.getUri_scheme())) {
                ADUtils.recordStatus(this, adInfo, AdConstants.AD_STATUS_OPEN);
            } else {
                ADUtils.recordStatus(this, adInfo, AdConstants.AD_STATUS_NOT_OPEN);
            }
        }
    }

    @Override
    public void onBackPressed() {
        back();
    }

    public void back() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (rootView != null) {
                rootView.removeAllViews();
                ((ViewGroup) rootView.getParent()).removeAllViews();
            }
            webView.destroy();
            webView = null;
        } catch (Exception ignore) {

        }
    }


}
