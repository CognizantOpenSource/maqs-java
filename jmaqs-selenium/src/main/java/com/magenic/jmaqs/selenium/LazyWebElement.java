package com.magenic.jmaqs.selenium;

import org.openqa.selenium.*;

import com.magenic.jmaqs.utilities.helper.GenericWait;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;

/**
 * Driver for dynamically finding and interacting with elements
 */
public class LazyWebElement extends AbstractLazyWebElement {

	/**
	 * Initializes a new instance of the {@link #LazyWebElement} class
	 * @param testObject The selenium test object
	 * @param locator The by locator to search on
	 * @param userFriendlyName The user friendly name of the lazy element
	 */
	public LazyWebElement(SeleniumTestObject testObject, By locator, String userFriendlyName) {
		super(userFriendlyName, locator, testObject);
	}

	/**
	 * Initializes a new instance of the Lazy Element class
	 * @param parent The parent lazy element
	 * @param locator The by locator to search on
	 * @param userFriendlyName The user friendly name of the lazy element
	 */
	public LazyWebElement(LazyWebElement parent, By locator, String userFriendlyName) {
		this(parent.getTestObject(), locator, userFriendlyName);
		this.parent = parent;
	}

	/**
	 * Gets the screenshot as the target type
	 * @param target The target output type
	 * @return The type to get the screenshot as
	 */
	public <X> X getScreenshotAs(OutputType<X> target) throws TimeoutException, InterruptedException {
		return GenericWait.waitFor(() -> this.getElement(this::getTheExistingElement).getScreenshotAs(target));
	}

}
