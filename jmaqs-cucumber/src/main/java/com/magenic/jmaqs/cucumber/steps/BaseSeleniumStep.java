package com.magenic.jmaqs.cucumber.steps;

import com.magenic.jmaqs.base.BaseTest;
import com.magenic.jmaqs.cucumber.BaseCucumberTestNG;
import com.magenic.jmaqs.cucumber.ScenarioContext;
import com.magenic.jmaqs.selenium.BaseSeleniumTest;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import org.openqa.selenium.WebDriver;

public class BaseSeleniumStep  extends  BaseGenericStep {
    public WebDriver getDriver()
    {
        return getTestObject().getWebDriver();
    }

    public SeleniumTestObject getTestObject()
    {
        return ScenarioContext.get(ScenarioContext.JMAQS_HOLDER, BaseSeleniumTest.class).getTestObject();
    }
}



