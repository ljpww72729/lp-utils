package com.ww.lp.lputils.entry.ad;

import android.os.Parcel;
import android.os.Parcelable;

import com.ww.lp.lputils.constants.Defines;

import org.json.JSONObject;

/**
 * 广告信息
 *
 * Created by LinkedME06 on 12/01/2017.
 */

public class AdInfo implements Parcelable {

    /**
     * 标识广告计划
     */
    private String ad_code;
    /**
     * 广告位id
     */
    private String ad_position;
    /**
     * 需要被唤起APP的uri_scheme
     */
    private String uri_scheme;
    /**
     * 被唤起APP的包名
     */
    private String pkg_name;
    /**
     * "保留参数"
     * 是否需要先在WebView中显示 0：否 1：是，默认为0
     */
    private String has_web_view;
    /**
     * 是否自动打开APP
     */
    private String is_auto_open;
    /**
     * WebView中加载的h5页面
     */
    private String h5_url;
    /**
     * 是否召回APP 1：需要 0：不需要
     */
    private String recall_app;
    /**
     * 标识请求的id
     */
    private String request_id;
    /**
     * 展示的图片url地址
     */
    private String img_url;
    /**
     * 显示的内容
     */
    private String ad_content;
    /**
     * 是否开启WebView的JavaScript，默认开启
     */
    private String enable_webview_js = "1";
    /**
     * apk文件下载方式 0：外置浏览器 1：APP内下载
     */
    private String download_way;
    /**
     * apk下载时如果APP已装则直接打开不下载，0：不打开 1：打开 默认为不打开
     */
    private String apk_exist_open = "0";
    /**
     * 下载时是否提示下载APP还是直接下载？0：不提示 1：提示 默认提示
     */
    private String alert_download_apk = "1";
    /**
     * 广告device_id，回传给服务器端
     */
    private String ad_device_id;
    /**
     * 广告激活匹配类型，androidid或imei，回传给服务器端
     */
    private String active_device_type;
    /**
     * 下载提示信息
     */
    private String alert_download_info = "您确定下载该应用吗？";
    /**
     * 如果值为true(1)，将判断用户安装状态，app未安装不展示；
     * 如果值为false(0)，则不判断app安装状态，直接展示；
     */
    private boolean check_install_status = true;

    /**
     * 广告类型 banner: 横幅广告  floating: 插屏广告
     */
    private String ad_type;

    /**
     * apk下载地址
     */
    private String apk_url;

    public String getApk_url() {
        return apk_url;
    }

    public void setApk_url(String apk_url) {
        this.apk_url = apk_url;
    }

    /**
     * 广告素材ID，其值为get_ad接口返回结果里的ad_content_id的值
     */
    private String ad_content_id;

    public String getAd_content_id() {
        return ad_content_id;
    }

    public void setAd_content_id(String ad_content_id) {
        this.ad_content_id = ad_content_id;
    }

    public String getAd_type() {
        return ad_type;
    }

    public void setAd_type(String ad_type) {
        this.ad_type = ad_type;
    }

    public String getAd_device_id() {
        return ad_device_id;
    }

    public void setAd_device_id(String ad_device_id) {
        this.ad_device_id = ad_device_id;
    }

    public String getActive_device_type() {
        return active_device_type;
    }

    public void setActive_device_type(String active_device_type) {
        this.active_device_type = active_device_type;
    }

    public String getAlert_download_info() {
        return alert_download_info;
    }

    public void setAlert_download_info(String alert_download_info) {
        this.alert_download_info = alert_download_info;
    }

    public String getAlert_download_apk() {
        return alert_download_apk;
    }

    public void setAlert_download_apk(String alert_download_apk) {
        this.alert_download_apk = alert_download_apk;
    }

    public String getApk_exist_open() {
        return apk_exist_open;
    }

    public void setApk_exist_open(String apk_exist_open) {
        this.apk_exist_open = apk_exist_open;
    }


    public String getDownload_way() {
        return download_way;
    }

    public void setDownload_way(String download_way) {
        this.download_way = download_way;
    }

    public String getAd_content() {
        return ad_content;
    }

    public void setAd_content(String ad_content) {
        this.ad_content = ad_content;
    }

    public String getAd_code() {
        return ad_code;
    }

    public void setAd_code(String ad_code) {
        this.ad_code = ad_code;
    }

    public String getAd_position() {
        return ad_position;
    }

    public void setAd_position(String ad_position) {
        this.ad_position = ad_position;
    }

    public String getUri_scheme() {
        return uri_scheme;
    }

    public void setUri_scheme(String uri_scheme) {
        this.uri_scheme = uri_scheme;
    }

    public String getPkg_name() {
        return pkg_name;
    }

    public void setPkg_name(String pkg_name) {
        this.pkg_name = pkg_name;
    }

    public String getHas_web_view() {
        return has_web_view;
    }

    public void setHas_web_view(String has_web_view) {
        this.has_web_view = has_web_view;
    }

    public String getIs_auto_open() {
        return is_auto_open;
    }

    public void setIs_auto_open(String is_auto_open) {
        this.is_auto_open = is_auto_open;
    }

    public String getH5_url() {
        return h5_url;
    }

    public void setH5_url(String h5_url) {
        this.h5_url = h5_url;
    }

    public String getRecall_app() {
        return recall_app;
    }

    public void setRecall_app(String recall_app) {
        this.recall_app = recall_app;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getEnable_webview_js() {
        return enable_webview_js;
    }

    public void setEnable_webview_js(String enable_webview_js) {
        this.enable_webview_js = enable_webview_js;
    }

    public boolean isCheck_install_status() {
        return check_install_status;
    }

    public void setCheck_install_status(boolean check_install_status) {
        this.check_install_status = check_install_status;
    }

    /**
     * 获取广告信息
     *
     * @return 广告信息
     */
    public static AdInfo getAdInfo(JSONObject jsonObject) {
        AdInfo adInfo = new AdInfo();
        adInfo.setAd_code(jsonObject.optString(Defines.LMAD.AD_CODE.getKey()));
        adInfo.setAd_position(jsonObject.optString(Defines.LMAD.AD_POSITION.getKey()));
        adInfo.setUri_scheme(jsonObject.optString(Defines.LMAD.URI_SCHEME.getKey()));
        adInfo.setPkg_name(jsonObject.optString(Defines.LMAD.PKG_NAME.getKey()));
        adInfo.setHas_web_view(jsonObject.optString(Defines.LMAD.HAS_WEB_VIEW.getKey(), "0"));
        adInfo.setIs_auto_open(jsonObject.optString(Defines.LMAD.IS_AUTO_OPEN.getKey()));
        adInfo.setH5_url(jsonObject.optString(Defines.LMAD.H5_URL.getKey()));
        adInfo.setRequest_id(jsonObject.optString(Defines.LMAD.REQUEST_ID.getKey()));
        adInfo.setImg_url(jsonObject.optString(Defines.LMAD.IMG_URL.getKey()));
        adInfo.setAd_content(jsonObject.optString(Defines.LMAD.AD_CONTENT.getKey()));
        adInfo.setRecall_app(jsonObject.optString(Defines.LMAD.RECALL_APP.getKey()));
        adInfo.setEnable_webview_js(jsonObject.optString(Defines.LMAD.ENABLE_WEBVIEW_JS.getKey(), "1"));
        adInfo.setDownload_way(jsonObject.optString(Defines.LMAD.DOWNLOAD_WAY.getKey(), "0"));
        adInfo.setApk_exist_open(jsonObject.optString(Defines.LMAD.APK_EXIST_OPEN.getKey(), "0"));
        adInfo.setAlert_download_apk(jsonObject.optString(Defines.LMAD.ALERT_DOWNLOAD_APK.getKey(), "1"));
        adInfo.setAlert_download_info(jsonObject.optString(Defines.LMAD.ALERT_DOWNLOAD_INFO.getKey(), "您确定下载该应用吗？"));
        adInfo.setAd_device_id(jsonObject.optString(Defines.LMAD.AD_DEVICE_ID.getKey()));
        adInfo.setActive_device_type(jsonObject.optString(Defines.LMAD.ACTIVE_DEVICE_TYPE.getKey()));
        adInfo.setCheck_install_status(jsonObject.optString(Defines.LMAD.CHECK_INSTALL_STATUS.getKey(), "1").equals("1"));
        adInfo.setAd_content_id(jsonObject.optString(Defines.LMAD.AD_CONTENT_ID.getKey(), "-1"));
        adInfo.setApk_url(jsonObject.optString(Defines.LMAD.APK_URL.getKey(), ""));
        return adInfo;
    }

    public AdInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ad_code);
        dest.writeString(this.ad_position);
        dest.writeString(this.uri_scheme);
        dest.writeString(this.pkg_name);
        dest.writeString(this.has_web_view);
        dest.writeString(this.is_auto_open);
        dest.writeString(this.h5_url);
        dest.writeString(this.recall_app);
        dest.writeString(this.request_id);
        dest.writeString(this.img_url);
        dest.writeString(this.ad_content);
        dest.writeString(this.enable_webview_js);
        dest.writeString(this.download_way);
        dest.writeString(this.apk_exist_open);
        dest.writeString(this.alert_download_apk);
        dest.writeString(this.ad_device_id);
        dest.writeString(this.active_device_type);
        dest.writeString(this.alert_download_info);
        dest.writeByte(this.check_install_status ? (byte) 1 : (byte) 0);
        dest.writeString(this.ad_type);
        dest.writeString(this.apk_url);
        dest.writeString(this.ad_content_id);
    }

    protected AdInfo(Parcel in) {
        this.ad_code = in.readString();
        this.ad_position = in.readString();
        this.uri_scheme = in.readString();
        this.pkg_name = in.readString();
        this.has_web_view = in.readString();
        this.is_auto_open = in.readString();
        this.h5_url = in.readString();
        this.recall_app = in.readString();
        this.request_id = in.readString();
        this.img_url = in.readString();
        this.ad_content = in.readString();
        this.enable_webview_js = in.readString();
        this.download_way = in.readString();
        this.apk_exist_open = in.readString();
        this.alert_download_apk = in.readString();
        this.ad_device_id = in.readString();
        this.active_device_type = in.readString();
        this.alert_download_info = in.readString();
        this.check_install_status = in.readByte() != 0;
        this.ad_type = in.readString();
        this.apk_url = in.readString();
        this.ad_content_id = in.readString();
    }

    public static final Creator<AdInfo> CREATOR = new Creator<AdInfo>() {
        @Override
        public AdInfo createFromParcel(Parcel source) {
            return new AdInfo(source);
        }

        @Override
        public AdInfo[] newArray(int size) {
            return new AdInfo[size];
        }
    };
}
