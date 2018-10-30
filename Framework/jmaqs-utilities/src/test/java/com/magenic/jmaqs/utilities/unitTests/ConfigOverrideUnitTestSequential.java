package com.magenic.jmaqs.utilities.unitTests;

import com.magenic.jmaqs.utilities.helper.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Configuration override unit test class.
 */
public class ConfigOverrideUnitTestSequential {
    /**
     * Test resetting the override config.
     */
    @Test
    public void resetOverridesTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("BrowserOverride", "CHROME");
        newValueMap.put("TimeoutOverride", "13333333");

        // test that values are overrode except those not added to the override dictionary
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(Config.getGeneralValue("BrowserOverride"), "CHROME");
        Assert.assertEquals(Config.getGeneralValue("TimeoutOverride"), "13333333");
        Assert.assertEquals(Config.getGeneralValue("TestKey"), "testValue");

        // reset the override config and make sure default values exist
        Config.resetOverrideConfig();
        Assert.assertEquals(Config.getGeneralValue("BrowserOverride"), "FireFox");
        Assert.assertEquals(Config.getGeneralValue("TimeoutOverride"), "13.52");
    }
}
