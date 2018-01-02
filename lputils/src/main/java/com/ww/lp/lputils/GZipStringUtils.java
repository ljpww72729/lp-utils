package com.ww.lp.lputils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 使用gzip压缩解压字符串，用于减少数据流量
 * Created by LinkedME06 on 2017/12/21.
 */

public class GZipStringUtils {
    public static void main(String[] args) throws Exception {
//        StringBuilder value = new StringBuilder() ;
//        for (int i = 0; i < 100; i++) {
//            value.append("java gzip 压缩测试");
//        }
//        String value = "com.taobao.taobao,com.eg.android.AlipayGphone,com.tencent.qqlive,com.qiyi.video,com.sina.weibo,com.autonavi.minimap,com.sankuai.meituan,com.smile.gifmaker,com.baidu.searchbox,com.tencent.mtt,com.youku.phone,com.baidu.BaiduMap,com.tencent.news,com.jingdong.app.mall,com.ss.android.article.news,com.achievo.vipshop,com.UCMobile,ctrip.android.view,com.Qunar,me.ele,com.dianping.v1,com.baidu.tieba,com.tencent.reading,com.tmall.wireless,com.sankuai.meituan.takeoutnew,com.sohu.sohuvideo,com.zhihu.android,com.immomo.momo,com.oppo.news,com.suning.mobile.ebuy,com.sina.news,com.netease.newsreader.activity,com.yidian.xiaomi,com.sohu.newsclient,com.ifeng.news2";
//        String value = "%5B%7B%22version_name%22%3A%2211.0.0.818%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.UCMobile-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.UCMobile-1.apk%22%2C%22install_date%22%3A1471435557497%2C%22uri_scheme%22%3A%22tencent1101031180%22%2C%22name%22%3A%22UC%E6%B5%8F%E8%A7%88%E5%99%A8%22%2C%22version_code%22%3A671%2C%22last_update_date%22%3A1471435557497%2C%22app_identifier%22%3A%22com.UCMobile%22%7D%2C%7B%22version_name%22%3A%220.1.5%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.tripadvisor.tripadvisor.daodao-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.tripadvisor.tripadvisor.daodao-1.apk%22%2C%22version_code%22%3A2%2C%22app_identifier%22%3A%22com.tripadvisor.tripadvisor.daodao%22%2C%22name%22%3A%22%E5%88%B0%E5%88%B0%E6%97%A0%E7%BA%BF%22%7D%2C%7B%22version_name%22%3A%222.5.4.170%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sec.android.app.shealth-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sec.android.app.shealth-1.apk%22%2C%22install_date%22%3A1388534516872%2C%22name%22%3A%22S+%E5%81%A5%E5%BA%B7%22%2C%22version_code%22%3A254170%2C%22last_update_date%22%3A1388534516872%2C%22app_identifier%22%3A%22com.sec.android.app.shealth%22%7D%2C%7B%22version_name%22%3A%221.1.1.125219ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.akazam.android.wlandialer-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.akazam.android.wlandialer-1.apk%22%2C%22install_date%22%3A1388534557624%2C%22uri_scheme%22%3A%22showwebview%22%2C%22name%22%3A%22%E5%A4%A9%E7%BF%BC%E5%AE%BD%E5%B8%A6%22%2C%22version_code%22%3A13%2C%22last_update_date%22%3A1388534557624%2C%22app_identifier%22%3A%22com.akazam.android.wlandialer%22%7D%2C%7B%22version_name%22%3A%223.0.2%28F%29ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.ct.client-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.ct.client-1.apk%22%2C%22install_date%22%3A1388534485779%2C%22name%22%3A%22%E6%8E%8C%E4%B8%8A%E8%90%A5%E4%B8%9A%E5%8E%85%22%2C%22version_code%22%3A3020%2C%22last_update_date%22%3A1388534485779%2C%22app_identifier%22%3A%22com.ct.client%22%7D%2C%7B%22version_name%22%3A%221.6.4ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.chinatelecom.bestpayclient-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.chinatelecom.bestpayclient-1.apk%22%2C%22install_date%22%3A1388534479974%2C%22name%22%3A%22%E7%BF%BC%E6%94%AF%E4%BB%98%22%2C%22version_code%22%3A11%2C%22last_update_date%22%3A1388534479974%2C%22app_identifier%22%3A%22com.chinatelecom.bestpayclient%22%7D%2C%7B%22version_name%22%3A%221.8.0ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.besttone.elocal-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.besttone.elocal-1.apk%22%2C%22install_date%22%3A1388534549942%2C%22uri_scheme%22%3A%22elocal%22%2C%22name%22%3A%22%E7%BF%BC%E5%91%A8%E8%BE%B9%22%2C%22version_code%22%3A1008000%2C%22last_update_date%22%3A1388534549942%2C%22app_identifier%22%3A%22com.besttone.elocal%22%7D%2C%7B%22version_name%22%3A%221.4%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.netease.print-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.netease.print-1.apk%22%2C%22version_code%22%3A5%2C%22app_identifier%22%3A%22com.netease.print%22%2C%22name%22%3A%22Netease+Print%22%7D%2C%7B%22version_name%22%3A%222.6.1ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.lectek.android.sfreader-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.lectek.android.sfreader-1.apk%22%2C%22install_date%22%3A1388534465585%2C%22name%22%3A%22%E5%A4%A9%E7%BF%BC%E9%98%85%E8%AF%BB%22%2C%22version_code%22%3A261%2C%22last_update_date%22%3A1388534465585%2C%22app_identifier%22%3A%22com.lectek.android.sfreader%22%7D%2C%7B%22version_name%22%3A%221.4.1ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.peopleClients.views-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.peopleClients.views-1.apk%22%2C%22install_date%22%3A1388534513488%2C%22name%22%3A%22%E4%BA%BA%E6%B0%91%E6%96%B0%E9%97%BB%22%2C%22version_code%22%3A80%2C%22last_update_date%22%3A1388534513488%2C%22app_identifier%22%3A%22com.peopleClients.views%22%7D%2C%7B%22version_name%22%3A%222.7.4%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.cn21.ecloud-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.cn21.ecloud-1.apk%22%2C%22install_date%22%3A1388534459614%2C%22uri_scheme%22%3A%22cloud189%22%2C%22name%22%3A%22%E5%A4%A9%E7%BF%BC%E4%BA%91%22%2C%22version_code%22%3A61%2C%22last_update_date%22%3A1388534459614%2C%22app_identifier%22%3A%22com.cn21.ecloud%22%7D%2C%7B%22version_name%22%3A%222.0.7%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.ctoutiao-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.ctoutiao-1.apk%22%2C%22install_date%22%3A1471942106239%2C%22uri_scheme%22%3A%22ctoutiao%22%2C%22name%22%3A%22%E5%88%9B%E5%A4%B4%E6%9D%A1%22%2C%22version_code%22%3A12%2C%22last_update_date%22%3A1471942106239%2C%22app_identifier%22%3A%22com.ctoutiao%22%7D%2C%7B%22version_name%22%3A%223.3.0ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.corp21cn.mail189-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.corp21cn.mail189-1.apk%22%2C%22install_date%22%3A1388534462292%2C%22uri_scheme%22%3A%22email%22%2C%22name%22%3A%22189%E9%82%AE%E7%AE%B1%22%2C%22version_code%22%3A22%2C%22last_update_date%22%3A1388534462292%2C%22app_identifier%22%3A%22com.corp21cn.mail189%22%7D%2C%7B%22version_name%22%3A%225.0.0%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.baidu.BaiduMap-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.baidu.BaiduMap-1.apk%22%2C%22install_date%22%3A1388534477028%2C%22uri_scheme%22%3A%22bdapp%22%2C%22name%22%3A%22%E7%99%BE%E5%BA%A6%E5%9C%B0%E5%9B%BE%22%2C%22version_code%22%3A386%2C%22last_update_date%22%3A1388534477028%2C%22app_identifier%22%3A%22com.baidu.BaiduMap%22%7D%2C%7B%22version_name%22%3A%222.0.04%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.erdo.android.FJDXCartoon-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.erdo.android.FJDXCartoon-1.apk%22%2C%22install_date%22%3A1388534559298%2C%22uri_scheme%22%3A%22auth%22%2C%22name%22%3A%22%E7%88%B1%E5%8A%A8%E6%BC%AB%22%2C%22version_code%22%3A2000004%2C%22last_update_date%22%3A1388534559298%2C%22app_identifier%22%3A%22com.erdo.android.FJDXCartoon%22%7D%2C%7B%22version_name%22%3A%222.2.9%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fflipboard.cn-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fflipboard.cn-1.apk%22%2C%22install_date%22%3A1388534501006%2C%22uri_scheme%22%3A%22flipboard%22%2C%22name%22%3A%22Flipboard%22%2C%22version_code%22%3A2217%2C%22last_update_date%22%3A1388534501006%2C%22app_identifier%22%3A%22flipboard.cn%22%7D%2C%7B%22version_name%22%3A%222.02.51%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sec.android.widget.samsungapps-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sec.android.widget.samsungapps-1.apk%22%2C%22install_date%22%3A1388534519894%2C%22name%22%3A%22Samsung+Apps+Widget%22%2C%22version_code%22%3A20251%2C%22last_update_date%22%3A1388534519894%2C%22app_identifier%22%3A%22com.sec.android.widget.samsungapps%22%7D%2C%7B%22version_name%22%3A%225.9.5%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.tencent.mobileqq-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.tencent.mobileqq-1.apk%22%2C%22install_date%22%3A1471502419423%2C%22name%22%3A%22QQ%22%2C%22version_code%22%3A288%2C%22last_update_date%22%3A1471502419423%2C%22app_identifier%22%3A%22com.tencent.mobileqq%22%7D%2C%7B%22version_name%22%3A%221.0%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.samsung.vnet-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.samsung.vnet-1.apk%22%2C%22version_code%22%3A1%2C%22app_identifier%22%3A%22com.samsung.vnet%22%2C%22name%22%3A%22%E4%BA%92%E8%81%94%E6%98%9F%E7%A9%BA%22%7D%2C%7B%22version_name%22%3A%2214072204.21.143.1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sec.android.app.samsungapps-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sec.android.app.samsungapps-1.apk%22%2C%22install_date%22%3A1388534522543%2C%22uri_scheme%22%3A%22samsungapps%22%2C%22name%22%3A%22%E4%B8%89%E6%98%9F%E5%BA%94%E7%94%A8%E5%95%86%E5%BA%97%22%2C%22version_code%22%3A4211431%2C%22last_update_date%22%3A1388534522543%2C%22app_identifier%22%3A%22com.sec.android.app.samsungapps%22%7D%2C%7B%22version_name%22%3A%221.1.0ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fim.yixin-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fim.yixin-1.apk%22%2C%22install_date%22%3A1388534496589%2C%22uri_scheme%22%3A%22yixin%22%2C%22name%22%3A%22%E6%98%93%E4%BF%A1%22%2C%22version_code%22%3A172%2C%22last_update_date%22%3A1388534496589%2C%22app_identifier%22%3A%22im.yixin%22%7D%2C%7B%22version_name%22%3A%225.19.1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.wandoujia.phoenix2-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.wandoujia.phoenix2-1.apk%22%2C%22install_date%22%3A1471488297121%2C%22uri_scheme%22%3A%22wdj%22%2C%22name%22%3A%22%E8%B1%8C%E8%B1%86%E8%8D%9A%22%2C%22version_code%22%3A12038%2C%22last_update_date%22%3A1471488297121%2C%22app_identifier%22%3A%22com.wandoujia.phoenix2%22%7D%2C%7B%22version_name%22%3A%2214032901.1.30.01%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sec.everglades-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sec.everglades-1.apk%22%2C%22install_date%22%3A1388534526163%2C%22uri_scheme%22%3A%22samsunghub%22%2C%22name%22%3A%22%E4%B8%89%E6%98%9F%E6%9C%8D%E5%8A%A1%E6%B1%87%22%2C%22version_code%22%3A2014032901%2C%22last_update_date%22%3A1388534526163%2C%22app_identifier%22%3A%22com.sec.everglades%22%7D%2C%7B%22version_name%22%3A%225.2.2ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.egame-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.egame-1.apk%22%2C%22install_date%22%3A1388534548265%2C%22name%22%3A%22%E7%88%B1%E6%B8%B8%E6%88%8F%22%2C%22version_code%22%3A522%2C%22last_update_date%22%3A1388534548265%2C%22app_identifier%22%3A%22com.egame%22%7D%2C%7B%22version_name%22%3A%224.3.0%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fviva.reader-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fviva.reader-1.apk%22%2C%22install_date%22%3A1388534539302%2C%22name%22%3A%22VIVA%E7%95%85%E8%AF%BB%22%2C%22version_code%22%3A32%2C%22last_update_date%22%3A1388534539302%2C%22app_identifier%22%3A%22viva.reader%22%7D%2C%7B%22version_name%22%3A%221.0%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.kk.lp-16.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.kk.lp-16.apk%22%2C%22uri_scheme%22%3A%22kklp%22%2C%22name%22%3A%22GraphicsTest%22%2C%22version_code%22%3A1%2C%22app_identifier%22%3A%22com.kk.lp%22%7D%2C%7B%22version_name%22%3A%225.2.1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.tencent.mm-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.tencent.mm-1.apk%22%2C%22install_date%22%3A1471435663120%2C%22uri_scheme%22%3A%22weixin%22%2C%22name%22%3A%22%E5%BE%AE%E4%BF%A1%22%2C%22version_code%22%3A400%2C%22last_update_date%22%3A1471435663120%2C%22app_identifier%22%3A%22com.tencent.mm%22%7D%2C%7B%22version_name%22%3A%223.5.1ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sohu.newsclient-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sohu.newsclient-1.apk%22%2C%22install_date%22%3A1388534533049%2C%22uri_scheme%22%3A%22sohunews%22%2C%22name%22%3A%22%E6%90%9C%E7%8B%90%E6%96%B0%E9%97%BB%22%2C%22version_code%22%3A58%2C%22last_update_date%22%3A1388534533049%2C%22app_identifier%22%3A%22com.sohu.newsclient%22%7D%2C%7B%22version_name%22%3A%223.1.3ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.pdager-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.pdager-1.apk%22%2C%22install_date%22%3A1388534552967%2C%22name%22%3A%22%E5%A4%A9%E7%BF%BC%E5%AF%BC%E8%88%AA%22%2C%22version_code%22%3A116%2C%22last_update_date%22%3A1388534552967%2C%22app_identifier%22%3A%22com.pdager%22%7D%2C%7B%22version_name%22%3A%2214032001.1.30.01%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sec.everglades.update-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sec.everglades.update-1.apk%22%2C%22install_date%22%3A1388534527940%2C%22name%22%3A%22SamsungHub+Updater%22%2C%22version_code%22%3A2014032001%2C%22last_update_date%22%3A1388534527940%2C%22app_identifier%22%3A%22com.sec.everglades.update%22%7D%2C%7B%22version_name%22%3A%221.0.4%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcc.linkedme.test-3.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcc.linkedme.test-3.apk%22%2C%22uri_scheme%22%3A%22lkmetest%22%2C%22name%22%3A%22LinkedME%22%2C%22version_code%22%3A4%2C%22app_identifier%22%3A%22cc.linkedme.test%22%7D%2C%7B%22version_name%22%3A%226.5.2%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Ftv.peel.samsung.app-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Ftv.peel.samsung.app-1.apk%22%2C%22install_date%22%3A1388534510468%2C%22uri_scheme%22%3A%22peel%22%2C%22name%22%3A%22Samsung+WatchON%22%2C%22version_code%22%3A6052%2C%22last_update_date%22%3A1388534510468%2C%22app_identifier%22%3A%22tv.peel.samsung.app%22%7D%2C%7B%22version_name%22%3A%225.0.0.3ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.besttone.hall-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.besttone.hall-1.apk%22%2C%22install_date%22%3A1388534482426%2C%22name%22%3A%22%E5%8F%B7%E7%A0%81%E7%99%BE%E4%BA%8B%E9%80%9A%22%2C%22version_code%22%3A5003%2C%22last_update_date%22%3A1388534482426%2C%22app_identifier%22%3A%22com.besttone.hall%22%7D%2C%7B%22version_name%22%3A%223.5.38%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.lenovo.anyshare-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.lenovo.anyshare-1.apk%22%2C%22install_date%22%3A1475203880395%2C%22uri_scheme%22%3A%22com.lenovo.anyshare.openapp.lenovoid%22%2C%22name%22%3A%22%E8%8C%84%E5%AD%90%E5%BF%AB%E4%BC%A0%22%2C%22version_code%22%3A4030538%2C%22last_update_date%22%3A1475203880395%2C%22app_identifier%22%3A%22com.lenovo.anyshare%22%7D%2C%7B%22version_name%22%3A%224.20.2%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.ijinshan.browser_fast-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.ijinshan.browser_fast-1.apk%22%2C%22install_date%22%3A1475918082206%2C%22uri_scheme%22%3A%22tencent1101476446%22%2C%22name%22%3A%22%E7%8C%8E%E8%B1%B9%E6%B5%8F%E8%A7%88%E5%99%A8%22%2C%22version_code%22%3A420002%2C%22last_update_date%22%3A1475918082206%2C%22app_identifier%22%3A%22com.ijinshan.browser_fast%22%7D%2C%7B%22version_name%22%3A%221.0.0ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.chinatelecom.pim-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.chinatelecom.pim-1.apk%22%2C%22install_date%22%3A1388534505406%2C%22name%22%3A%22%E5%8F%B7%E7%B0%BF%E5%8A%A9%E6%89%8B%22%2C%22version_code%22%3A12%2C%22last_update_date%22%3A1388534505406%2C%22app_identifier%22%3A%22com.chinatelecom.pim%22%7D%2C%7B%22version_name%22%3A%224.0.7.48ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.telecom.video-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.telecom.video-1.apk%22%2C%22install_date%22%3A1388534537315%2C%22name%22%3A%22%E5%A4%A9%E7%BF%BC%E8%A7%86%E8%AE%AF%22%2C%22version_code%22%3A2104007048%2C%22last_update_date%22%3A1388534537315%2C%22app_identifier%22%3A%22com.telecom.video%22%7D%2C%7B%22version_name%22%3A%225.2.0.110ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.autonavi.minimap-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.autonavi.minimap-1.apk%22%2C%22install_date%22%3A1388534470823%2C%22uri_scheme%22%3A%22androidamap%22%2C%22name%22%3A%22%E9%AB%98%E5%BE%B7%E5%9C%B0%E5%9B%BE%22%2C%22version_code%22%3A320%2C%22last_update_date%22%3A1388534470823%2C%22app_identifier%22%3A%22com.autonavi.minimap%22%7D%2C%7B%22version_name%22%3A%221.0+build-5517ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Ftelecom.mdesk.widgetprovider-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Ftelecom.mdesk.widgetprovider-1.apk%22%2C%22install_date%22%3A1388534546804%2C%22uri_scheme%22%3A%22app%22%2C%22name%22%3A%22%E5%A4%A9%E7%BF%BC%E7%B2%BE%E5%93%81%E6%8E%A8%E8%8D%90%22%2C%22version_code%22%3A5492%2C%22last_update_date%22%3A1388534546804%2C%22app_identifier%22%3A%22telecom.mdesk.widgetprovider%22%7D%2C%7B%22version_name%22%3A%223.3ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.youku.phone-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.youku.phone-1.apk%22%2C%22install_date%22%3A1388534543737%2C%22uri_scheme%22%3A%22youkuad%22%2C%22name%22%3A%22%E4%BC%98%E9%85%B7%22%2C%22version_code%22%3A38%2C%22last_update_date%22%3A1388534543737%2C%22app_identifier%22%3A%22com.youku.phone%22%7D%2C%7B%22version_name%22%3A%221.0%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.samsung.android.app.storyalbumwidget-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.samsung.android.app.storyalbumwidget-1.apk%22%2C%22version_code%22%3A1%2C%22app_identifier%22%3A%22com.samsung.android.app.storyalbumwidget%22%2C%22name%22%3A%22Story+Album+Widget%22%7D%2C%7B%22version_name%22%3A%223.1.108%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sec.chaton-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.sec.chaton-1.apk%22%2C%22install_date%22%3A1388534489597%2C%22uri_scheme%22%3A%22chaton%22%2C%22name%22%3A%22ChatON%22%2C%22version_code%22%3A303052000%2C%22last_update_date%22%3A1388534489597%2C%22app_identifier%22%3A%22com.sec.chaton%22%7D%2C%7B%22version_name%22%3A%22V5.101.118.696ctch1%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.gwsoft.imusic.controller-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.gwsoft.imusic.controller-1.apk%22%2C%22install_date%22%3A1388534561832%2C%22name%22%3A%22%E7%88%B1%E9%9F%B3%E4%B9%90%22%2C%22version_code%22%3A5101118%2C%22last_update_date%22%3A1388534561832%2C%22app_identifier%22%3A%22com.gwsoft.imusic.controller%22%7D%2C%7B%22version_name%22%3A%2240.0.2214.109%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.android.chrome-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.android.chrome-1.apk%22%2C%22install_date%22%3A1476086112093%2C%22uri_scheme%22%3A%22googlechrome%22%2C%22name%22%3A%22Chrome%22%2C%22version_code%22%3A2214109%2C%22last_update_date%22%3A1476086112093%2C%22app_identifier%22%3A%22com.android.chrome%22%7D%2C%7B%22version_name%22%3A%225.10.0%22%2C%22os%22%3A%22Android%22%2C%22source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.taobao.taobao-1.apk%22%2C%22public_source_dir%22%3A%22%5C%2Fdata%5C%2Fapp%5C%2Fcom.taobao.taobao-1.apk%22%2C%22install_date%22%3A1472377763052%2C%22uri_scheme%22%3A%22taobao%22%2C%22name%22%3A%22%E6%89%8B%E6%9C%BA%E6%B7%98%E5%AE%9D%22%2C%22version_code%22%3A136%2C%22last_update_date%22%3A1472377763052%2C%22app_identifier%22%3A%22com.taobao.taobao%22%7D%5D";
//         open数据
//        String value = "{" +
//                "\"device_id\": \"fbb7d925af42a1e104d49fca6cbe31c8\"," +
//                "\"device_type\": 12," +
//                "\"device_imei\": \"\"," +
//                "\"device_android_id\": \"d0331c464b734549\"," +
//                "\"device_serial_number\": \"GSLDU16629026220\"," +
//                "\"device_mac\": \"\"," +
//                "\"local_ip\": \"192.168.99.115\"," +
//                "\"device_model\": \"FRD-AL10\"," +
//                "\"device_fingerprint_id\": \"9a4a7f3a8986f621c598bd794fe85ce1\"," +
//                "\"identity_id\": \"4006677204697142\"," +
//                "\"is_referrable\": 1," +
//                "\"app_version\": \"1.0.19\"," +
//                "\"app_version_code\": 19," +
//                "\"os_version\": \"7.0\"," +
//                "\"sdk_update\": 1," +
//                "\"os\": \"Android\"," +
//                "\"is_debug\": true," +
//                "\"lat_val\": false," +
//                "\"device_update\": \"0\"," +
//                "\"browser_misc\": \"\"," +
//                "\"android_id_md5\": \"a85dda7e241b9b6beb2321002a6c8895\"," +
//                "\"imei_md5\": \"\"," +
//                "\"timestamp\": 1514182625029," +
//                "\"sdk_version\": \"android1.0.19\"," +
//                "\"imei\": \"\"," +
//                "\"imsi\": \"\"," +
//                "\"android_id\": \"d0331c464b734549\"," +
//                "\"app_name\": \"LinkedME\"," +
//                "\"retry_times\": 0," +
//                "\"linkedme_key\": \"7e289a2484f4368dbafbd1e5c7d06903\"," +
//                "\"sign\": \"d6ef174bbaa61ef9a2490ce46a1a1119\"" +
//                "}";
        // close接口数据压缩
        String value = "{" +
                "\"device_id\": \"fbb7d925af42a1e104d49fca6cbe31c8\"," +
                "\"device_fingerprint_id\": \"9a4a7f3a8986f621c598bd794fe85ce1\"," +
                "\"identity_id\": \"4006677204697142\"," +
                "\"session_id\": \"1514182624586\"," +
                "\"os\": \"Android\"," +
                "\"android_id_md5\": \"a85dda7e241b9b6beb2321002a6c8895\"," +
                "\"imei_md5\": \"23b6134a15da4652a0da11e2a50681fa\"," +
                "\"timestamp\": 1514182903262," +
                "\"sdk_version\": \"android1.0.19\"," +
                "\"imei\": \"869949023126053\"," +
                "\"imsi\": \"460010681403622\"," +
                "\"android_id\": \"d0331c464b734549\"," +
                "\"app_name\": \"LinkedME\"," +
                "\"retry_times\": 0," +
                "\"linkedme_key\": \"7e289a2484f4368dbafbd1e5c7d06903\"," +
                "\"sign\": \"5bf42a1c30c86c0b418cb33e166b2290\"" +
                "                                                                                                       }";
        //字符串压缩为byte数组
        byte[] values = value.getBytes();
        System.out.println("压缩前数据大小：" + values.length/1024f + "K");

        values = compress(values);
        System.out.println("解压后数据大小：" + values.length/1024f + "K");
        //把压缩后的byte数组转为字符串
        String str = new String(values, "iso8859-1");

        //传输字符串
        System.out.println(str);

        //将接受到的字符串转换为byte数组
        values = str.getBytes("iso8859-1");
        //解压缩这个byte数组
        values = decompress(values);
        System.out.println(new String(values, "utf-8"));

    }

    /**
     * 数据解压缩
     */
    public static byte[] decompress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 解压缩
        decompress(bais, baos);
        data = baos.toByteArray();
        baos.flush();
        baos.close();
        bais.close();
        return data;
    }

    /**
     * 数据解压缩
     */
    public static void decompress(InputStream is, OutputStream os)
            throws Exception {
        GZIPInputStream gis = new GZIPInputStream(is);
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = gis.read(data, 0, BUFFER)) != -1) {
            os.write(data, 0, count);
        }
        gis.close();
    }

    public static byte[] compress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 压缩
        compress(bais, baos);
        byte[] output = baos.toByteArray();
        baos.flush();
        baos.close();
        bais.close();
        return output;
    }

    static final int BUFFER = 10240;

    /**
     * 数据压缩
     */
    public static void compress(InputStream is, OutputStream os)
            throws Exception {
        GZIPOutputStream gos = new GZIPOutputStream(os);
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = is.read(data, 0, BUFFER)) != -1) {
            gos.write(data, 0, count);
        }
        gos.finish();
        gos.flush();
        gos.close();
    }
}
