/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.baseTest.unitTests;

import com.magenic.jmaqs.baseTest.BaseTest;
import com.magenic.jmaqs.baseTest.BaseTestObject;
import com.magenic.jmaqs.baseTest.DriverManager;
import com.magenic.jmaqs.baseTest.ManagerDictionary;
import com.magenic.jmaqs.baseTest.SoftAssert;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.MessageType;
import com.magenic.jmaqs.utilities.performance.PerfTimerCollection;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The type Base test object test.
 */
public class BaseTestObjectTest {

  /**
   * Base Test Object.
   */
  private BaseTest baseTest;

  /**
   * Method.
   */
  private Method method;

  /**
   * testContext.
   */
  private ITestContext testContext;

  /**
   * Sets up.
   *
   * @param method      the method
   * @param testContext the test context
   */
  @BeforeMethod
  public void setUp(Method method, ITestContext testContext) {
    this.method = method;
    this.testContext = testContext;
    baseTest = this.getBaseTest();
    try {
      baseTest.setup(method, testContext);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Test Constructor.
   */
  @Test
  public void testBaseTestObject1() {
    final BaseTestObject testObject = baseTest.getTestObject();
    final String methodName = this.method.getName();
    BaseTestObject baseTestObject = new BaseTestObject(testObject.getLog(), methodName);
    Assert.assertNotNull(baseTestObject, "Checking that Base Test Object instantiated correctly");
  }

  /**
   * Test Constructor.
   */
  @Test
  public void testBaseTestObject2() {
    final BaseTestObject testObject = baseTest.getTestObject();
    BaseTestObject baseTestObject = new BaseTestObject(testObject);
    Assert.assertNotNull(baseTestObject, "Checking that Base Test Object instantiated correctly");

  }

  /**
   * Test set value.
   */
  @Test
  public void testSetValue() {
    BaseTestObject testObject = baseTest.getTestObject();
    final String key = "SetKey";
    final String value = "SetKey Value";
    testObject.setValue(key, value);
    Assert.assertTrue(testObject.getValues().containsKey(key),
        "Checking that key exists in test object dictionary");
    Assert
        .assertEquals(testObject.getValues().get(key), value, "Checking that value set correctly");
  }

  /**
   * Test set object.
   */
  @Test
  public void testSetObject() {

    BaseTestObject testObject = baseTest.getTestObject();
    final String key = "SetObject";
    final Object object = new Object();
    testObject.setObject(key, object);
    Assert.assertTrue(testObject.getObjects().containsKey(key),
        "Checking that key exists in test object dictionary");
    Assert.assertEquals(testObject.getObjects().get(key), object,
        "Checking that value set correctly");
  }

  /**
   * Test get log.
   */
  @Test
  public void testGetLog() {
    BaseTestObject testObject = baseTest.getTestObject();
    Assert.assertNotNull(testObject.getLog(), "Checking that logger is not null.");
  }

  /**
   * Test set log.
   */
  @Test
  public void testSetLog() {
    BaseTestObject testObject = baseTest.getTestObject();
    final Logger logger = this.getLogger();
    testObject.setLog(logger);
    Assert.assertEquals(testObject.getLog(), logger, "Checking that logger set correctly.");
  }

  /**
   * Test get perf timer collection 1.
   */
  @Test
  public void testGetPerfTimerCollection1() {
    BaseTestObject testObject = baseTest.getTestObject();
    Assert.assertNotNull(testObject.getPerfTimerCollection(), "Checking that logger is not null.");
  }

  /**
   * Test set perf timer collection 1.
   */
  @Test
  public void testSetPerfTimerCollection1() {
    BaseTestObject testObject = baseTest.getTestObject();
    final PerfTimerCollection perfTimerCollection = new PerfTimerCollection(testObject.getLog(),
        method.getName());
    testObject.setPerfTimerCollection(perfTimerCollection);
    Assert.assertEquals(testObject.getPerfTimerCollection(), perfTimerCollection,
        "Checking that perf timer collection set correctly.");
  }

  /**
   * Test get soft assert 1.
   */
  @Test
  public void testGetSoftAssert1() {
    BaseTestObject testObject = baseTest.getTestObject();
    Assert.assertNotNull(testObject.getSoftAssert(), "Checking that logger is not null.");
  }

  /**
   * Test set soft assert 1.
   */
  @Test
  public void testSetSoftAssert1() {
    BaseTestObject testObject = baseTest.getTestObject();
    final SoftAssert softAssert = new SoftAssert(testObject.getLog());
    testObject.setSoftAssert(softAssert);
    Assert.assertEquals(testObject.getSoftAssert(), softAssert,
        "Checking that soft assert set correctly.");
  }

  /**
   * Test get values.
   */
  @Test
  public void testGetValues() {
    BaseTestObject testObject = baseTest.getTestObject();
    Assert.assertNotNull(testObject.getValues(), "Checking that values is not null.");
  }

  /**
   * Test get objects.
   */
  @Test
  public void testGetObjects() {
    BaseTestObject testObject = baseTest.getTestObject();
    Assert.assertNotNull(testObject.getObjects(), "Checking that objects is not null.");
  }

  /**
   * Test set objects.
   */
  @Test
  public void testSetObjects() {
    BaseTestObject testObject = baseTest.getTestObject();
    final ConcurrentHashMap<String, Object> objects = new ConcurrentHashMap<>();
    testObject.setObjects(objects);
    Assert.assertEquals(testObject.getObjects(), objects,
        "Checking that object hash map set correctly.");
  }

  /**
   * Test get manager store 1.
   */
  @Test
  public void testGetManagerStore1() {
    BaseTestObject testObject = baseTest.getTestObject();
    Assert.assertNotNull(testObject.getManagerStore(), "Checking that objects is not null.");
  }

  /**
   * Test set manager store.
   */
  @Test
  public void testSetManagerStore() {
    BaseTestObject testObject = baseTest.getTestObject();
    final ManagerDictionary managerDictionary = new ManagerDictionary();
    testObject.setManagerStore(managerDictionary);
    Assert.assertEquals(testObject.getManagerStore(), managerDictionary,
        "Checking that object hash map set correctly.");
  }

  /**
   * Test add driver manager.
   */
  @Test
  public void testAddDriverManager() {
    BaseTestObject testObject = baseTest.getTestObject();
    final Supplier supplier = (Supplier) () -> null;
    DriverManager driverManager = getDriverManager(testObject, supplier);
    Assert.assertEquals(testObject.getManagerStore().size(), 0,
        "Checking that manager store is empty");
    testObject.addDriverManager(driverManager);
    Assert.assertEquals(testObject.getManagerStore().size(), 1,
        "Checking that manager store has 1 object added");

  }

  /**
   * Test add driver manager - Overwrite True.
   */
  @Test
  public void testAddDriverManagerTrue() {
    BaseTestObject testObject = baseTest.getTestObject();
    final Supplier supplier = (Supplier) () -> null;
    final DriverManager driverManager = getDriverManager(testObject, supplier);
    final DriverManager driverManager2 = getDriverManager(testObject, supplier);
    Assert.assertEquals(testObject.getManagerStore().size(), 0,
        "Checking that manager store is empty");
    testObject.addDriverManager(driverManager, true);
    Assert.assertEquals(testObject.getManagerStore().size(), 1,
        "Checking that manager store has 1 object added");
    testObject.addDriverManager(driverManager2, true);
    Assert.assertEquals(testObject.getManagerStore().size(), 1,
        "Checking that manager store has 1 object added");
  }

  /**
   * Test add driver manager - Overwrite False.
   */
  @Test
  public void testAddDriverManagerFalse() {
    BaseTestObject testObject = baseTest.getTestObject();
    final Supplier supplier = (Supplier) () -> null;
    final DriverManager driverManager = getDriverManager(testObject, supplier);
    final DriverManager driverManager2 = getDriverManager(testObject, supplier);
    Assert.assertEquals(testObject.getManagerStore().size(), 0,
        "Checking that manager store is empty");
    testObject.addDriverManager(driverManager, false);
    Assert.assertEquals(testObject.getManagerStore().size(), 1,
        "Checking that manager store has 1 object added");
  }

  /**
   * Test add driver manager 2.
   */
  @Test
  public void testAddDriverManager2() {
    BaseTestObject testObject = baseTest.getTestObject();
    final Supplier supplier = (Supplier) () -> null;
    final DriverManager driverManager = getDriverManager(testObject, supplier);
    final String key = "DriverManager1";
    Assert.assertEquals(testObject.getManagerStore().size(), 0,
        "Checking that manager store is empty");
    testObject.addDriverManager(key, driverManager);
    Assert.assertEquals(testObject.getManagerStore().size(), 1,
        "Checking that manager store has 1 object added");
    Assert.assertTrue(testObject.getManagerStore().containsKey(key),
        "Checking if key exists in Manager Store");
  }

  /**
   * Test close.
   */
  @Test
  public void testClose() {
    BaseTestObject testObject = baseTest.getTestObject();
    final Supplier supplier = (Supplier) () -> null;
    final DriverManager driverManager = getDriverManager(testObject, supplier);
    final String key = "DriverManager1";
    testObject.addDriverManager(key, driverManager);
    testObject.close();
    Assert.assertNull(testObject.getManagerStore(), "Checking that manager store has been closed");
    Assert.assertEquals(testObject.getValues().size(), 0,
        "Checking if values in manager store are closed");

  }

  /**
   * Test add associated file.
   */
  @Test
  public void testAddAssociatedFile() {
    BaseTestObject testObject = baseTest.getTestObject();
    File temp = null;
    try {
      temp = File.createTempFile("tempfile", ".tmp");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert temp.exists();

    Assert.assertTrue(testObject.addAssociatedFile(temp.getAbsolutePath()),
        "Checking that associated file was added");
    Assert.assertEquals((testObject.getArrayOfAssociatedFiles()).length, 1,
        "Checking that one file was added to array.");
  }

  /**
   * Test remove associated file.
   */
  @Test
  public void testRemoveAssociatedFile() {
    BaseTestObject testObject = baseTest.getTestObject();
    File temp = null;
    try {
      temp = File.createTempFile("tempfile", ".tmp");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert temp.exists();
    final String path = temp.getAbsolutePath();

    Assert
        .assertTrue(testObject.addAssociatedFile(path), "Checking that associated file was added");
    Assert.assertTrue(testObject.removeAssociatedFile(path), "Checking that assocai");
  }

  /**
   * Test get array of associated files.
   */
  @Test
  public void testGetArrayOfAssociatedFiles() {
    BaseTestObject testObject = baseTest.getTestObject();
    File temp = null;
    try {
      temp = File.createTempFile("tempfile", ".tmp");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert temp.exists();
    final String path = temp.getAbsolutePath();
    Assert
        .assertTrue(testObject.addAssociatedFile(path), "Checking that associated file was added");
    Assert.assertNotNull(testObject.getArrayOfAssociatedFiles(),
        "Checking that array is instantiated");
    Assert.assertEquals(testObject.getArrayOfAssociatedFiles().length, 1,
        "Checking that array is not empty");
  }

  /**
   * Test contains associated file.
   */
  @Test
  public void testContainsAssociatedFile() {
    BaseTestObject testObject = baseTest.getTestObject();
    File temp = null;
    try {
      temp = File.createTempFile("tempfile", ".tmp");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert temp.exists();
    final String path = temp.getAbsolutePath();
    Assert
        .assertTrue(testObject.addAssociatedFile(path), "Checking that associated file was added");
    Assert.assertNotNull(testObject.getArrayOfAssociatedFiles(),
        "Checking that array is instantiated");
    Assert.assertTrue(testObject.containsAssociatedFile(path), "Checking if array contains file");
  }

  // Test Setup Objects
  private Logger getLogger() {
    return new Logger() {
      @Override
      public void logMessage(MessageType messageType, String message, Object... args) {

      }

      @Override
      public void logMessage(String message, Object... args) {

      }
    };
  }

  private BaseTest getBaseTest() {
    return new BaseTest() {
      @Override
      protected void postSetupLogging() throws Exception {

      }

      @Override
      protected void beforeLoggingTeardown(ITestResult resultType) {

      }
    };

  }

  private DriverManager getDriverManager(BaseTestObject testObject, Supplier supplier) {
    return new DriverManager(supplier, testObject) {
      @Override
      public void close() throws Exception {

      }
    };
  }

}