package com.ww.lp.lputils;

import org.junit.Test;

/**
 * Created by LinkedME06 on 3/22/18.
 */
public class RegexUtilsTest {
    @Test
    public void handleImgPixel() throws Exception {
//        String imgUrl = "http://img.lkme.cc/1502/1200X628.jpg";
//        String imgUrl = "http://img.lkme.cc/1502/1200_628.jpg";
//        String imgUrl = "http://img.lkme.cc/1502/1200x628";
//        String imgUrl = "http://img.lkme.cc/1502/1200X628_1";
//        String imgUrl = "http://img.lkme.cc/1502/1200+628";
//        String imgUrl = "http://img.lkme.cc/1502/1200*628";
        String imgUrl = "http://img.lkme.cc/1502/1200_628";
        RegexUtils.handleImgPixel(imgUrl);
    }

}