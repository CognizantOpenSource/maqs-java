package com.magenic.jmaqs.selenium;

import java.util.List;
import java.util.function.Supplier;

import com.magenic.jmaqs.selenium.factories.FluentWaitFactory;
import com.magenic.jmaqs.utilities.helper.exceptions.ExecutionFailedException;
import org.openqa.selenium.*;

import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.GenericWait;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import com.magenic.jmaqs.utilities.helper.functionalinterfaces.Action;
import com.magenic.jmaqs.utilities.logging.MessageType;
import org.openqa.selenium.support.ui.FluentWait;

/**
 * Driver for dynamically finding and interacting with elements
 */
public class LazyElement {

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
	public LazyElement(SeleniumTestObject testObject, By locator, String userFriendlyName) {
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
	public LazyElement(LazyElement parent, By locator, String userFriendlyName) {
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
	 * Gets the test object
	 * @return the test object
	 */
	public SeleniumTestObject getTestObject() {
		return this.testObject;
	}

	/**
	 * Gets the tag name of the lazy element
	 */
	public String getTagName() throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(this.getElement(this::getTheExistingElement)::getTagName);
	}

	/**
	 * Gets the lazy element's text
	 * @return The element text
	 */
	public String getText() throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(this.getElement(this::getTheExistingElement)::getText);
	}

	/**
	 * Gets the lazy element's location
	 * @return the location as a Point
	 */
	public Point getLocation() throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(this.getElement(this::getTheExistingElement)::getLocation);
	}

	/**
	 * Gets the lazy element's size
	 */
	public Dimension getSize() throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(this.getElement(this::getTheExistingElement)::getSize);
	}

	/**
	 * Click the lazy element 
	 */
	public void click() throws TimeoutException, InterruptedException, ExecutionFailedException {
		WebElement element = GenericWait.waitFor(() -> this.getElement(this::getTheClickableElement));
        this.executeEvent(element::click, "Click");
	}

	/**
	 * Send Secret keys with no logging
	 * @param keys the secret keys
	 * @throws ExecutionFailedException If error occurs while sending keys
	 */
	public void sendSecretKeys(String keys) throws ExecutionFailedException, TimeoutException, InterruptedException {
	    this.getTestObject().getLog().logMessage(MessageType.VERBOSE, "Send secret keys to '%s'", this.getUserFriendlyName());
		WebElement element = GenericWait.waitFor(() -> this.getElement(this::getTheVisibleElement));

		try {
			this.getTestObject().getLog().suspendLogging();
			this.executeEvent(() -> element.sendKeys(keys), "SendKeys");
			this.getTestObject().getLog().continueLogging();
		}
		catch (ExecutionFailedException e) {
			this.getTestObject().getLog().continueLogging();
			this.getTestObject().getLog().logMessage(
					MessageType.ERROR,
					"Exception during sending secret keys: " + e + System.lineSeparator());

			throw e;
		}
	}

	/**
	 * Clear the lazy element 
	 */
	public void clear() throws TimeoutException, InterruptedException, ExecutionFailedException {
		WebElement element = GenericWait.waitFor(() -> this.getElement(this::getTheVisibleElement));
		this.executeEvent(element::clear, "Clear");
	}

	/**
	 * Submit the lazy element
	 */
	public void submit() throws TimeoutException, InterruptedException, ExecutionFailedException {
		WebElement element = GenericWait.waitFor(() -> this.getElement(this::getTheExistingElement));
        this.executeEvent(element::submit, "Submit");
	}

	/**
	 * Gets the value for the given attribute
	 * @param attributeName The name of the attribute
	 * @return The attribute
	 */
	public String getAttribute(String attributeName) throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(() -> this.getElement(this::getTheExistingElement).getAttribute(attributeName));
	}

	/**
	 * Gets the current value of an element - Useful for get input box text
	 * @return The current value of the element
	 */
	public String getValue() throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(() -> this.getElement(this::getTheVisibleElement).getAttribute("value"));
	}

	/**
	 * Gets the CSS value for the given attribute
	 * @param propertyName The property name
	 * @return the css value for the property
	 */
	public String getCssValue(String propertyName) throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(() -> this.getElement(this::getTheExistingElement).getCssValue(propertyName));
	}

	/**
	 * Wait for and get the visible web element
	 * @return The visible web element
	 */
	public WebElement getTheVisibleElement() {
	    Supplier<WebElement> elementSupplier;

		if (this.parent == null) {
            elementSupplier = () -> {
                UIWait waitDriver = UIWaitFactory.getWaitDriver(this.getTestObject().getWebDriver());
                return waitDriver.waitForVisibleElement(this.getBy());
            };
        }
		else {
            elementSupplier = () -> {
                WebElement parentElement = this.parent.getTheExistingElement();
                FluentWait<WebElement> fluentWait = FluentWaitFactory.getNewElementFluentWait(parentElement);
                return fluentWait.until(e -> e.findElement(this.getBy()));
            };
        }

		this.setCachedElement(elementSupplier);
		return this.getCachedElement();
	}

	/**
	 * Wait for and get the click-able web element
	 * @return The click-able web element
	 */
	public WebElement getTheClickableElement() {

        Supplier<WebElement> elementSupplier;

        if (this.parent == null) {
            elementSupplier = () -> {
                UIWait waitDriver = UIWaitFactory.getWaitDriver(this.getTestObject().getWebDriver());
                return waitDriver.waitForClickableElement(this.getBy());
            };
        }
        else {
            elementSupplier = () -> {
                WebElement parentElement = this.getParent().getTheExistingElement();
                FluentWait<WebElement> fluentWait = FluentWaitFactory.getNewElementFluentWait(parentElement);
                return fluentWait.until(e -> e.findElement(this.getBy()));
            };
        }

		this.setCachedElement(elementSupplier);
		return this.getCachedElement();
	}

	/**
	 * Waits for and gets the existing web element
	 * @return The existing web element
	 */
	public WebElement getTheExistingElement() {

		Supplier<WebElement> elementSupplier;

		if (this.parent == null) {
			elementSupplier = () -> {
				UIWait waitDriver = UIWaitFactory.getWaitDriver(this.getTestObject().getWebDriver());
				return waitDriver.waitForPresentElement(this.getBy());
			};
		}
		else {
			elementSupplier = () -> {
			    WebElement parentElement = this.getParent().getTheExistingElement();
                FluentWait<WebElement> fluentWait = FluentWaitFactory.getNewElementFluentWait(parentElement);
			    return fluentWait.until(e -> e.findElement(this.getBy()));
			};
		}

		this.setCachedElement(elementSupplier);
		return this.getCachedElement();
	}

	/**
	 * Finds the first {@link org.openqa.selenium.WebElement WebElement} using the given method.
	 * @param by The locating mechanism to use
	 * @return the {@link org.openqa.selenium.WebElement WebElement} being found
	 */
	public WebElement findElement(By by) throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(() -> this.getElement(this::getTheExistingElement).findElement(by));
	}

	/**
	 * Finds all {@link org.openqa.selenium.WebElement WebElement} within 
	 * the current context using the given mechanism.
	 * @param by the locating mechanism to use
	 * @return the List of {@link org.openqa.selenium.WebElement WebElement} being found
	 */
	public List<WebElement> findElements(By by) throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(() -> this.getElement(this::getTheExistingElement).findElements(by));
	}

	/**
	 * Gets the screenshot as the target type
	 * @param target The target output type
	 * @return The type to get the screenshot as
	 */
	public <X> X getScreenshotAs(OutputType<X> target) throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(() -> this.getElement(this::getTheExistingElement).getScreenshotAs(target));
	}

	/**
	 * Sends the keys to the element
	 * @param keysToSend The keys being sent to the element
	 */
	public void sendKeys(CharSequence... keysToSend) throws TimeoutException, InterruptedException, ExecutionFailedException {
	    StringBuilder keyBuilder = new StringBuilder(keysToSend.length);

	    for (CharSequence cs : keysToSend) {
	        keyBuilder.append(cs);
        }

        this.getTestObject().getLog().logMessage(MessageType.VERBOSE, "Send keys '%s' to '%s'", keyBuilder.toString(), this.getUserFriendlyName());

        WebElement element = GenericWait.waitFor(() -> this.getElement(this::getTheVisibleElement));
        this.executeEvent(() -> element.sendKeys(keysToSend), "SendKeys");
	}

	/**
	 * If the Element is selected
	 * @return If the element is selected
	 */
	public boolean isSelected() throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(this.getElement(this::getTheExistingElement)::isSelected);
	}

	/**
	 * If the element is enabled
	 * @return If the element is enabled
	 */
	public boolean isEnabled() throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(this.getElement(this::getTheExistingElement)::isEnabled);
	}

	/**
	 * If the element is displayed
	 * @return If the element is displayed
	 */
	public boolean isDisplayed() throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(this.getElement(this::getTheExistingElement)::isDisplayed);
	}

	/**
	 * Gets the rectangle value that highlights the lazy element location
	 */
	public Rectangle getRect() throws TimeoutException, InterruptedException {
		return new Rectangle(this.getLocation(), this.getSize());
	}

	/**
	 * Returns a string that represents the current object
	 * @return the lazy element string 
	 */
	@Override
	public String toString() {
		String temp = "";

		// Check if LazyElement has a parent
		// If so, prefix parent's toString()
		if (this.parent != null) {
			temp += this.parent.toString();
		}

		return temp + this.by.toString() + this.userFriendlyName;
	}

	/**
	 * Gets a web element using the provided factory if the cached element is
     * null or if the cached element is stale
	 * @param getElement The function that gets the element
	 * @return The web element
	 * @throws NoSuchElementException If the element can not be found
	 */
	private WebElement getElement(Supplier<WebElement> getElement) {
		if (this.getCachedElement() != null) {
			try {
				this.getCachedElement().isDisplayed();
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
		catch (NoSuchElementException nsee) {
			StringBuilder messageBuilder = new StringBuilder();

			messageBuilder.append("Failed to find: " + this.userFriendlyName + System.lineSeparator());
			messageBuilder.append("Locator: " + this.getBy() + System.lineSeparator());
			messageBuilder.append("Because: " + nsee.getMessage() + System.lineSeparator());

			throw new NoSuchElementException(messageBuilder.toString(), nsee);
		}
	}

	/**
	 * Execute an element action
	 * @param elementAction the element action
	 * @param caller Text to identify the caller function
	 * @throws Exception If the elementAction fails to execute
	 */
	private void executeEvent(Action elementAction, String caller) throws ExecutionFailedException {
		try {
			this.getTestObject().getLog().logMessage(MessageType.VERBOSE, "Performing lazy driver action: " + caller);
			elementAction.invoke();
		}
		catch (Exception e) {
			StringBuilder messageBuilder = new StringBuilder();

			messageBuilder.append("Failed to " + caller + ": " + this.userFriendlyName + System.lineSeparator());
			messageBuilder.append("Locator: " + this.getBy() + System.lineSeparator());
			messageBuilder.append("Because: " + e.getMessage() + System.lineSeparator());

			throw new ExecutionFailedException(messageBuilder.toString(), e);
		}
	}
}
