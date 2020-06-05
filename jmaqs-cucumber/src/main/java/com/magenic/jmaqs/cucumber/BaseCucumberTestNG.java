/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.cucumber;

import com.magenic.jmaqs.base.BaseTest;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.MessageType;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public abstract class BaseCucumberTestNG extends AbstractTestNGCucumberTests implements ITest {

  private ThreadLocal<String> testName = new ThreadLocal<>();

  /**
   * Method beforeMethod ...
   *
   * @param method  of type Method
   * @param params  of type Object[]
   * @throws Throwable when
   */
  @BeforeMethod
  public void beforeMethod(Method method, Object[] params, ITestContext context) throws Throwable {
    String testNameTest = params[1].toString().replace("\"", "") + " - " + params[0].toString().replace("\"", "");
    testName.set(testNameTest);
    String name = Arrays.asList(params).toString().replace(" ", "").replace(',', '_')
        .replace("[", "").replace("]", "").replace("\"", "");

    System.out.println(name);

    BaseTest test = createSpecificBaseTest();
    test.customSetup(name, context);
    ScenarioContext.put(ScenarioContext.JMAQS_HOLDER, test);
    System.out.println("Before method setup done");
  }

  /**
   * Save off results and cleanup test class
   * @param result The result of the test run
   */
  @AfterMethod
  public void tearDown(ITestResult result) {
   BaseTest test =  ScenarioContext.get(ScenarioContext.JMAQS_HOLDER, BaseTest.class);
   test.setTestResult(result);
    if (result.getStatus() == ITestResult.FAILURE) {
      test.getLogger().logMessage(MessageType.ERROR, result.getThrowable().toString());
    }
      System.out.println("RemoveDriver");
    test.teardown();
    ScenarioContext.remove(ScenarioContext.JMAQS_HOLDER);
  }

  @Override
  public String getTestName() {
    return testName.get();
  }

  public abstract BaseTest createSpecificBaseTest();

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

  protected BaseTest getBaseTest()
  {
      return ScenarioContext.get(ScenarioContext.JMAQS_HOLDER, BaseTest.class);
  }
}
