package com.ww.lp.lputils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by LinkedME06 on 25/08/2017.
 */
public class DateUtilsTest {
    @Test
    public void convertHHmmssToSeconds() throws Exception {
        Assert.assertEquals(DateUtils.convertHHmmssToSeconds("12:12:14"), 43934);
        Assert.assertEquals(DateUtils.convertHHmmssToSeconds("12:14"), 734);
        Assert.assertEquals(DateUtils.convertHHmmssToSeconds("14"), 14);
    }

}