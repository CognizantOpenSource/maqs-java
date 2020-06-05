package com.magenic.jmaqs.cucumber;

import com.magenic.jmaqs.base.BaseTest;
import com.magenic.jmaqs.selenium.BaseSeleniumTest;
import com.magenic.jmaqs.selenium.SeleniumTestObject;

public class BaseSeleniumCucumber extends BaseCucumberTestNG {

    @Override
    public BaseTest createSpecificBaseTest() {
        return new BaseSeleniumTest();
    }

    /**
     * Get the Selenium test object
     *
     * @return The Selenium test object
     */
    public SeleniumTestObject getTestObject()
    {
        return ((BaseSeleniumTest)getBaseTest()).getTestObject();
    }
}



