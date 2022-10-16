/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import com.cognizantsoftvision.maqs.utilities.logging.TestResultType;
import org.testng.ITestResult;

/**
 * Get properties from TestNG and JUnit test results.
 */
public class TestResultContent {

  private TestResultContent() {
    // Private constructor
  }

  /**
   /**
   * Get the type of test result.
   * @param status can be gotten by doing this.testResult.getStatus() in the Base Test class
   * @return The type of test result
   */
  public static TestResultType getResultType(int status) {
    switch (status) {
      case ITestResult.SUCCESS:
        return TestResultType.PASS;
      case ITestResult.FAILURE:
        return TestResultType.FAIL;
      case ITestResult.SKIP:
        return TestResultType.SKIP;
      default:
        return TestResultType.OTHER;
    }
  }

  /**
   * Get the test result type as text.
   * @param status can be gotten by doing this.testResult.getStatus() in the Base Test class
   * @return The test result type as text
   */
  public static String getResultText(int status) {
    switch (status) {
      case ITestResult.SUCCESS:
        return "SUCCESS";
      case ITestResult.FAILURE:
        return "FAILURE";
      case ITestResult.SKIP:
        return "SKIP";
      default:
        return "OTHER";
    }
  }
}
