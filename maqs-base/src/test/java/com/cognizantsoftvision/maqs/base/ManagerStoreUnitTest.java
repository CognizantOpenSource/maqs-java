/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import static org.testng.Assert.assertNotNull;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import java.util.function.Supplier;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Manager Dictionary unit test class.
 */
public class ManagerStoreUnitTest extends BaseGenericTest {

  /**
   * Test closing the manager store.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testClose() {
    ManagerStore managerStore = new ManagerStore();
    final String dm1 = "DM1";
    managerStore.put(dm1, getTestDriverManager());
    final String dm2 = "DM2";
    managerStore.put(dm2, getTestDriverManager());
    final String dm3 = "DM3";
    managerStore.put(dm3, getTestDriverManager());

    try {
      managerStore.close();
      Assert.assertFalse(managerStore.containsKey(dm1));
      Assert.assertFalse(managerStore.containsKey(dm2));
      Assert.assertFalse(managerStore.containsKey(dm3));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Test getting the manager driver.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetDriver() {
    final String dm1 = "DM1";
    try (ManagerStore managerStore = new ManagerStore()) {
      managerStore.putOrOverride(dm1, getTestDriverManager());
      Assert.assertTrue(managerStore.containsKey(dm1));
      assertNotNull(managerStore.getDriver(dm1));
    }
  }

  /**
   * Test adding to the manager store.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void testPut() {
    final String dm1 = "DM1";
    try (ManagerStore managerStore = new ManagerStore()) {
      managerStore.put(dm1, getTestDriverManager());
      Assert.assertTrue(managerStore.containsKey(dm1));
      assertNotNull(managerStore.get(dm1));

      this.getManagerStore().put(getTestDriverManager());
      this.getManagerStore().getManager("TEST");
    }
  }

  /**
   * gets the driver manager.
   * @return a test driver manager.
   */
  private TestDriverManager getTestDriverManager() {
    Supplier<Object> supplier = () -> "TEST";
    return new TestDriverManager(supplier, (BaseTestObject) getTestObject());
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testPutOrOverride() {
    try (ManagerStore managerStore = new ManagerStore()) {
      TestDriverManager testManager = getTestDriverManager();
      managerStore.putOrOverride(testManager);
      Assert.assertTrue(managerStore.containsValue(testManager));
    }
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testPutOrOverride1() {
    final String dm1 = "DM1";
    try (ManagerStore managerStore = new ManagerStore()) {
      TestDriverManager testManager = getTestDriverManager();
      managerStore.putOrOverride(dm1, testManager);
      Assert.assertTrue(managerStore.containsKey(dm1));
      assertNotNull(managerStore.get(dm1));
    }
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testRemove() {
    final String dm1 = "DM1";
    final String dm2 = "DM2";
    try (ManagerStore managerStore = new ManagerStore()) {
      managerStore.put(dm1, getTestDriverManager());
      managerStore.put(dm2, getTestDriverManager());
      Assert.assertTrue(managerStore.containsKey(dm1));
      Assert.assertTrue(managerStore.containsKey(dm2));
      System.out.println("Removing DM2 entry...");
      Assert.assertTrue(managerStore.remove(dm2), "Checking if remove reported as successful");
      Assert.assertTrue(managerStore.containsKey(dm1));
      Assert.assertFalse(managerStore.containsKey(dm2));
    }
  }

  /**
   * Test Driver Manager for Unit Tests.
   */
  private static class TestDriverManager extends DriverManager<Object> {
    TestDriverManager(Supplier<Object> getDriverFunction, BaseTestObject baseTestObject) {
      super(getDriverFunction, baseTestObject);
    }

    @Override
    public void close() {
      System.out.println("Closing Test Driver Manager...");
    }
  }
}