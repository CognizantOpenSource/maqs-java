package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.playwright.pageModel.PageModel;
import com.cognizantsoftvision.maqs.playwright.pageModel.PageModelOther;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/// <summary>
/// Test the base Playwright page object model
/// </summary>
public class PageObjectUnitTest extends BasePlaywrightTest {

  /// <summary>
  /// Setup test Playwright page model
  /// </summary>
  @BeforeTest
  public void createPlaywrightPageModel() {
    this.getPageDriver().navigateTo(PageModel.getUrl());
    this.getTestObject().setObject("pom", new PageModel(this.getTestObject()));
  }

  /// <summary>
  /// Verify test object is the same
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void PageModelTestObject() {
    Assert.assertEquals(this.getTestObject(), this.getPageModel().getTestObject());
  }

  /// <summary>
  /// Verify web driver is the same
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void PageModelWebDriver() {
    Assert.assertEquals(this.getPageDriver(), this.getPageModel().getPageDriver());
  }

  /// <summary>
  /// Verify logger is the same
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void PageModelLogger() {
    Assert.assertEquals(this.getLogger(), this.getPageModel().getLogger());
  }

  /// <summary>
  /// Verify perf timer collection is the same
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void PageModelPerfTimerCollection() {
    Assert.assertEquals(this.getPerfTimerCollection(), this.getPageModel().getPerfTimerCollection());
  }

  /// <summary>
  /// Verify we can override the page object web driver
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void OverridePageObjectWebDriver() {
    try {
      PageDriver oldWebDriver = this.getPageModel().getPageDriver();
      this.getPageModel().overridePageDriver(PageDriverFactory.getDefaultPageDriver());

      Assert.assertNotEquals(oldWebDriver, this.getPageModel().getPageDriver(), "The page driver was not updated");
    } finally {
      this.getPageModel().getPageDriver().close();
    }
  }

  /// <summary>
  /// Do lazy elements respect overrides
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void LazyRespectOverride() {
    // Define new named driver
    this.getManagerStore().putOrOverride("OtherDriver",
        new PageDriverManager(PageDriverFactory::getDefaultPageDriver, this.getTestObject()));
    PageDriver otherDriver = this.getManagerStore().getDriver("OtherDriver");

    PageModel model1 = this.getPageModel();
    PageModelOther model2 = new PageModelOther(this.getTestObject(), otherDriver);
    model2.openPage();

    // Make sure the page are properly loading using the different web drivers
    Assert.assertTrue(model1.getFlowerTablePlaywrightElement().isVisible(), "Model one may not be on the right page");
    Assert.assertTrue(model2.getLoadedPlaywrightElement().isEventuallyVisible(), "Model two may not be on the right page");

    // Swap the drivers
    model1.overridePageDriver(otherDriver);
    model2.overridePageDriver(this.getPageDriver());

    // Make sure the page are properly loading using the different web drivers
    Assert.assertFalse(model1.getFlowerTablePlaywrightElement().isVisible(), "Model one should have changed pages");
    Assert.assertFalse(model2.getLoadedPlaywrightElement().isVisible(), "Model two should have changed pages");

    // Now reload the pages
    model1.openPage();
    model2.openPage();

    // Make sure the page are properly loading using the different web drivers
    Assert.assertTrue(model1.getFlowerTablePlaywrightElement().isVisible(), "Model one may not be on the right page");
    Assert.assertTrue(model2.getLoadedPlaywrightElement().isEventuallyVisible(), "Model two may not be on the right page");
  }

  /// <summary>
  /// Get the Selenium page object
  /// </summary>
  /// <returns>The page model</returns>
  private PageModel getPageModel() {
//    return this.TestObject.Objects["pom"] as PageModel;
    return this.getTestObject().getObjects().get("pom").getClass();
  }
}
