package com.magenic.jmaqs.selenium;

import java.util.List;
import java.util.function.Supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.GenericWait;
import com.magenic.jmaqs.utilities.helper.TimeoutException;
import com.magenic.jmaqs.utilities.helper.functionalinterfaces.Action;
import com.magenic.jmaqs.utilities.logging.MessageType;

/**
 * Driver for dynamically finding and interacting with elements
 */
public class LazyElement implements WebElement
{
	/** A user friendly name, for logging purposes */
	private final String userFriendlyName;

	/** The parent lazy element */
	private LazyElement parent;

	/** The 'by' selector for the element */
	private By by; 

	/** The test object for the element */
	private SeleniumTestObject testObject; 

	/** Cached copy of the element or null if we haven't already found the element */
	private WebElement cachedElement;

	/**
	 * Initializes a new instance of the {@link #LazyElement} class
	 * @param testObject The selenium test object
	 * @param locator The by locator to search on
	 * @param userFriendlyName The user friendly name of the lazy element
	 */
	public LazyElement(SeleniumTestObject testObject, By locator, String userFriendlyName)
	{
		this.testObject = testObject;
		this.by = locator;
		this.userFriendlyName = userFriendlyName;
	}

	/**
	 * Initializes a new instance of the Lazy Element class
	 * @param parent The parent lazy element
	 * @param locator The by locator to search on
	 * @param userFriendlyName The user friendly name of the lazy element
	 */
	public LazyElement(LazyElement parent, By locator, String userFriendlyName)
	{
		this(parent.getTestObject(), locator, userFriendlyName);
		this.parent = parent;
	}

	/**
	 * Gets the by selector
	 * @return the by
	 */
	public By getBy() {
		return this.by;
	}

	/**
	 * Gets the cached element
	 * @return the cachedElement
	 */
	public WebElement getCachedElement() {
		return this.cachedElement;
	}

	/**
	 * Sets the cached Element
	 * @param cachedElementFactory the cachedElement function to set
	 * the cached element
	 */
	private void setCachedElement(Supplier<WebElement> cachedElementFactory) {
		this.cachedElement = cachedElementFactory.get();
	}

	/**
	 * Gets the user friendly name
	 * @return the userFriendlyName
	 */
	public String getUserFriendlyName() {
		return this.userFriendlyName;
	}

	/**
	 * Gets the parent of the lazy element
	 * @return the parent
	 */
	public LazyElement getParent() {
		return this.parent;
	}

	/**
	 * Gets the enabled value of the lazy element
	 * @return the enabled value
	 */
	public boolean getIsEnabled() {
		return this.isEnabled();
	}

	/**
	 * Gets the test object
	 * @return the test object
	 */
	public SeleniumTestObject getTestObject() {
		return this.testObject;
	}


	/**
	 * Gets the is selected value
	 * @return The is selected value
	 */
	public boolean getIsSelected() {
		return this.isSelected();
	}

	/**
	 * Gets the is displayed value
	 * @return The is displayed value
	 */
	public boolean getIsDisplayed() {
		return this.isDisplayed();
	}

	/**
	 * Gets the tag name of the lazy element
	 */
	public String getTagName() {
		return this.tryGenericWaitFor(
				() -> this.tryGetElement(
						() -> this.getTheExistingElement()).getTagName());
	}

	/**
	 * Gets the lazy element's text
	 * @return The element text
	 */
	public String getText() {
		return this.tryGenericWaitFor(
				() -> this.tryGetElement(
					    () -> this.getTheExistingElement())).getText();
	}

	/**
	 * Gets the lazy element's location
	 * @return the location as a Point
	 */
	public Point getLocation() {
		return this.tryGenericWaitFor(
				() -> this.tryGetElement(
					    () -> this.getTheExistingElement())).getLocation();
	}

	/**
	 * Gets the lazy element's size
	 */
	public Dimension getSize() {
		return this.tryGenericWaitFor(
				() -> this.tryGetElement(
					    () -> this.getTheExistingElement())).getSize();
	}

	/**
	 * Click the lazy element 
	 */
	public void click() {
		this.tryGenericWaitFor(() -> {
			WebElement element = this.tryGetElement(() -> this.getTheClickableElement());
			boolean didClick = this.tryExecuteEvent(() -> element.click(), "Click");

			return didClick;
		});
	}

	/**
	 * Send Secret keys with no logging
	 * @param keys the secret keys
	 * @throws Exception If error occurs while sending keys
	 */
	public void sendSecretKeys(String keys) throws Exception {
		WebElement element = this.getElement(() -> this.getTheVisibleElement());

		try {
			this.getTestObject().getLog().suspendLogging();
			this.executeEvent(() -> element.sendKeys(keys), "SendKeys");
			this.getTestObject().getLog().continueLogging();
		}
		catch (Exception e) {
			this.getTestObject().getLog().continueLogging();
			this.getTestObject().getLog().logMessage(
					MessageType.ERROR, 
					"Exception durring sending secret keys: " + e.getMessage() + System.lineSeparator() + e.getStackTrace());

			throw e;
		}
	}

	/**
	 * Clear the lazy element 
	 */
	public void clear() {
		this.tryGenericWaitFor(() -> {
			WebElement element = this.tryGetElement(() -> this.getTheVisibleElement());
			boolean didClear = this.tryExecuteEvent(() -> element.clear(), "Clear");

			return didClear;
		});
	}

	/**
	 * Submit the lazy element
	 */
	public void submit() {
		this.tryGenericWaitFor(() -> {
			WebElement element = this.tryGetElement(() -> this.getTheExistingElement());
			boolean didSubmit = this.tryExecuteEvent(() -> element.submit(), "Submit");

			return didSubmit;
		});
	}

	/**
	 * Gets the value for the given attribute
	 * @param attributeName The name of the attribute
	 * @return The attribute
	 */
	public String getAttribute(String attributeName) {
		return this.tryGenericWaitFor(
				() -> this.tryGetElement(
					    () -> this.getTheExistingElement())).getAttribute(attributeName);
	}

	/**
	 * Gets the current value of an element - Useful for get input box text
	 * @return The current value of the element
	 */
	public String getValue() {
		return this.tryGenericWaitFor(
				() -> this.tryGetElement(
					    () -> this.getTheVisibleElement())).getAttribute("value");
	}

	/**
	 * Gets the CSS value for the given attribute
	 * @param propertyName The property name
	 * @return the css value for the property
	 */
	@Override
	public String getCssValue(String propertyName) {
		return this.tryGenericWaitFor(
				() -> this.tryGetElement(
					    () -> this.getTheExistingElement())).getCssValue(propertyName);
	}

	/**
	 * Wait for and get the visible web element
	 * @return The visible web element
	 */
	public WebElement getTheVisibleElement()
	{
		this.setCachedElement((this.parent == null) ? 
				() -> UIWaitFactory.getWaitDriver(this.getTestObject().getWebDriver()).waitForVisibleElement(this.getBy()) :
					() -> UIWaitFactory.getWaitDriver(this.parent.getTheExistingElement()).waitForVisibleElement(this.getBy()));

		return this.getCachedElement();
	}

	/**
	 * Wait for and get the click-able web element
	 * @return The click-able web element
	 */
	public WebElement getTheClickableElement()
	{
		this.setCachedElement((this.parent == null) ? 
				() -> UIWaitFactory.getWaitDriver(this.getTestObject().getWebDriver()).waitForClickableElement(this.getBy()) :
				() -> UIWaitFactory.getWaitDriver(this.parent.getTheExistingElement()).waitForClickableElement(this.getBy()));

		return this.getCachedElement();
	}

	/**
	 * Waits for and gets the existing web element
	 * @return The existing web element
	 */
	public WebElement getTheExistingElement()
	{
		this.setCachedElement((this.parent == null) ? 
				() -> UIWaitFactory.getWaitDriver(this.getTestObject().getWebDriver()).waitForPresentElement(this.getBy()) :
				() -> UIWaitFactory.getWaitDriver(this.parent.getTheExistingElement()).waitForPresentElement(this.getBy()));

		return this.getCachedElement();
	}

	//  This method doesn't seem to exist in Java. There is a C# equivalent but it looks like an extension
	//  method. Investigate further before removing
	//
	//	/**
	//	 * Gets the value of a JavaScript property of this element
	//	 * @param propertyName the property name
	//	 * @return the JavaScript property of the element
	//	 */
	//	public String getProperty(String propertyName) {
	//		return this.getTheExistingElement().getProperty(propertyName);
	//	}

	/**
	 * Finds the first {@link org.openqa.selenium.WebElement WebElement} using the given method.
	 * @param by The locating mechanism to use
	 * @return the {@link org.openqa.selenium.WebElement WebElement} being found
	 */
	public WebElement findElement(By by) {
		return this.tryGetElement(() -> getTheExistingElement()).findElement(by);
	}

	/**
	 * Finds all {@link org.openqa.selenium.WebElement WebElement} within 
	 * the current context using the given mechanism.
	 * @param by the locating mechanism to use
	 * @return the List of {@link org.openqa.selenium.WebElement WebElement} being found
	 */
	public List<WebElement> findElements(By by) {
		return this.tryGetElement(() -> getTheExistingElement()).findElements(by);
	}

	/**
	 * Returns a string that represents the current object
	 * @return the lazy element string 
	 */
	@Override
	public String toString()
	{
		String temp = "";

		// Check if LazyElement has a parent
		// If so, prefix parent's toString()
		if (this.parent != null) {
			temp += this.parent.toString();
		}

		return temp + this.by.toString() + this.userFriendlyName;
	}

	/**
	 * Get a web element
	 * @param getElement The function that gets the element
	 * @return The web element
	 * @throws Exception If the element can not be found
	 */
	private WebElement getElement(Supplier<WebElement> getElement) throws Exception {
		if (this.getCachedElement() != null) {
			try {
				return this.getCachedElement();
			}
			catch (Exception e) {
				this.getTestObject().getLog().logMessage(MessageType.VERBOSE, "Refinding element because: " + e.getMessage());
			}
		}

		try {
			this.getTestObject().getLog().logMessage(MessageType.VERBOSE, "Performing lazy driver find on: " + this.getBy());
			this.setCachedElement(getElement);
			return this.getCachedElement();
		}
		catch (Exception e) {
			StringBuilder messageBuilder = new StringBuilder();

			messageBuilder.append("Failed to find: " + this.userFriendlyName + System.lineSeparator());
			messageBuilder.append("Locator: " + this.getBy() + System.lineSeparator());
			messageBuilder.append("Because: " + e.getMessage() + System.lineSeparator());

			throw new Exception(messageBuilder.toString(), e);
		}
	}

	/**
	 * Execute an element action
	 * @param elementAction the element action
	 * @param caller Text to identify the caller function
	 * @throws Exception If the elementAction fails to execute
	 */
	private void executeEvent(Action elementAction, String caller) throws Exception
	{
		try {
			this.getTestObject().getLog().logMessage(MessageType.VERBOSE, "Performing lazy driver action: " + caller);
			elementAction.invoke();
		}
		catch (Exception e) {
			StringBuilder messageBuilder = new StringBuilder();

			messageBuilder.append("Failed to " + caller + ": " + this.userFriendlyName + System.lineSeparator());
			messageBuilder.append("Locator: " + this.getBy() + System.lineSeparator());
			messageBuilder.append("Because: " + e.getMessage() + System.lineSeparator());

			throw new Exception(messageBuilder.toString(), e);
		}
	}

	/**
	 * Gets the screenshot as the target type
	 * @param target The target output type
	 * @return The type to get the screenshot as
	 */
	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return this.tryGenericWaitFor(
				() -> this.tryGetElement(
						() -> this.getTheExistingElement())).getScreenshotAs(target);
	}

	/**
	 * Sends the keys to the element
	 * @param keysToSend The keys being sent to the element
	 */
	@Override
	public void sendKeys(CharSequence... keysToSend) {
		this.tryGenericWaitFor(() -> {
			WebElement element = this.getTheVisibleElement();
			boolean didSendKeys = this.tryExecuteEvent(() -> element.sendKeys(keysToSend), "SendKeys");

			return didSendKeys;
		});
	}

	/**
	 * If the Element is selected
	 * @return If the element is selected
	 */
	@Override
	public boolean isSelected() {
		return this.tryGenericWaitFor(
				() -> this.tryGetElement(
					    () -> this.getTheExistingElement())).isSelected();
	}

	/**
	 * If the element is enabled
	 * @return If the element is enabled
	 */
	@Override
	public boolean isEnabled() {
		return this.tryGenericWaitFor(
				() -> this.tryGetElement(
					    () -> this.getTheExistingElement())).isEnabled();
	}

	/**
	 * If the element is displayed
	 * @return If the element is displayed
	 */
	@Override
	public boolean isDisplayed() {
		return this.tryGenericWaitFor(
				() -> this.tryGetElement(
					    () -> this.getTheExistingElement())).isDisplayed();
	}

	/**
	 * Gets the rectangle value that highlights the lazy element location
	 */
	@Override
	public Rectangle getRect() {
		return new Rectangle(this.getLocation(), this.getSize());
	}

	/**
	 * Trys to execute the generic wait and return it's value
	 * @param func the function to wait for true
	 * @return The value being waited for
	 */
	private <T> T tryGenericWaitFor(Supplier<T> func) {
		T value = null;

		try {
			value = GenericWait.waitFor(func);
		} 
		catch (InterruptedException | TimeoutException e) {
			this.getTestObject().getLog().logMessage(
					MessageType.ERROR, 
					"Encountered error while waiting for Generic Wait to complete. Either an interruption or a Timeout occurred" +
							"Caused by: " + e.getMessage() + System.lineSeparator() +
							"With stack trace: " + e.getStackTrace() + System.lineSeparator());
		}

		return value;
	}

	/**
	 * Trys to execute the event and returns if 
	 * execution was able to succeed
	 * @param elementFactory The action that will be executed
	 * @param caller The caller for logging purposes
	 * @return If the execution was successful
	 */
	private boolean tryExecuteEvent(Action action, String caller) {
		boolean didExecute = false;

		try {
			this.executeEvent(action, caller);
			didExecute = true;
		} 
		catch (Exception e) {
			didExecute = false;
		}

		return didExecute;
	}

	/**
	 * Trys to the get web element using the supplier function
	 * @param sup The function to get the element
	 * @return The WebElement
	 */
	private WebElement tryGetElement(Supplier<WebElement> sup) {
		WebElement webElement = null;

		try {
			webElement = this.getElement(sup);
		} 
		catch (Exception e) {
			return webElement;
		}

		return webElement;
	}
}
