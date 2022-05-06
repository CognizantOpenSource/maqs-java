package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.playwright.pageModel.PageModel;
import com.cognizantsoftvision.maqs.playwright.pageModel.AsyncPageModel;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The Playwright Object unit test class.
 */
public class PageObjectUnitTest extends BasePlaywrightTest {

  /**
   * Setup test Playwright page model.
   */
  @BeforeMethod
  public void createPlaywrightPageModel() {
    this.getPageDriver().navigateTo(PageModel.getUrl());
    this.getTestObject().setObject("pom", new PageModel(this.getTestObject()));
  }

  /**
   * Verify test object is the same.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void pageModelTestObject() {
    Assert.assertEquals(this.getTestObject(), this.getPageModel().getTestObject());
  }

  /**
   * Verify web driver is the same.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void pageModelWebDriver() {
    Assert.assertEquals(this.getPageDriver(), this.getPageModel().getPageDriver());
  }

  /**
   * Verify logger is the same.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void pageModelLogger() {
    Assert.assertNotNull(this.getTestObject().getLogger());
  }

  /**
   * Verify perf timer collection is the same.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void pageModelPerfTimerCollection() {
    Assert.assertEquals(this.getPerfTimerCollection(), this.getPageModel().getPerfTimerCollection());
  }

  /**
   * Verify we can override the page object web driver.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void overridePageObjectWebDriver() {
    try {
      PageDriver oldWebDriver = this.getPageModel().getPageDriver();
      this.getPageModel().overridePageDriver(PageDriverFactory.getDefaultPageDriver());

      Assert.assertNotEquals(oldWebDriver, this.getPageModel().getPageDriver(), "The page driver was not updated");
    } finally {
      this.getPageModel().getPageDriver().close();
    }
  }

  /**
   * Do lazy elements respect overrides.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void lazyRespectOverride() {
    // Define new named driver
    this.getManagerStore().putOrOverride("OtherDriver",
        new PlaywrightDriverManager(PageDriverFactory::getDefaultPageDriver, this.getTestObject()));

    PlaywrightDriverManager manager = (PlaywrightDriverManager) this.getManagerStore().getManager("OtherDriver");
    PageDriver otherDriver = manager.getPageDriver();

    PageModel model1 = this.getPageModel();
    AsyncPageModel model2 = new AsyncPageModel(this.getTestObject(), otherDriver);
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

  /**
   * Get the Selenium page object.
   * @return The page model
   */
  private PageModel getPageModel() {
    return (PageModel) this.getTestObject().getObjects().get("pom");
  }
}
