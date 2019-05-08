package com.magenic.jmaqs.baseTest.unitTests;

import com.magenic.jmaqs.baseTest.BaseTest;
import com.magenic.jmaqs.baseTest.BaseTestObject;
import com.magenic.jmaqs.baseTest.DriverManager;
import com.magenic.jmaqs.baseTest.ManagerDictionary;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.testng.Assert.*;

public class ManagerDictionaryTest extends BaseTest {

  @Test
  public void testClose() {

    try (ManagerDictionary managerDictionary = new ManagerDictionary()) {
      managerDictionary.put("DM1", getTestDriverManager());
      managerDictionary.put("DM2", getTestDriverManager());
      managerDictionary.put("DM3", getTestDriverManager());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testGetDriver() {
  }

  @Test
  public void testPut() {

    final String dm1 = "DM1";
    ManagerDictionary managerDictionary = new ManagerDictionary();
    managerDictionary.put(dm1, getTestDriverManager());
    Assert.assertTrue(managerDictionary.containsKey(dm1));
    assertNotNull(managerDictionary.get(dm1));
  }

  private TestDriverManager getTestDriverManager() {
    Supplier<Object> supplier = () -> null;
    return new TestDriverManager(supplier, getTestObject());
  }

  @Test
  public void testPutOrOverride() {
  }

  @Test
  public void testPutOrOverride1() {
  }

  @Test
  public void testRemove() {
    final String dm1 = "DM1";
    final String dm2 = "DM2";
    ManagerDictionary managerDictionary = new ManagerDictionary();
    managerDictionary.put(dm1, getTestDriverManager());
    managerDictionary.put(dm2, getTestDriverManager());
    Assert.assertTrue(managerDictionary.containsKey(dm1));
    Assert.assertTrue(managerDictionary.containsKey(dm2));
    managerDictionary.remove(dm2);
    Assert.assertTrue(managerDictionary.containsKey(dm1));
    Assert.assertFalse(managerDictionary.containsKey(dm2));
  }

  @Override
  protected void postSetupLogging() throws Exception {

  }

  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {

  }

  private class TestDriverManager extends DriverManager {
    /**
     * Instantiates a new Driver manager.
     *
     * @param getDriverFunction
     * @param baseTestObject    the base test object
     */
    public TestDriverManager(Supplier<Object> getDriverFunction, BaseTestObject baseTestObject) {
      super(getDriverFunction, baseTestObject);
    }

    @Override
    public void close() throws Exception {

    }
  }
}