package com.magenic.maqs.appium.UnitTests;

import com.magenic.maqs.appium.baseAppiumTest.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by JasonE on 2/22/2017.
 */
public class AppiumConfigUnitTests {
//    @Test
//    public void testGetMobileDeviceOS() throws Exception {
//        String mobileDeviceOS = AppiumConfig.getMobileDeviceOs();
//        Assert.assertTrue(mobileDeviceOS.equalsIgnoreCase("Android"));
//
//    }

    @Test
    public void testGetMobileDeviceUDID() throws Exception {
        String mobileDeviceUDID = AppiumConfig.getMobileDeviceUdid();
        Assert.assertTrue(mobileDeviceUDID.equalsIgnoreCase("1234567890ACDEF1234687890ABCDEF"));
    }

    @Test
    public void testGetBundleID() throws Exception {
        String bundleID = AppiumConfig.getBundleId();
        Assert.assertTrue(bundleID.equalsIgnoreCase("com.magenic.maqs.appium.tester"));
    }

    @Test
    public void testGetOSVersion() throws Exception {
        String osVersion = AppiumConfig.getOsVersion();
        Assert.assertTrue(osVersion.equalsIgnoreCase("6.0"));
    }

    @Test
    public void testGetDeviceName() throws Exception {
        String deviceName = AppiumConfig.getDeviceName();
        Assert.assertTrue(deviceName.equalsIgnoreCase("Samsung Galaxy S6 Device"));
    }

    /*@Test
    public void testMobileDevice() throws Exception {
        AppiumDriver driver = AppiumConfig.mobileDevice();
        Assert.assertNotNull(driver);
        driver.quit();
    }*/

//    @Test
//    public void testIsUsingMobileBrowser() throws Exception {
//        boolean mobileBrowser = AppiumConfig.isUsingMobileBrowser();
//        Assert.assertFalse(mobileBrowser);
//    }

    @Test
    public void testGetMobileHubUrlString() throws Exception {
        String mobileHubUrl = AppiumConfig.getMobileHubUrlString();
        Assert.assertTrue(mobileHubUrl.equalsIgnoreCase("http://127.0.0.1:4723/wd/hub"));
    }


    @Test
    public void testSetTimeouts() throws Exception {

    }


}
