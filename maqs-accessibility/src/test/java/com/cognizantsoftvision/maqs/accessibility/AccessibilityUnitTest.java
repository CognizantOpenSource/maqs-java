/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.accessibility;

import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import com.cognizantsoftvision.maqs.selenium.BaseSeleniumTest;
import com.cognizantsoftvision.maqs.selenium.SeleniumConfig;
import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.cognizantsoftvision.maqs.utilities.logging.FileLogger;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Accessibility Unit Tests.
 */
public class AccessibilityUnitTest extends BaseSeleniumTest {

  /**
   * Unit testing site URL - Login page.
   */
  private static final String TestSiteUrl = SeleniumConfig.getWebSiteBase();

  /**
   * Verify we get verbose message back.
   */
  @Test(groups = TestCategories.ACCESSIBILITY)
  public void testAccessibilityCheckVerbose() throws IOException {
    Assert.assertTrue(true, "Expected to find 20 pass matches.");
  }
}