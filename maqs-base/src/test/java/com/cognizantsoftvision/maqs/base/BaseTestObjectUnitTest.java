/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.performance.PerfTimerCollection;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The type Base test object test.
 */
public class BaseTestObjectUnitTest extends BaseGenericTest {

  /**
   * Test Constructor with Log and Method.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testBaseTestObject1() {
    final ITestObject testObject = this.getTestObject();

    // final String methodName = this.method.getName();
    BaseTestObject baseTestObject = new BaseTestObject(testObject.getLogger(), "FakeTestName");
    Assert.assertNotNull(baseTestObject, "Checking that Base Test Object instantiated correctly");
  }

  /**
   * Test Constructor with Test Object.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testBaseTestObject2() {
    BaseTestObject baseTestObject = new BaseTestObject(this.getTestObject());
    Assert.assertNotNull(baseTestObject, "Checking that Base Test Object instantiated correctly");
  }

  /**
   * Test set value.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testSetValue() {
    ITestObject testObject = this.getTestObject();
    final String key = "SetKey";
    final String value = "SetKey Value";
    testObject.setValue(key, value);
    Assert.assertTrue(testObject.getValues().containsKey(key), "Checking that key exists in test object dictionary");
    Assert.assertEquals(testObject.getValues().get(key), value, "Checking that value set correctly");
  }

  /**
   * Test set values.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testSetValues() {
    ITestObject testObject = this.getTestObject();
    final String key = "SetKey";
    final String value = "SetKey Value";
    ConcurrentHashMap <String, String> hashMap = new ConcurrentHashMap<>();
    hashMap.put(key, value);
    testObject.setValues(hashMap);
    Assert.assertTrue(testObject.getValues().containsKey(key), "Checking that key exists in test object dictionary");
    Assert.assertEquals(testObject.getValues().get(key), value, "Checking that value set correctly");
  }

  /**
   * Test set object.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testSetObject() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    final String key = "SetObject";
    final Object object = new Object();
    testObject.setObject(key, object);
    Assert.assertTrue(testObject.getObjects().containsKey(key), "Checking that key exists in test object dictionary");
    Assert.assertEquals(testObject.getObjects().get(key), object, "Checking that value set correctly");
  }

  /**
   * Test set objects.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testSetObjects() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    final String key = "SetObject";
    final Object object = new Object();
    ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<>();
    hashMap.put(key, object);

    testObject.setObjects(hashMap);
    Assert.assertTrue(testObject.getObjects().containsKey(key), "Checking that key exists in test object dictionary");
    Assert.assertEquals(testObject.getObjects().get(key), object, "Checking that value set correctly");
  }

  /**
   * Test get log.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetLog() {
    ITestObject testObject = this.getTestObject();
    Assert.assertNotNull(testObject.getLogger(), "Checking that logger is not null.");
  }

  /**
   * Test set log.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testSetLog() {
    ITestObject testObject = this.getTestObject();
    final ILogger logger = this.getLogger();
    testObject.setLogger(logger);
    Assert.assertEquals(testObject.getLogger(), logger, "Checking that logger set correctly.");
  }

  /**
   * Test Get Perf Collection Timer - Not Null.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetPerfTimerCollectionNotNull() {
    ITestObject testObject = this.getTestObject();
    Assert.assertNotNull(testObject.getPerfTimerCollection(), "Checking that logger is not null.");
  }

  /**
   * Test Set Perf Collection Timer - Get/Set.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testSetPerfTimerCollectionGetSet() {
    ITestObject testObject = this.getTestObject();
    final PerfTimerCollection perfTimerCollection = new PerfTimerCollection(testObject.getLogger(), "FakeTestName");
    testObject.setPerfTimerCollection(perfTimerCollection);
    Assert.assertEquals(testObject.getPerfTimerCollection(), perfTimerCollection,
        "Checking that perf timer collection set correctly.");
  }

  /**
   * Test get values.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetValues() {
    ITestObject testObject = this.getTestObject();
    Assert.assertNotNull(testObject.getValues(), "Checking that values is not null.");
  }

  /**
   * Test get objects.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetObjects() {
    ITestObject testObject = this.getTestObject();
    Assert.assertNotNull(testObject.getObjects(), "Checking that objects is not null.");
  }

  /**
   * Test Get Manager Store - Not Null.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetManagerStoreNotNull() {
    ITestObject testObject = this.getTestObject();
    Assert.assertNotNull(testObject.getManagerStore(), "Checking that objects is not null.");
  }

  /**
   * Test add driver manager.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testAddDriverManager() {
    ITestObject testObject = this.getTestObject();
    final Supplier<String> supplier = () -> null;
    DriverManager<String> driverManager = getDriverManager(testObject, supplier);
    Assert.assertEquals(testObject.getManagerStore().size(), 0, "Checking that manager store is empty");
    testObject.addDriverManager("new Driver", driverManager);
    Assert.assertEquals(testObject.getManagerStore().size(), 1, "Checking that manager store has 1 object added");
  }

  /**
   * Test overriding the driver manager.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testOverrideDriverManager() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    DriverManager<String> driverManager = getDriverManager(testObject, null);
    driverManager.baseDriver = "Test String";
    testObject.overrideDriverManager("Test", driverManager);
    testObject.overrideDriverManager("Test", driverManager);
  }

  /**
   * Test add driver manager - Overwrite True.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testAddDriverManagerTrue() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    final Supplier<String> supplier = () -> null;
    final DriverManager<String> driverManager = getDriverManager(testObject, supplier);
    final DriverManager<String> driverManager2 = getDriverManager(testObject, supplier);
    Assert.assertEquals(testObject.getManagerStore().size(), 0, "Checking that manager store is empty");
    testObject.addDriverManager(driverManager);
    Assert.assertEquals(testObject.getManagerStore().size(), 1, "Checking that manager store has 1 object added");
    testObject.addDriverManager(driverManager2, true);
    Assert.assertEquals(testObject.getManagerStore().size(), 1, "Checking that manager store has 1 object added");
  }

  /**
   * Test add driver manager - Overwrite False.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testAddDriverManagerFalse() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    final Supplier<String> supplier = () -> null;
    final DriverManager<String> driverManager = getDriverManager(testObject, supplier);

    Assert.assertEquals(testObject.getManagerStore().size(), 0, "Checking that manager store is empty");
    testObject.addDriverManager(driverManager, false);
    Assert.assertEquals(testObject.getManagerStore().size(), 1, "Checking that manager store has 1 object added");
  }

  /**
   * Test add driver manager 2.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testAddDriverManager2() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    final Supplier<String> supplier = () -> null;
    final DriverManager<String> driverManager = getDriverManager(testObject, supplier);
    final String key = "DriverManager1";
    Assert.assertEquals(testObject.getManagerStore().size(), 0, "Checking that manager store is empty");
    testObject.addDriverManager(key, driverManager);
    Assert.assertEquals(testObject.getManagerStore().size(), 1, "Checking that manager store has 1 object added");
    Assert.assertTrue(testObject.getManagerStore().containsKey(key), "Checking if key exists in Manager Store");
  }

  /**
   * Test close.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testClose() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    final Supplier<String> supplier = () -> null;
    final DriverManager<String> driverManager = getDriverManager(testObject, supplier);
    final String key = "DriverManager1";
    testObject.addDriverManager(key, driverManager);
    testObject.close();
    Assert.assertNull(testObject.getManagerStore(), "Checking that manager store has been closed");
    Assert.assertEquals(testObject.getValues().size(), 0, "Checking if values in manager store are closed");
  }

  /**
   * Test add associated file.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testAddAssociatedFile() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    File temp = null;
    try {
      temp = File.createTempFile("tempFile", ".tmp");
    } catch (IOException e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(temp);
    Assert.assertTrue(temp.exists());

    Assert.assertTrue(testObject.addAssociatedFile(temp.getAbsolutePath()), "Checking that associated file was added");
    Assert.assertEquals((testObject.getArrayOfAssociatedFiles()).length, 1,
        "Checking that one file was added to array.");
  }

  /**
   * Test try adding invalid associated file.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testAddInvalidAssociatedFile() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    Assert.assertFalse(testObject.addAssociatedFile(""), "Checking that associated file was added");
  }

  /**
   * Test remove associated file.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testRemoveAssociatedFile() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    File temp = null;
    try {
      temp = File.createTempFile("tempFile", ".tmp");
    } catch (IOException e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(temp);
    Assert.assertTrue(temp.exists());

    Assert.assertTrue(testObject.addAssociatedFile(path), "Checking that associated file was added");
    Assert.assertTrue(testObject.removeAssociatedFile(path), "Checking that associated file was removed");
  }

  /**
   * Test getting the associated files.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetAssociatedFiles() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    File temp = null;
    try {
      temp = File.createTempFile("tempfile", ".tmp");
    } catch (IOException e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(temp);
    Assert.assertTrue(temp.exists());

    final String path = temp.getAbsolutePath();
    Assert.assertTrue(testObject.addAssociatedFile(path), "Checking that associated file was added");
    Assert.assertNotNull(testObject.getAssociatedFiles(), "Checking that array is instantiated");
    Assert.assertEquals(testObject.getAssociatedFiles().size(), 1, "Checking that array is not empty");
  }

  /**
   * Test get array of associated files.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetArrayOfAssociatedFiles() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    File temp = null;
    try {
      temp = File.createTempFile("tempFile", ".tmp");
    } catch (IOException e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(temp);
    Assert.assertTrue(temp.exists());

    final String path = temp.getAbsolutePath();
    Assert.assertTrue(testObject.addAssociatedFile(path), "Checking that associated file was added");
    Assert.assertNotNull(testObject.getArrayOfAssociatedFiles(), "Checking that array is instantiated");
    Assert.assertEquals(testObject.getArrayOfAssociatedFiles().length, 1, "Checking that array is not empty");
  }

  /**
   * Test contains associated file.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testContainsAssociatedFile() {
    BaseTestObject testObject = (BaseTestObject) this.getTestObject();
    File temp = null;
    try {
      temp = File.createTempFile("tempFile", ".tmp");
    } catch (IOException e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(temp);
    Assert.assertTrue(temp.exists());

    final String path = temp.getAbsolutePath();
    Assert.assertTrue(testObject.addAssociatedFile(path), "Checking that associated file was added");
    Assert.assertNotNull(testObject.getArrayOfAssociatedFiles(), "Checking that array is instantiated");
    Assert.assertTrue(testObject.containsAssociatedFile(path), "Checking if array contains file");
  }

  private DriverManager<String> getDriverManager(ITestObject testObject, Supplier<String> supplier) {
    return new DriverManager<>(supplier, testObject) {
      @Override
      public void close() {
      }
    };
  }
}