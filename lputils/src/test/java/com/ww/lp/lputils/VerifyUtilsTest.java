package com.ww.lp.lputils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by LinkedME06 on 2/23/18.
 */
public class VerifyUtilsTest {
    @Test
    public void isPhoneNumber() throws Exception {
        String phoneNumber = "15510680829";
        Assert.assertEquals(true, VerifyUtils.isPhoneNumber(phoneNumber));
        phoneNumber = "1551068829";
        Assert.assertEquals(false, VerifyUtils.isPhoneNumber(phoneNumber));
    }

}