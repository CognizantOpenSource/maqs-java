package com.cognizantsoftvision.maqs.base;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

/**
 * Test the Test Result Content class.
 */
public class TestResultContentUnitTest extends BaseGenericTest {

  /**
   * Tests getting the correct Result type back.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testGettingTheResultType() {
    Assert.assertEquals(TestResultContent.getResultType(ITestResult.SUCCESS).name(), "PASS");
    Assert.assertEquals(TestResultContent.getResultType(ITestResult.FAILURE).name(), "FAIL");
    Assert.assertEquals(TestResultContent.getResultType(ITestResult.SKIP).name(), "SKIP");
    Assert.assertEquals(TestResultContent.getResultType(ITestResult.SUCCESS_PERCENTAGE_FAILURE).name(), "OTHER");
    Assert.assertEquals(TestResultContent.getResultType(ITestResult.STARTED).name(), "OTHER");
    Assert.assertEquals(TestResultContent.getResultType(ITestResult.CREATED).name(), "OTHER");
  }

  /**
   * Tests getting the correct result text back.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testGettingTheResultText() {
    Assert.assertEquals(TestResultContent.getResultText(ITestResult.SUCCESS), "SUCCESS");
    Assert.assertEquals(TestResultContent.getResultText(ITestResult.FAILURE), "FAILURE");
    Assert.assertEquals(TestResultContent.getResultText(ITestResult.SKIP), "SKIP");
    Assert.assertEquals(TestResultContent.getResultText(ITestResult.SUCCESS_PERCENTAGE_FAILURE), "OTHER");
    Assert.assertEquals(TestResultContent.getResultText(ITestResult.STARTED), "OTHER");
    Assert.assertEquals(TestResultContent.getResultText(ITestResult.CREATED), "OTHER");
  }
}