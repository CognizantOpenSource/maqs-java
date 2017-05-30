package com.magenic.maqs.appium.BaseAppiumTest;

import com.magenic.jmaqs.baseTest.BaseGenericTest;
import com.magenic.jmaqs.selenium.baseSeleniumTest.SeleniumConfig;
import com.magenic.jmaqs.selenium.baseSeleniumTest.SeleniumTestObject;
import com.magenic.jmaqs.selenium.baseSeleniumTest.SeleniumWait;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.MessageType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;


/**
 * Base Appium Test Class.
 *
 * @author JasonE
 */
public abstract class BaseAppiumTest extends BaseGenericTest {

    /**
     * Initialize a new instance of the BaseAppiumTest class.
     */
    public BaseAppiumTest()
    {

    }

    /** Thread local storage of AppiumTestObject. */
    private ThreadLocal<AppiumTestObject> appiumTestObject = new ThreadLocal<AppiumTestObject>();

    
    /**
	 * Gets the appium driver.
	 *
	 * @return the appium driver
	 */
    public AppiumDriver getAppiumDriver()
    {
        return this.appiumTestObject.get().getAppiumDriver();
    }

    /**
	 * Gets the appium wait.
	 *
	 * @return the appium wait
	 */
    public SeleniumWait getAppiumWait()
    {
        return this.appiumTestObject.get().getAppiumWait();
    }

    /**
	 * Gets the appium test object.
	 *
	 * @return the appium test object
	 */
    public AppiumTestObject getAppiumTestObject()
    {
        return this.appiumTestObject.get();
    }

    /* (non-Javadoc)
     * @see magenic.maqs.utilities.BaseTest.BaseGenericTest#postSetupLogging()
     */
    protected void postSetupLogging()
    {
        try
        {
            //TODO: Compare to C# version
            /*if (AppiumConfig.getMobileDeviceOS().equalsIgnoreCase("Remote"))
            {
                this.getLogger().logMessage(MessageType.INFORMATION, "Remote driver: %s", AppiumConfig.getRemoteMobileDevice());
            }
            else
            {*/
            AppiumDriver driver = AppiumConfig.mobileDevice();
            SeleniumWait wait = new SeleniumWait(driver);

            appiumTestObject
                    .set(new AppiumTestObject(driver, wait, this.getFullyQualifiedTestClassName(), this.getLogger()));
                this.getLogger().logMessage(MessageType.INFORMATION, "Loaded driver: %s", AppiumConfig.getMobileDeviceOS());
            //}
        }
        catch(Exception e)
        {
            this.getLogger().logMessage(MessageType.ERROR, "Failed to start driver because: %s", e.getMessage());
            System.out.println(StringProcessor.safeFormatter("Browser type %s is not supported", e.getMessage()));
        }
    }

    /* (non-Javadoc)
     * @see magenic.maqs.utilities.BaseTest.BaseGenericTest#beforeLoggingTeardown(org.testng.ITestResult)
     */
    protected void beforeLoggingTeardown(ITestResult resultType)
    {
        try {

        }
        catch (Exception e)

        {
            this.tryToLog(MessageType.WARNING, "Failed to get screen shot because: %s", e.getMessage());
        }
        this.tryToLog(MessageType.INFORMATION, "Close");

        try {
            this.appiumTestObject.get().appiumDriver.quit();
        }catch(Exception e)
        {
            this.tryToLog(MessageType.WARNING, "Failed to quit because: %s", e.getMessage());
        }
    }
}
