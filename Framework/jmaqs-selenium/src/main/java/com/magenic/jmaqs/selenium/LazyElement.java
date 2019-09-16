package com.magenic.jmaqs.selenium;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.selenium.functionalinterfaces.Action;
import com.magenic.jmaqs.utilities.helper.GenericWait;
import com.magenic.jmaqs.utilities.helper.TimeoutException;
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

	/** Value indicating whether the lazy element is enabled */
	private boolean isEnabled;

	/** Value indicating whether the lazy element was selected or not */
	private boolean isSelected;

	/** Value indicating whether the lazy element is displayed */
	private boolean isDisplayed;

	/** The lazy element's tag name */
	private String tagName;

	/** The lazy element's text */
	private String text;

	/** the lazy element's location */
	private Point location;

	/** The lazy element's size */
	private Dimension size;

	/**
	 * Initializes a new instance of the {@link"LazyElement" /> class
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
		String tagName = "";

		try {
			tagName = GenericWait.waitFor(() -> {
				return this.getElement(() -> this.getTheExistingElement()).getTagName();
			});
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tagName;
	}

	/**
	 * Gets the lazy element's text
	 * @return The element text
	 */
	public String getText() {
		String text = "";

		try {
			text = GenericWait.waitFor(() -> {
				return this.getElement(() -> this.getTheExistingElement()).getText();
			});
		} 
		catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return text;
	}

	/**
	 * Gets the lazy element's location
	 * @return the location as a Point
	 */
	public Point getLocation() {
		Point location = null;

		try {
			location = GenericWait.waitFor(() -> {
				return this.getElement(() -> this.getTheVisibleElement()).getLocation();
			});
		} 
		catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return location;
	}

	/**
	 * Gets the lazy element's size
	 */
	public Dimension getSize() {
		Dimension size = null;

		try {
			size = GenericWait.waitFor(() -> {
				return this.getElement(() -> this.getTheVisibleElement()).getSize();
			});
		} 
		catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return size;
	}

	/**
	 * Click the lazy element 
	 */
	public void click() {
		try {
			GenericWait.waitFor(() -> {
				WebElement element = this.getElement(() -> this.getTheClickableElement());

				try {
					this.executeEvent(() -> element.click(), "Click");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return true;
			});
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Send keys to the lazy element
	 * @param text The text to send to the component
	 */
	public void sendKeys(String text)
	{
		try {
			GenericWait.waitFor(() -> {
				WebElement element = this.getElement(() -> this.getTheVisibleElement());
				
				try {
					this.executeEvent(() -> element.sendKeys(text), "SendKeys");
				} 
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return true;
			});
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			GenericWait.waitFor(() ->
			{
				WebElement element = this.getElement(() -> this.getTheVisibleElement());
				
				try {
					this.executeEvent(() -> element.clear(), "Clear");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return true;
			});
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Submit the lazy element
	 */
	public void submit() {
		try {
			GenericWait.waitFor(() ->
			{
				WebElement element = this.getElement(() -> this.getTheExistingElement());
				
				try {
					this.executeEvent(() -> element.submit(), "Submit");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return true;
			});
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the value for the given attribute
	 * @param attributeName The name of the attribute
	 * @return The attribute
	 */
	public String getAttribute(String attributeName) {
		String attribute = "";
		
		try {
			attribute = GenericWait.waitFor(() -> {
				return this.getElement(() -> this.getTheExistingElement()).getAttribute(attributeName);
			});
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return attribute;
	}

	/**
	 * Gets the current value of an element - Useful for get input box text
	 * @return The current value of the element
	 */
	public String getValue() {
		String value = "";
		
		try {
			value = GenericWait.waitFor(() -> {
				return this.getElement(() -> this.getTheVisibleElement()).getAttribute("value");
			});
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}

	/**
	 * Gets the CSS value for the given attribute
	 * @param propertyName The property name
	 * @return the css value for the property
	 */
	@Override
	public String getCssValue(String propertyName) {
		String cssValue = "";
		
		try {
			cssValue =  GenericWait.waitFor(() -> {
				return this.getElement(() -> this.getTheExistingElement()).getCssValue(propertyName);
			});
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cssValue;
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
	 * Wait for and get the clickable web element
	 * @return The clickable web element
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

	/**
	 * Gets the value of a JavaScript property of this element
	 * @param propertyName the property name
	 * @return the JavaScript property of the element
	 */
	public String getProperty(String propertyName) {
		return this.getTheExistingElement().getProperty(propertyName);
	}

	/**
	 * Finds the first {@link org.openqa.selenium.WebElement WebElement} using the given method.
	 * @param by The locating mechanism to use
	 * @return the {@link org.openqa.selenium.WebElement WebElement} being found
	 */
	public WebElement findElement(By by) {
		return this.getTheExistingElement().findElement(by);
	}

	/**
	 * Finds all {@link org.openqa.selenium.WebElement WebElement} within 
	 * the current context using the given mechanism.
	 * @param by the locating mechanism to use
	 * @return the List of {@link org.openqa.selenium.WebElement WebElement} being found
	 */
	public List<WebElement> findElements(By by) {
		return this.getTheExistingElement().findElements(by);
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

	/// <summary>
	/// Get a web element
	/// </summary>
	/// <param name="getElement">The get web element function</param>
	/// <returns>The web element</returns>
	private WebElement getElement(Supplier<WebElement> getElement) throws Exception
	{
		// Try to use cached element
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

	/// <summary>
	/// Execute an element action
	/// </summary>
	/// <param name="elementAction">The element action</param>
	/// <param name="caller">Name of the action, for logging purposes</param>
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

	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSelected() {
		boolean isSelected = false;

		try {
			isSelected =  GenericWait.waitFor(() -> {
				return this.getElement(() -> this.getTheExistingElement()).isSelected();
			});
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isSelected;
	}

	@Override
	public boolean isEnabled() {
		boolean isEnabled = false;

		try {
			isEnabled = GenericWait.waitFor(() -> {
				return this.getElement(() -> this.getTheExistingElement()).isEnabled();
			});
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isEnabled;
	}

	@Override
	public boolean isDisplayed() {
		boolean isDisplayed = false;

		try {
			isDisplayed = GenericWait.waitFor(() -> {
				return this.getElement(() -> this.getTheExistingElement()).isDisplayed();
			});
		} catch (InterruptedException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isDisplayed;
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}
}
