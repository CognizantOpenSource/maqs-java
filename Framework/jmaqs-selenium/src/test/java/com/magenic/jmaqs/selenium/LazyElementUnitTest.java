package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.utilities.helper.exceptions.ExecutionFailedException;
import org.openqa.selenium.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;

import static org.testng.Assert.*;

/**
 * Unit test class for LazyElement
 */
public class LazyElementUnitTest extends BaseSeleniumTest {

	/**  String contstants used for the tests */
	private static final String DISABLED = "Disabled";
	private static final String FLOWER_TABLE_BY = "#Flower table";
	private static final String FLOWER_TABLE = "Flower table";
	private static final String FLOWER_TABLE_CAPTION = "Flower table caption";
	private static final String FOOTER_PARAGRAPH_BY = "FOOTER P";
	private static final String FOOTER = "Footer";

	/**
	 * Gets the disabled item
	 * @return The disabled item
	 */
	private LazyElement getDisabledItem() {
		return new LazyElement(this.getTestObject(), By.cssSelector("#disabledField INPUT"), DISABLED);
	}

	/**
	 * Gets the input box 
	 * @return The input box
	 */
	private LazyElement getInputBox() {
		return new LazyElement(this.getTestObject(), By.cssSelector("#TextFields [name='firstname']"), "Input box");
	}

	/**
	 * Gets the dialog one
	 * @return The dialog one
	 */
	private LazyElement getDialogOne() {
		return new LazyElement(this.getTestObject(), By.cssSelector("#CloseButtonShowDialog"), "Dialog");
	}

	/**
	 * Gets the dialog one button
	 * @return The dialog one button 
	 */
	private LazyElement getDialogOneButton() {
		return new LazyElement(this.getTestObject(), By.cssSelector("#showDialog1"), "Dialog");
	}

	/**
	 * Gets the submit button
	 * @return The submit button
	 */
	private LazyElement getSubmitButton() {
		return new LazyElement(this.getTestObject(), By.cssSelector("[class='btn btn-default'][type='submit']"), "Submit button");
	}

	/**
	 * Gets the not selected element
	 * @return The not selected element
	 */
	private LazyElement getNotSelected() {
		return new LazyElement(this.getTestObject(), By.cssSelector("#computerParts [value='one']"), "Not selected");
	}

	/**
	 * Gets an item that is going to be selected
	 * @return The selected element
	 */
	private LazyElement getSelected() {
		return new LazyElement(this.getTestObject(), By.cssSelector("#computerParts [value='two']"), "Selected");
	}

	/**
	 * Gets a parent element
	 * @return The parent element
	 */
	private LazyElement getFlowerTableLazyElement() {
		return new LazyElement(this.getTestObject(), By.cssSelector(FLOWER_TABLE_BY), FLOWER_TABLE);
	}

	/**
	 * Gets a child element, the second table caption
	 * @return The second table caption
	 */
	private LazyElement getFlowerTableCaptionWithParent() {
		return new LazyElement(this.getFlowerTableLazyElement(), By.cssSelector("CAPTION > Strong"), FLOWER_TABLE_CAPTION);
	}

	/**
	 * Gets the first table caption
	 * @return The first table caption
	 */
	private LazyElement getFirstTableCaption() {
		return new LazyElement(this.getTestObject(), By.cssSelector("CAPTION > Strong"), "Clothing table caption");
	}

	/**
	 * Gets the disabled DIV
	 * @return The disabled div
	 */
	private LazyElement getDisabledDiv()  {
		return new LazyElement(this.getTestObject(), By.cssSelector("#disabledField"), "Parent disabled div");
	}

	/**
	 * Gets the disabled input
	 * @return The disabled input
	 */
	private LazyElement getDisabledInput() {
		return new LazyElement(this.getDisabledDiv(), By.cssSelector("input"), FLOWER_TABLE_CAPTION);
	}

	/**
	 * Setup before a test
	 */
	@BeforeMethod
	public void navigateToTestPage() {
		this.getWebDriver().navigate().to(SeleniumConfig.getWebSiteBase() + "Automation");
		UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
	}

	/**
	 * Verify Lazy Element search respects the parent find by finding mismatch
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyWithParentAndWithoutDontMatch() throws TimeoutException, InterruptedException {
		// Make sure we got the table caption we are looking for
		assertEquals(FLOWER_TABLE, this.getFlowerTableCaptionWithParent().getText());

		// Make sure the the first found was not the the flower table
		assertNotEquals(this.getFlowerTableCaptionWithParent().getText(), this.getFirstTableCaption().getText());
	}

	/**
	 * Verify Lazy Element search respects the parent find by finding match
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyWithParentAndWithoutMatch() throws TimeoutException, InterruptedException {
		// Get the lazy element without a parent
		LazyElement flowerTableCaptionWithoutParent = new LazyElement(
				this.getTestObject(), 
				By.cssSelector("#FlowerTable CAPTION > Strong"),
				FLOWER_TABLE);

		// Make sure we are finding the correct table
		assertEquals(FLOWER_TABLE, this.getFlowerTableCaptionWithParent().getText());

		// Make sure we got the table caption we are looking for
		assertEquals(this.getFlowerTableCaptionWithParent().getText(), flowerTableCaptionWithoutParent.getText());
	}
	/**
	 * Verify Lazy Element is cached as expected
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyPreCaching() {
		// Create the lazy element and use it
		LazyElement footer = new LazyElement(this.getTestObject(), By.cssSelector(FOOTER_PARAGRAPH_BY), FOOTER);

		// Make sure we are getting back the same cached element
		assertNull(footer.getCachedElement(), "The cached element should be null as we never triggered a find");
	}

	/**
	 * A new find does not return the cached element
	 */
	@Test(groups=TestCategories.Selenium)
	public void newFindDifferentThanCached() throws TimeoutException, InterruptedException {
		// Create the lazy element and use it
		LazyElement footer = new LazyElement(this.getTestObject(), By.cssSelector(FOOTER_PARAGRAPH_BY), FOOTER);

		// Trigger a find and save off the element
		footer.getValue();
		WebElement footerElementBefore = footer.getCachedElement();

		// Do the event again and save off the changed element 
		footer.getValue();

		// Make sure doing a new find returns an element that is not the same as the cached element
		assertNotEquals(this.getWebDriver().findElement(footer.getBy()), footerElementBefore);
	}

	/**
	 * Check that the element was cached
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementCached() throws TimeoutException, InterruptedException {
		// Create the lazy element and use it
		LazyElement footer = new LazyElement(this.getTestObject(), By.cssSelector(FOOTER_PARAGRAPH_BY), FOOTER);

		// Trigger a find and save off the element
		footer.getValue();
		WebElement footerElementBefore = footer.getCachedElement();

		// Do the event again and save off the changed element 
		footer.getValue();
		WebElement footerElementAfter = footer.getCachedElement();

		// Make sure the second event didn't trigger a new find
		assertEquals(footerElementBefore, footerElementAfter);
	}

	/**
	 * Stale elements trigger a new find
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyCaching() throws TimeoutException, InterruptedException {

		// setting the wait driver since the caching isn't actually waiting for element actions
		// just setting the element
		UIWaitFactory.getWaitDriver(this.getWebDriver(), 1, 1);

		// Create the lazy element and use it
		LazyElement footer = new LazyElement(this.getTestObject(), By.cssSelector(FOOTER_PARAGRAPH_BY), FOOTER);

		// Trigger a find and save off the element
		footer.getValue();
		WebElement footerElementBefore = footer.getCachedElement();

		// Do the event again and save off the changed element 
		footer.getValue();

		// Go to another page so the old element will be stale, this will force us to get a new one
		this.getWebDriver().navigate().to(SeleniumConfig.getWebSiteBase() + "Automation/AsyncPage");

		// Trigger a new find, this should be new because the cached element is stale
		footer.getValue();
		assertNotEquals(footerElementBefore, footer.getCachedElement());
	}

	/**
	 * Verify the get elements trigger new finds - We do this because we are looking for specific states
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyGetsTriggerFind()
	{
		// Create the lazy element and use it
		LazyElement footer = new LazyElement(this.getTestObject(), By.cssSelector(FOOTER_PARAGRAPH_BY), FOOTER);

		WebElement cacheFooter = footer.getTheVisibleElement();

		// Make sure the second event didn't trigger a new find
		assertEquals(cacheFooter, footer.getCachedElement());
	}

	/**
	 * Verify the get click-able element triggers new finds - We do this because we are looking for specific states
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyGetClickableTriggerFind() {
		// Create the lazy element and use it
		LazyElement footer = new LazyElement(this.getTestObject(), By.cssSelector(FOOTER_PARAGRAPH_BY), FOOTER);
		LazyElement footer2 = new LazyElement(this.getTestObject(), By.cssSelector(FOOTER_PARAGRAPH_BY), FOOTER);

		footer.getTheVisibleElement();
		footer2.getTheClickableElement();

		// Make sure get click-able triggers a new find
		assertNotSame(footer.getCachedElement(), footer.getTheClickableElement());
	}

	/**
	 * Verify the get existing element triggers new finds - We do this because we are looking for specific states
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyGetExistTriggerFind() {
		// Create the lazy element and use it
		LazyElement footer = new LazyElement(this.getTestObject(), By.cssSelector(FOOTER_PARAGRAPH_BY), FOOTER);

		footer.getTheVisibleElement();

		// Make sure get exists triggers a new find
		assertNotEquals(footer.getCachedElement(), footer.getTheExistingElement());
	}

	/**
	 * Verify the get visible element triggers new finds - We do this because we are looking for specific states
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyGetVisibleTriggerFind() {
		// Create the lazy element and use it
		LazyElement footer = new LazyElement(this.getTestObject(), By.cssSelector(FOOTER_PARAGRAPH_BY), FOOTER);

		footer.getTheVisibleElement();

		// Make sure get visible triggers a new find
		assertNotEquals(footer.getCachedElement(), footer.getTheVisibleElement());
	}

	/**
	 * Verify Lazy Element Clear test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementClear() throws TimeoutException, InterruptedException, ExecutionFailedException {
		// Make sure we can set the value
		this.getInputBox().sendKeys("test");
		assertEquals("test", this.getInputBox().getAttribute("value"));

		// Make sure the value is cleared
		this.getInputBox().clear();
		assertEquals("", this.getInputBox().getAttribute("value"));
	}

	/**
	 * Verify Lazy Element Click test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementClick() throws TimeoutException, InterruptedException, ExecutionFailedException {
		this.getDialogOneButton().click();
		assertTrue(this.getDialogOne().getIsDisplayed());
	}

	/**
	 * Verify Lazy Element get By test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementGetBy() {
		By testBy = By.cssSelector("#ItemsToAutomate");
		LazyElement testLazyElement = new LazyElement(this.getTestObject(), testBy, "TEST");

		assertEquals(testBy, testLazyElement.getBy());
	}

	/**
	 * Verify Lazy Element get of the test object
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementGetTestObject() {
		LazyElement testLazyElement = new LazyElement(this.getTestObject(), By.cssSelector("#ItemsToAutomate"), "TEST");
		assertEquals(this.getTestObject(), testLazyElement.getTestObject());
	}

	/**
	 * Verify Lazy Element GetAttribute test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementGetAttribute() throws TimeoutException, InterruptedException {
		assertEquals(DISABLED, this.getDisabledItem().getAttribute("value"));
	}

	/**
	 * Verify Lazy Element with a parent GetAttribute test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementGetAttributeWithParent() throws TimeoutException, InterruptedException {
		assertEquals(this.getDisabledInput().getValue(), DISABLED);
	}

	/**
	 * Verify Lazy Element GetCssValue test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementGetCssValue() throws TimeoutException, InterruptedException {
		assertEquals(this.getDialogOneButton().getCssValue("overflow"), "visible");
	}

	/**
	 * Verify Lazy Element with parent GetCssValue test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementGetCssValueWithParent() throws TimeoutException, InterruptedException {
		assertEquals(this.getDisabledInput().getCssValue("max-width"), "280px");
	}

	/**
	 * Verify Lazy Element SendKeys test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementSendKeys() throws TimeoutException, InterruptedException, ExecutionFailedException {
		this.getInputBox().sendKeys("test");
		assertEquals(this.getInputBox().getValue(), "test");
	}

	/**
	 * Verify Lazy Element with a parent SendKeys test
	 */
	@Test(groups=TestCategories.Selenium, expectedExceptions=ExecutionFailedException.class)
	public void lazyElementSendKeysWithParent() throws TimeoutException, InterruptedException, ExecutionFailedException {
		this.getDisabledInput().sendKeys("test");
	}

	/**
	 * Verify Lazy Element SendKeys test
	 * @throws IOException, Exception 
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementSendSecretKeys() throws IOException, ExecutionFailedException, InterruptedException, TimeoutException {
		this.getInputBox().sendKeys("beforeSuspendTest");
		this.getInputBox().clear();
		this.getInputBox().sendSecretKeys("secretKeys");
		this.getInputBox().clear();
		this.getInputBox().sendKeys("continueTest");

		FileLogger logger = (FileLogger)this.getTestObject().getLog();
		String filepath = logger.getFilePath();
		String logFile = Files.readAllLines(Paths.get(filepath)).stream().reduce((x, y) -> x + y + System.lineSeparator())
				.orElseThrow(() -> new FileNotFoundException(String.format("The log file %s was not found at path %s", logger.getFileName(), filepath)));
		
		assertTrue(logFile.contains("beforeSuspendTest"));
		assertFalse(logFile.contains("secretKeys"));
		assertTrue(logFile.contains("continueTest"));

		Files.delete(Paths.get(filepath));
	}

	/**
	 * Verify Lazy Element Submit test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementSubmit() throws TimeoutException, InterruptedException, ExecutionFailedException {
		this.getWebDriver().navigate().to(SeleniumConfig.getWebSiteBase() + "Employees");
		UIWait waitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());

		waitDriver.waitForClickableElement(By.cssSelector("A[href^='/Employees/Edit/']")).click();
		waitDriver.waitForPageLoad();

		this.getSubmitButton().submit();
		assertTrue(waitDriver.waitUntilAbsentElement(By.cssSelector("#[type='submit']")), "Submit did not go away");
	}

	/**
	 * Verify Lazy Element with parent Submit test
	 */
	@Test(groups=TestCategories.Selenium, expectedExceptions=ExecutionFailedException.class)
	public void lazyElementSubmitWithParent() throws TimeoutException, InterruptedException, ExecutionFailedException {
		this.getDisabledInput().submit();
	}

	/**
	 * Verify Lazy Element Displayed test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementDisplayed() throws TimeoutException, InterruptedException {
		assertTrue(this.getDialogOneButton().getIsDisplayed());
		assertFalse(this.getDialogOne().getIsDisplayed());
	}

	/**
	 * Verify Lazy Element with parent Displayed test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementDisplayedWithParent() throws TimeoutException, InterruptedException {
		assertTrue(this.getDisabledInput().getIsDisplayed());
	}

	/**
	 * Verify Lazy Element Enabled test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementEnabled() throws TimeoutException, InterruptedException {
		assertFalse(this.getDisabledItem().getIsEnabled());
		assertTrue(this.getInputBox().getIsEnabled());
	}

	/**
	 * Verify Lazy Element with parent Enabled test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementEnabledWithParent() throws TimeoutException, InterruptedException {
		assertFalse(this.getDisabledInput().getIsEnabled());
		assertTrue(this.getFlowerTableCaptionWithParent().getIsEnabled());
	}

	/**
	 * Verify Lazy Element Selected test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementSelected() throws TimeoutException, InterruptedException {
		ElementHandler.selectDropDownOptionByValue(this.getWebDriver(), By.cssSelector("#computerParts"), "two");

		assertTrue(this.getSelected().getIsSelected());
		assertFalse(this.getNotSelected().getIsSelected());
	}

	/**
	 * Verify Lazy Element Text test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementText() throws TimeoutException, InterruptedException {
		assertEquals("Show dialog", this.getDialogOneButton().getText());
	}

	/**
	 * Verify Lazy Element with parent Text test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementTextWithParent() throws TimeoutException, InterruptedException {
		assertEquals(FLOWER_TABLE, this.getFlowerTableCaptionWithParent().getText());
	}

	/**
	 * Verify Lazy Element Location test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementLocation() throws TimeoutException, InterruptedException {
		Point point = this.getInputBox().getLocation();
		assertTrue(point.getX() > 0 && point.getY() > 0, "Unexpected point: " + point);
	}

	/**
	 * Verify Lazy Element with parent Location test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementLocationWithParent() throws TimeoutException, InterruptedException {
		Point earlierPoint = this.getInputBox().getLocation();
		Point laterPoint = this.getDisabledInput().getLocation();

		assertTrue(laterPoint.getX() > 0, "Unexpected point: " + laterPoint);
		assertTrue(earlierPoint.getY() < laterPoint.getY(), "Unexpected point: " + laterPoint);
	}

	/**
	 * Verify Lazy Element Size test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementSize() throws TimeoutException, InterruptedException {
		Dimension size = this.getInputBox().getSize();
		assertTrue(size.getWidth() > 0 && size.getHeight() > 0, "Height and/or width are less than 1");
	}

	/**
	 * Verify Lazy Element with parent Size test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementSizeWithParent() throws TimeoutException, InterruptedException {
		Dimension size = this.getDisabledInput().getSize();
		assertTrue(size.getWidth() > 152 && size.getHeight() > 21, "Height of greater than 22 and width of greater than 152, but got " + size);
	}

	/**
	 * Verify lazy element tag name test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementTagName() throws TimeoutException, InterruptedException {
		assertEquals("input", this.getInputBox().getTagName());
	}

	/**
	 * Verify lazy element with parent tag name test
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementTagNameWithParent() throws TimeoutException, InterruptedException {
		assertEquals("strong", this.getFlowerTableCaptionWithParent().getTagName());
	}

	/**
	 * Verify lazy element get the visible element
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementGetVisibleElement() {
		assertNotEquals(null, this.getInputBox().getTheVisibleElement());
	}

	/**
	 * Verify lazy element get the click-able element
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementGetClickableElement() {
		assertNotNull(this.getInputBox().getTheClickableElement());
	}

	/**
	 * Verify lazy element get the existing element
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementGetExistingElement() {
		assertNotNull(this.getInputBox().getTheExistingElement());
	}

	/**
	 * Verify lazy element to string
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementToString() {
		// Hard-coded userFriendlyName due to private access on LazyElement
		String stringValue = 
				this.getFlowerTableLazyElement().getBy().toString() + FLOWER_TABLE;

		assertEquals(stringValue, this.getFlowerTableLazyElement().toString());
	}

	/**
	 * Verify lazy element with parent to string
	 */
	@Test(groups=TestCategories.Selenium)
	public void lazyElementWithParentToString() {
		// Hard-coded userFriendlyName due to private access on LazyElement
		String stringValue =
				this.getFlowerTableLazyElement().getBy().toString() + FLOWER_TABLE +
						this.getFlowerTableCaptionWithParent().getBy().toString() + FLOWER_TABLE_CAPTION;

		assertEquals(stringValue, this.getFlowerTableCaptionWithParent().toString());
	}
}
