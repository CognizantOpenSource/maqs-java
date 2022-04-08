/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.ConfigSection;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaywrightConfigUnitTest {

  /// <summary>
  /// Get expected WebBase configuration
  /// </summary>
  @Test()
  public void testGetBrowser() {
    Assert.assertEquals(PlaywrightConfig.getBrowserName(), "Chrome");
  }

        [DataTestMethod]
      [DataRow("Chrome")]
      [DataRow("Chromium")]
      [DataRow("Firefox")]
      [DataRow("Edge")]
      [DataRow("Webkit")]

  public void ConfigBrowserName(String browserName) {
    Config.addaddTestSettingValue("Browser", browserName, ConfigSection.PLAYWRIGHT_MAQS);
    Assert.assertEquals(PlaywrightConfig.getBrowserName(), browserName);
  }

        [DataTestMethod]
      [DataRow("Chromium", PlaywrightBrowser.hromium)]
      [DataRow("Firefox", PlaywrightBrowser.Firefox)]
      [DataRow("Edge", PlaywrightBrowser.Edge)]
      [DataRow("Webkit", PlaywrightBrowser.Webkit)]
      [DataRow(null, PlaywrightBrowser.Chrome)]
      [DataRow("Chrome", PlaywrightBrowser.Chrome)]
  public void ConfigBrowserEnum(string browser, PlaywrightBrowser browserEnum) {
    Config.AddTestSettingValue("Browser", browser, ConfigSection.PLAYWRIGHT_MAQS);
    Assert.AreEqual(browserEnum, PlaywrightConfig.getBrowserType());
  }

  /// <summary>
  /// Make sure error correct error is thrown if we use a bad browser name
  /// </summary>
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void configBadBrowserName() {
    //Config.addAddTestSettingValue("Browser", "IE", ConfigSection.PlaywrightMaqs);
    PlaywrightBrowser type = PlaywrightConfig.getBrowserType();
    Assert.fail("IE returned type: " + type);
  }

  /// <summary>
  /// Get expected UseProxy configuration
  /// </summary>
  @Test
  public void getUseProxy() {
    Assert.assertFalse(PlaywrightConfig.getUseProxy());
  }

  /// <summary>
  /// Get expected proxy address configuration
  /// </summary>
  public void GetProxyAddress() {
    Assert.assertEquals(PlaywrightConfig.getProxyAddress(), "http://localhost:8002");
  }
}
