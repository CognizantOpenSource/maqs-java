package com.magenic.jmaqs.selenium.baseSeleniumTest;

import com.magenic.jmaqs.utilities.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import com.magenic.jmaqs.utilities.logging.MessageType;

    public class EventHandler implements WebDriverEventListener{
    private Logger logger;
    private WebDriver webDriver;

    public EventHandler(WebDriver webDriver, Logger logger) {
        this.webDriver = webDriver;
        this.logger = logger;
    }

    public void beforeChangeValueOf(WebElement e, WebDriver arg1) {
        String element = e.getAttribute("value");
        this.logger.logMessage(MessageType.INFORMATION, "Element before value changed: {0}", element);
    }

    public void afterChangeValueOf(WebElement e, WebDriver arg1) {
        String element = e.getAttribute("value");
        this.logger.logMessage(MessageType.INFORMATION, "Element after value changed: {0}", element);
    }

    public void beforeClickOn(WebElement e, WebDriver webDriver) {
        try {
            this.logger.logVerbose("Element clicked: {0} Text:{1} Location: X:{2} Y:{3}", e.toString(), e.getText(), e.getLocation().x, e.getLocation().y);
        }
        catch (Exception exc) {
            this.logger.logVerbose("Element clicked");
        }
    }

    public void afterClickOn(WebElement e, WebDriver arg1) {
        try {
            this.logger.logVerbose("Element clicked: {0} Text:{1} Location: X:{2} Y:{3}", e.toString(), e.getText(), e.getLocation().x, e.getLocation().y);
        }
        catch (Exception exc)
        {
            this.logger.logVerbose("Element clicked");
        }
    }

    @Override
    public void beforeChangeValueOf(WebElement e, WebDriver driver, CharSequence[] keysToSend) {
        String element = e.getAttribute("value");
        this.logger.logMessage(MessageType.INFORMATION, "Element value changed: {0}", element);
    }

    @Override
    public void afterChangeValueOf(WebElement e, WebDriver driver, CharSequence[] keysToSend) {
        String element = e.getAttribute("value");
        this.logger.logMessage(MessageType.INFORMATION, "Element value changed: {0}", element);
    }

    public void beforeFindBy(By arg0, WebElement e, WebDriver arg2) {
        this.logger.logVerbose("Just before Finding element: {0}", e.toString());
    }

    public void afterFindBy(By by, WebElement e, WebDriver arg2) {
        this.logger.logVerbose("Find happened on " + e.toString()
                + " Using method " + by.toString());
    }

    public void beforeNavigateBack(WebDriver e) {
        this.logger.logMessage(MessageType.INFORMATION, "Navigating back: {0}", e.getCurrentUrl());
    }

    public void afterNavigateBack(WebDriver e) {
        this.logger.logMessage(MessageType.INFORMATION, "Navigated back: {0}", e.getCurrentUrl());
    }

    public void beforeNavigateForward(WebDriver e) {
        this.logger.logMessage(MessageType.INFORMATION, "Just before beforeNavigateForward: {0}", e.getCurrentUrl());
    }

    public void afterNavigateForward(WebDriver e) {
        this.logger.logMessage(MessageType.INFORMATION, "Inside the afterNavigateForward to: {0}", e.getCurrentUrl());
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    public void beforeNavigateTo(String e, WebDriver arg1) {
        this.logger.logMessage(MessageType.INFORMATION, "Just before beforeNavigateTo to: {0}", e);
    }

    public void afterNavigateTo(String e, WebDriver arg1) {
        this.logger.logMessage(MessageType.INFORMATION, "Inside the afterNavigateTo to: {0}", e);
    }

    public void beforeScript(String e, WebDriver arg1) {
        this.logger.logMessage(MessageType.INFORMATION, "Just before beforeScript: {0}", e);
    }

    public void afterScript(String e, WebDriver arg1) {
        this.logger.logMessage(MessageType.INFORMATION, "Inside the afterScript to, Script is: {0}", e);
    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    public void onException(Throwable e, WebDriver arg1) {
        // First chance handler catches these when it is a real error - These are typically retry loops
        this.logger.logMessage(MessageType.VERBOSE, "Exception occured at {0}", e.getMessage());
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {

    }
}