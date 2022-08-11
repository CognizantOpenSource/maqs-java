/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.performance.PerfTimerCollection;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * The JUnit Base Test Object unit test.
 */
class JUnitBaseTestObjectUnitTest extends BaseGenericTest {

    /**
     * Test Constructor with Log and Method.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testBaseTestObject1() {
        final ITestObject testObject = this.getTestObject();

        // final String methodName = this.method.getName();
        BaseTestObject baseTestObject = new BaseTestObject(testObject.getLogger(), "FakeTestName");
        Assertions.assertNotNull(baseTestObject, "Checking that Base Test Object instantiated correctly");
    }

    /**
     * Test Constructor with Test Object.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testBaseTestObject2() {
        final ITestObject testObject = this.getTestObject();
        BaseTestObject baseTestObject = new BaseTestObject(testObject);
        Assertions.assertNotNull(baseTestObject, "Checking that Base Test Object instantiated correctly");

    }

    /**
     * Test set value.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testSetValue() {
        ITestObject testObject = this.getTestObject();
        final String key = "SetKey";
        final String value = "SetKey Value";
        testObject.setValue(key, value);
        Assertions.assertTrue(testObject.getValues().containsKey(key), "Checking that key exists in test object dictionary");
        Assertions.assertEquals(testObject.getValues().get(key), value, "Checking that value set correctly");
    }

    /**
     * Test set object.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testSetObject() {
        ITestObject testObject = this.getTestObject();
        final String key = "SetObject";
        final Object object = new Object();
        testObject.setObject(key, object);
        Assertions.assertTrue(testObject.getObjects().containsKey(key), "Checking that key exists in test object dictionary");
        Assertions.assertEquals(testObject.getObjects().get(key), object, "Checking that value set correctly");
    }

    /**
     * Test get log.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testGetLog() {
        ITestObject testObject = this.getTestObject();
        Assertions.assertNotNull(testObject.getLogger(), "Checking that logger is not null.");
    }

    /**
     * Test set log.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testSetLog() {
        ITestObject testObject = this.getTestObject();
        final ILogger logger = this.getLogger();
        testObject.setLogger(logger);
        Assertions.assertEquals(testObject.getLogger(), logger, "Checking that logger set correctly.");
    }

    /**
     * Test Get Perf Collection Timer - Not Null.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testGetPerfTimerCollectionNotNull() {
        ITestObject testObject = this.getTestObject();
        Assertions.assertNotNull(testObject.getPerfTimerCollection(), "Checking that logger is not null.");
    }

    /**
     * Test Set Perf Collection Timer - Get/Set.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testSetPerfTimerCollectionGetSet() {
        ITestObject testObject = this.getTestObject();
        final PerfTimerCollection perfTimerCollection = new PerfTimerCollection(testObject.getLogger(), "FakeTestName");
        testObject.setPerfTimerCollection(perfTimerCollection);
        Assertions.assertEquals(testObject.getPerfTimerCollection(), perfTimerCollection,
                "Checking that perf timer collection set correctly.");
    }

    /**
     * Test get values.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testGetValues() {
        ITestObject testObject = this.getTestObject();
        Assertions.assertNotNull(testObject.getValues(), "Checking that values is not null.");
    }

    /**
     * Test get objects.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testGetObjects() {
        ITestObject testObject = this.getTestObject();
        Assertions.assertNotNull(testObject.getObjects(), "Checking that objects is not null.");
    }

    /**
     * Test Get Manager Store - Not Null.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testGetManagerStoreNotNull() {
        ITestObject testObject = this.getTestObject();
        Assertions.assertNotNull(testObject.getManagerStore(), "Checking that objects is not null.");
    }

    /**
     * Test add driver manager.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testAddDriverManager() {
        ITestObject testObject = this.getTestObject();
        final Supplier<String> supplier = () -> null;
        DriverManager<String> driverManager = getDriverManager(testObject, supplier);
        Assertions.assertEquals(0, testObject.getManagerStore().size(), "Checking that manager store is empty");
        testObject.addDriverManager(driverManager);
        Assertions.assertEquals(1, testObject.getManagerStore().size(), "Checking that manager store has 1 object added");

    }

    /**
     * Test add driver manager - Overwrite True.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testAddDriverManagerTrue() {
        ITestObject testObject = this.getTestObject();
        final Supplier<String> supplier = () -> null;
        final DriverManager<String> driverManager = getDriverManager(testObject, supplier);
        final DriverManager<String> driverManager2 = getDriverManager(testObject, supplier);
        Assertions.assertEquals( 0, testObject.getManagerStore().size(), "Checking that manager store is empty");
        testObject.addDriverManager(driverManager, true);
        Assertions.assertEquals(1, testObject.getManagerStore().size(), "Checking that manager store has 1 object added");
        testObject.addDriverManager(driverManager2, true);
        Assertions.assertEquals(1, testObject.getManagerStore().size(), "Checking that manager store has 1 object added");
    }

    /**
     * Test add driver manager - Overwrite False.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testAddDriverManagerFalse() {
        ITestObject testObject = this.getTestObject();
        final Supplier<String> supplier = () -> null;
        final DriverManager<String> driverManager = getDriverManager(testObject, supplier);

        Assertions.assertEquals(0, testObject.getManagerStore().size(), "Checking that manager store is empty");
        testObject.addDriverManager(driverManager, false);
        Assertions.assertEquals(1, testObject.getManagerStore().size(), "Checking that manager store has 1 object added");
    }

    /**
     * Test add driver manager 2.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testAddDriverManager2() {
        ITestObject testObject = this.getTestObject();
        final Supplier<String> supplier = () -> null;
        final DriverManager<String> driverManager = getDriverManager(testObject, supplier);
        final String key = "DriverManager1";
        Assertions.assertEquals(0, testObject.getManagerStore().size(), "Checking that manager store is empty");
        testObject.addDriverManager(key, driverManager);
        Assertions.assertEquals(1, testObject.getManagerStore().size(), "Checking that manager store has 1 object added");
        Assertions.assertTrue(testObject.getManagerStore().containsKey(key), "Checking if key exists in Manager Store");
    }

    /**
     * Test close.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testClose() throws Exception {
        ITestObject testObject = this.getTestObject();
        final Supplier<String> supplier = () -> null;
        final DriverManager<String> driverManager = getDriverManager(testObject, supplier);
        final String key = "DriverManager1";
        testObject.addDriverManager(key, driverManager);
        testObject.close();
        Assertions.assertNull(testObject.getManagerStore(), "Checking that manager store has been closed");
        Assertions.assertEquals(0, testObject.getValues().size(), "Checking if values in manager store are closed");

    }

    /**
     * Test add associated file.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testAddAssociatedFile() {
        ITestObject testObject = this.getTestObject();
        File temp = null;
        try {
            temp = File.createTempFile("tempFile", ".tmp");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(Objects.requireNonNull(temp).exists());
        Assertions.assertTrue(testObject.addAssociatedFile(temp.getAbsolutePath()), "Checking that associated file was added");
        Assertions.assertEquals(1, testObject.getArrayOfAssociatedFiles().length, "Checking that one file was added to array.");
    }

    /**
     * Test remove associated file.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testRemoveAssociatedFile() {
        ITestObject testObject = this.getTestObject();
        File temp = null;
        try {
            temp = File.createTempFile("tempFile", ".tmp");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(Objects.requireNonNull(temp).exists());
        final String path = temp.getAbsolutePath();

        Assertions.assertTrue(testObject.addAssociatedFile(path), "Checking that associated file was added");
        Assertions.assertTrue(testObject.removeAssociatedFile(path), "Checking that associated file was removed");
    }

    /**
     * Test get array of associated files.
     */
    @Test
    @Tag(TestCategories.FRAMEWORK)
    void testGetArrayOfAssociatedFiles() {
        ITestObject testObject = this.getTestObject();
        File temp = null;
        try {
            temp = File.createTempFile("tempFile", ".tmp");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(Objects.requireNonNull(temp).exists());
        final String path = temp.getAbsolutePath();
        Assertions.assertTrue(testObject.addAssociatedFile(path), "Checking that associated file was added");
        Assertions.assertNotNull(testObject.getArrayOfAssociatedFiles(), "Checking that array is instantiated");
        Assertions.assertEquals(1, testObject.getArrayOfAssociatedFiles().length, "Checking that array is not empty");
    }

    /**
     * Test contains associated file.
     */
    @Test()
    @Tag(TestCategories.FRAMEWORK)
    void testContainsAssociatedFile() {
        ITestObject testObject = this.getTestObject();
        File temp = null;
        try {
            temp = File.createTempFile("tempFile", ".tmp");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(Objects.requireNonNull(temp).exists());
        final String path = temp.getAbsolutePath();
        Assertions.assertTrue(testObject.addAssociatedFile(path), "Checking that associated file was added");
        Assertions.assertNotNull(testObject.getArrayOfAssociatedFiles(), "Checking that array is instantiated");
        Assertions.assertTrue(testObject.containsAssociatedFile(path), "Checking if array contains file");
    }

    // Test Setup Objects
    /*
     * private Logger getLogger() { return new Logger() {
     *
     * @Override public void logMessage(MessageType messageType, String message,
     * Object... args) {
     *
     * }
     *
     * @Override public void logMessage(String message, Object... args) {
     *
     * } }; }
     */

    private DriverManager<String> getDriverManager(ITestObject testObject, Supplier<String> supplier) {
        return new DriverManager<>(supplier, testObject) {
            @Override public void close() {
            }
        };
    }
}