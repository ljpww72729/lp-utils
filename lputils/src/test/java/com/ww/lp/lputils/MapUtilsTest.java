package com.ww.lp.lputils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by LinkedME06 on 01/04/2017.
 */
public class MapUtilsTest {
    @Test
    public void convertArrayMapToString() throws Exception {
        Assert.assertEquals("k1=v1&k2=v2&k3=v3", MapUtils.convertMapToString(DataSource.getTestArrayMap(), "&"));
    }

    @Test
    public void convertMapToString() throws Exception {
        Assert.assertEquals("k1=v1&k2=v2&k3=v3", MapUtils.convertMapToString(DataSource.getTestArrayMap(), "&"));
    }

}