package com.ww.lp.lputils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LinkedME06 on 3/22/18.
 */

public class RegexUtils {

    public static void handleImgPixel(String imgUrl) {
        String regex = "(\\d+)[Xx_+*](\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(imgUrl);
        if (matcher.find() && matcher.groupCount() == 2) {
            int width = Integer.parseInt(matcher.group(1));
            int height = Integer.parseInt(matcher.group(2));
            System.out.println("width = " + width + ", height = " + height);
        } else {
            System.out.println("无匹配项");
        }
    }
}
