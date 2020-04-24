package com.magenic.jmaqs.selenium;

import java.util.ArrayList;
import java.util.List;
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

	@Override
	public WebElement findElement(By by) throws TimeoutException, InterruptedException {
		return new LazyWebElement(this, by, userFriendlyName);
	}

	@Override
	public List<WebElement> findElements(By by) throws TimeoutException, InterruptedException {
		int index = 0;
		List<WebElement> elements = new ArrayList<>();
		for (WebElement element : this.getTheExistingElement().findElements(by)) {
			elements.add(new LazyWebElement(this, by, element, index, String.format("%s - %d", userFriendlyName, index++)));
		}

		return super.findElements(by);
	}
}
