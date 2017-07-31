package com.ww.lp.lputils.constants;

/**
 * Created by LinkedME06 on 11/01/2017.
 */

public class Defines {


    /**
     * <p> 定义所有服务请求的路径 </p>
     */
    public enum RequestPath {
        //广告接口
        AdGetAd("get_ad"),
        AdRecordStatus("record_status");
        private String key = "";

        RequestPath(String key) {
            this.key = key;
        }

        public String getPath() {
            return key;
        }

        @Override
        public String toString() {
            return key;
        }
    }

    public enum LMAD {
        /**
         * 标识广告计划
         */
        AD_CODE("ad_code"),
        /**
         * 广告位id
         */
        AD_POSITION("ad_position"),
        /**
         * 需要被唤起APP的uri_scheme
         */
        URI_SCHEME("uri_scheme"),
        /**
         * 被唤起APP的包名
         */
        PKG_NAME("pkg_name"),
        /**
         * 是否需要先在WebView中显示 0：否 1：是
         */
        HAS_WEB_VIEW("has_web_view"),
        /**
         * 是否自动打开APP
         */
        IS_AUTO_OPEN("is_auto_open"),
        /**
         * WebView中加载的h5页面
         */
        H5_URL("h5_url"),
        /**
         * 标识请求的id
         */
        REQUEST_ID("request_id"),
        /**
         * 展示的图片url地址
         */
        IMG_URL("img_url"),
        /**
         * 显示的内容
         */
        AD_CONTENT("ad_content"),
        /**
         * 是否召回APP
         */
        RECALL_APP("recall_app"),
        /**
         * 是否开启WebView的JavaScript 1：开启 默认开启
         */
        ENABLE_WEBVIEW_JS("enable_webview_js"),
        /**
         * 标识从哪个APP唤起应用
         */
        LM_OPEN_FROM("lm_open_from"),
        /**
         * apk文件下载方式 0：外置浏览器 1：APP内下载
         */
        DOWNLOAD_WAY("download_way"),
        /**
         * apk下载时如果APP已装则直接打开不下载，0：不打开 1：打开 默认为0
         */
        APK_EXIST_OPEN("apk_exist_open"),
        /**
         * 下载时是否提示下载APP还是直接下载？0：不提示 1：提示 默认提示
         */
        ALERT_DOWNLOAD_APK("alert_download_apk"),
        /**
         * 下载提示信息
         */
        ALERT_DOWNLOAD_INFO("alert_download_info"),
        /**
         * 用户自定义标签，以逗号分隔
         */
        TAGS("tags"),
        /**
         * 广告device_id，回传给服务器端
         */
        AD_DEVICE_ID("ad_device_id"),
        /**
         * 广告激活匹配类型，androidid或imei，回传给服务器端
         */
        ACTIVE_DEVICE_TYPE("active_device_type"),
        /**
         * 广告点击状态 1:提活 2：显示广告 3：点击广告
         */
        STATUS("status"),
        /**
         * 请求的广告类型，banner: 横幅广告 floating:插屏广告
         */
        AD_TYPE("ad_type"),
        /**
         * 屏幕宽度,屏幕宽度和高度只在ad_type为floating时上传
         */
        SCRWIDTH("scrwidth"),
        /**
         * 屏幕高度,屏幕宽度和高度只在ad_type为floating时上传
         */
        SCRHEIGHT("scrheight"),
        /**
         * 如果值为1，将判断用户安装状态，app未安装不展示；
         * 如果值为0，则不判断app安装状态，直接展示；
         */
        CHECK_INSTALL_STATUS("check_install_status"),
        /**
         * 是否为来自移动端SDK请求，true:是  false:否
         */
        IS_FROME_SDK("is_frome_sdk"),
        /**
         * 广告素材ID，其值为get_ad接口返回结果里的ad_content_id的值
         */
        AD_CONTENT_ID("ad_content_id"),
        /**
         * apk下载地址
         */
        APK_URL("apk_url");


        private String key = "";

        LMAD(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        @Override
        public String toString() {
            return key;
        }
    }


    public enum Jsonkey {

        Last_Round_Trip_Time("lrtt"),
        Queue_Wait_Time("qwt"),
        /**
         * 校验参数
         */
        LKME_SIGN("sign"),
        /**
         * 设备IMEI号（简化参数）
         */
        LKME_IMEI("imei"),
        /**
         * 设备Android ID（简化参数）
         */
        LKME_ANDROID_ID("android_id"),
        /**
         * 设备型号,比如华为5X的设备型号:KIW-AL10
         */
        LKME_DEVICE_MODEL("device_model"),
        /**
         * 操作系统类型
         */
        LKME_OS("os"),
        /**
         * 操作系统版本号 23
         */
        LKME_OS_VERSION_INT("os_version_int"),
        /**
         * 操作系统版本 6.0.1
         */
        LKME_OS_VERSION("os_version"),
        /**
         * 设备屏幕像素密度
         */
        LKME_SCREEN_DPI("screen_dpi"),
        /**
         * 设备屏幕高度(像素)
         */
        LKME_SCREEN_HEIGHT("screen_height"),
        /**
         * 设备屏幕宽度(像素)
         */
        LKME_SCREEN_WIDTH("screen_width"),
        /**
         * 设备当前是否在Wi-Fi环境下
         */
        LKME_IS_WIFI("is_wifi"),
        /**
         * 当前请求是否为referrable
         */
        LKME_IS_REFERRABLE("is_referrable"),
        /**
         * 当前请求是否为debug模式
         */
        LKME_IS_DEBUG("is_debug"),
        /**
         * 当前设备地理位置信息
         */
        LKME_LAT_VAL("lat_val"),
        LKME_GoogleAdvertisingID("google_advertising_id"),
        /**
         * 运营商
         */
        LKME_CARRIER("carrier"),
        /**
         * 应用版本
         */
        LKME_APP_VERSION("app_version"),
        /**
         * 应用编译版本号
         */
        LKME_APP_VERSION_CODE("app_version_code"),
        /**
         * SDK更新状态标识
         */
        LKME_SDK_UPDATE("sdk_update"),
        /**
         * 当前SDK版本
         */
        LKME_SDK_VERSION("sdk_version"),
        /**
         * 请求重试次数
         */
        LKME_RETRY_TIMES("retry_times"),
        /**
         * 设备指纹ID
         */
        LKME_DF_ID("device_fingerprint_id"),
        /**
         * 设备ID
         */
        LKME_IDENTITY_ID("identity_id"),
        /**
         * 跳转链接
         */
        LKME_LINK("link"),
        /**
         * 会话ID
         */
        LKME_SESSION_ID("session_id"),
        /**
         * 会话session
         */
        LKME_CLOSE_SESSION("close_session"),
        /**
         * 浏览器指纹ID
         */
        LKME_BF_ID("browser_fingerprint_id"),
        /**
         * 自定义参数
         */
        LKME_PARAMS("params"),
        /**
         * 是否为首次会话
         */
        LKME_IS_FIRST_SESSION("is_first_session"),
        /**
         * 是否来自LinkedMe链接
         */
        LKME_CLICKED_LINKEDME_LINK("clicked_linkedme_link"),
        /**
         * 链接元数据
         */
        LKME_METADATA("$metadata"),
        /**
         * 控制参数
         */
        LKME_CONTROLL("$control"),
        /**
         * 用户身份标签
         */
        LKME_IDENTITY("identity"),
        /**
         * 用户唯一标识
         */
        LKME_USER_ID("user_id"),
        /**
         * 按钮Id
         */
        LKME_BUTTON_ID("btn_id"),
        /**
         * 深度链接生成的md5值
         */
        LKME_DEEPLINK_MD5("deeplink_md5_new"),
        /**
         * 是否为测试短链,true为是 false为否
         */
        LKME_IS_TEST_URL("is_test_url"),
        /**
         * 深度链接
         */
        LKME_DEEPLINK_URL("deeplink_url"),
        /**
         * uri-scheme
         */
        LKME_URI_SCHEME("uri_scheme"),
        /**
         * timestamp 时间戳
         */
        LKME_TIMESTAMP("timestamp"),
        /**
         * 应用数据
         */
        LKME_APPS_DATA("apps_data"),
        /**
         * 应用数据
         */
        LKME_DEVICE_UPDATE("device_update"),
        /**
         * 是否调用close接口
         */
        LKME_CLOSE_ENABLE("close_enable"),
        /**
         * 是否开启
         */
        LKME_IS_GAL("is_gal"),
        /**
         * 时间间隔
         */
        LKME_GAL_INTERVAL("interval"),
        /**
         * gal请求时间间隔
         */
        LKME_GAL_REQ_INTERVAL("req_interval"),
        /**
         * track
         */
        LKME_GAL_TRACK("track"),
        /**
         * 经度
         */
        LKME_LNG("lng"),
        /**
         * 纬度
         */
        LKME_LAT("lat"),
        /**
         * 应用名称
         */
        LKME_APP_NAME("app_name");

        private String key = "";

        Jsonkey(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        @Override
        public String toString() {
            return key;
        }
    }



}
