package com.magenic.maqs.appium.BaseAppiumTest;

import io.appium.java_client.AppiumDriver;

// TODO: Auto-generated Javadoc
/**
 * Created by jasonedstrom on 2/21/17.
 */
public class AppiumWait {

    /** The Constant DEFAULT_TIMEOUT. */
    private static final int DEFAULT_TIMEOUT = 20;
    
    /** The Constant CLEARTIME. */
    private static final int CLEARTIME = 5;
    
    /** The Constant DEFAULT_FLUENT_RETRY_TIME. */
    private static final int DEFAULT_FLUENT_RETRY_TIME = 250;
    
    /** The Constant ONE_THOUSAND. */
    private static final int ONE_THOUSAND = 1000;

    /** The appium driver. */
    private AppiumDriver appiumDriver;
    
    /** The fluent try time. */
    private int fluentTryTime;

    /** The implicit wait timeout. */
    private int implicitWaitTimeout;

    /**
     * Instantiates a new appium wait.
     *
     * @param driver the driver
     */
    public AppiumWait(AppiumDriver driver)
    {
        this(driver, DEFAULT_TIMEOUT);
    }


    /**
     * Instantiates a new appium wait.
     *
     * @param driver the driver
     * @param implicitWaitTimeout the implicit wait timeout
     */
    public AppiumWait(AppiumDriver driver, int implicitWaitTimeout) {
        this(driver, implicitWaitTimeout, DEFAULT_FLUENT_RETRY_TIME);
    }

    /**
     * Instantiates a new appium wait.
     *
     * @param driver the driver
     * @param implicitWaitTimeout the implicit wait timeout
     * @param fluentTryTime the fluent try time
     */
    public AppiumWait(AppiumDriver driver, int implicitWaitTimeout, int fluentTryTime) {
        this.appiumDriver = appiumDriver;
        this.fluentTryTime = fluentTryTime;
        this.implicitWaitTimeout = implicitWaitTimeout;
    }

    /*public MobileElement waitForElement (final By by)
    {
        return this.waitForElement(by, this.implicitWaitTimeout);
    }

    public MobileElement waitForElement(final By by, boolean assertFound)
    {
        MobileElement element = this.waitForElement(by);

        if (element ==  null && assertFound)
        {
            Assert.fail(String.format("The selector with value [%s] couldn't be found." + System.getProperty("line.separator"), by.toString()));
        }

        return element;
    }*/

    /*public MobileElement waitForElement(final By by, int timeOutSeconds)
    {
        return this.waitForElement(by, timeOutSeconds, false);
    }

    public WebElement waitForElement(final By by, int timeOutSeconds, boolean printMissingToConsole) {
        WebElement element;
        try
        {
            this.setImplicitWait(timeOutSeconds);



            WebDriverWait wait = new WebDriverWait(appiumDriver, timeOutSeconds);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

            this.resetImplicitWait();

            return element;
        }
        catch (Exception e)
        {
            if (printMissingToConsole)
            {
                this.printSelectorNotFoundError(by.toString(), e);
            }
        }

        this.resetImplicitWait();
        return null;
    }*/
}
