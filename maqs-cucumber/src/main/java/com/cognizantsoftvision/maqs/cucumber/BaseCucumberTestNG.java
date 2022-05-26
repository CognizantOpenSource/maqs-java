/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.cucumber;

import com.cognizantsoftvision.maqs.base.BaseTest;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import java.lang.reflect.Method;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

/**
 * Abstract base cucumber object.
 */
public abstract class BaseCucumberTestNG extends AbstractTestNGCucumberTests implements ITest {

  /**
   * The test name.
   */
  private final ThreadLocal<String> testName = new ThreadLocal<>();

  /**
   * Run before a test scenario.
   *
   * @param method of type Method
   * @param params of type Object[]
   */
  @BeforeMethod
  public void beforeMethod(Method method, Object[] params, ITestContext context) {
    String testNameTest = params[1].toString().replace("\"", "") + " - " + params[0].toString().replace("\"", "");
    testName.set(testNameTest);

    BaseTest test = createSpecificBaseTest();
    test.customSetup(testNameTest, context);

    ScenarioContext.put(ScenarioContext.MAQS_HOLDER, test);
  }

  /**
   * Save off results and cleanup test class.
   *
   * @param result The result of the test run
   */
  @AfterMethod
  public void tearDown(ITestResult result) {
    // Get the test object and set the result
    BaseTest test = ScenarioContext.get(ScenarioContext.MAQS_HOLDER, BaseTest.class);
    test.setTestResult(result);

    // If there was a failure log it
    if (result.getStatus() == ITestResult.FAILURE) {
      test.getLogger().logMessage(MessageType.ERROR, result.getThrowable().toString());
    }

    test.teardown();
    testName.remove();
    ScenarioContext.remove(ScenarioContext.MAQS_HOLDER);
  }

  /**
   * Get the test name.
   *
   * @return The test name
   */
  @Override
  public String getTestName() {
    return testName.get();
  }

  /**
   * Create a test class.
   *
   * @return The tests class
   */
  public abstract BaseTest createSpecificBaseTest();

  /**
   * Add ability to run in parallel.
   *
   * @return Scenarios
   */
  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }

  /**
   * Get the base test.
   *
   * @return The base test
   */
  protected BaseTest getBaseTest() {
    return ScenarioContext.get(ScenarioContext.MAQS_HOLDER, BaseTest.class);
  }
}
