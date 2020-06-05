package com.magenic.jmaqs.cucumber;

import com.magenic.jmaqs.base.BaseGenericTest;
import com.magenic.jmaqs.base.BaseTest;
import com.magenic.jmaqs.base.BaseTestObject;
import com.magenic.jmaqs.cucumber.BaseCucumberTestNG;
import com.magenic.jmaqs.selenium.BaseSeleniumTest;
import org.testng.annotations.DataProvider;

public class BaseGenericCucumber extends BaseCucumberTestNG {

    @Override
    public BaseTest createSpecificBaseTest() {
        return new BaseGenericTest();
    }

    /**
     * Get the base test object
     *
     * @return The base test object
     */
    public BaseTestObject getTestObject()
    {
        return getBaseTest().getTestObject();
    }

}



