/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logger;

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import com.cognizantsoftvision.maqs.utilities.helper.exceptions.MaqsLoggingConfigException;
import com.cognizantsoftvision.maqs.utilities.logging.ConsoleLogger;
import com.cognizantsoftvision.maqs.utilities.logging.FileLogger;
import com.cognizantsoftvision.maqs.utilities.logging.Logger;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingConfig;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingEnabled;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import java.io.File;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Logging Configuration unit test class.
 * Tests running in serial.
 */
@Test(singleThreaded = true)
public class LoggingConfigUnitTest {

    /**
     * Test getting Logging Enabled Setting. Override Config to 'YES'
     */
    @Test
    public void getLoggingEnabledSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("Log", "YES");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingEnabledSetting(), LoggingEnabled.YES,
                "Expected Logging Enabled Setting YES.");
    }

    /**
     * Test getting Logging Enabled Setting. Override Config to 'ONFAIL'
     */
    @Test
    public void getLoggingEnabledOnFailSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("Log", "ONFAIL");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingEnabledSetting(), LoggingEnabled.ONFAIL,
                "Expected Logging Enabled Setting ONFAIL.");
    }

    /**
     * Test getting Logging Enabled Setting. Override Config to 'NO'
     */
    @Test
    public void getLoggingDisabledSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("Log", "NO");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingEnabledSetting(), LoggingEnabled.NO,
                "Expected Logging Enabled Setting NO.");
    }

    /**
     * Test getting Logging Enabled Setting with an Illegal Argument Override Config
     * to 'INVALIDVALUE' - Expect IllegalArgumentException
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getLoggingSettingIllegalArgumentTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("Log", "INVALIDVALUE");
        Config.addGeneralTestSettingValues(newValueMap, true);
        LoggingConfig.getLoggingEnabledSetting();
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'VERBOSE'
     */
    @Test
    public void getLoggingLevelVerboseSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "VERBOSE");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.VERBOSE,
                "Expected Logging Level Setting VERBOSE.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'INFORMATION'
     */
    @Test
    public void getLoggingLevelInformationSettingTest() throws MaqsLoggingConfigException {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "INFORMATION");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.INFORMATION,
                "Expected Logging Level Setting INFORMATION.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'INFORMATION'
     */
    @Test
    public void getLoggingLevelActionSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "ACTION");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.ACTION,
            "Expected Logging Level Setting ACTION.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'GENERIC'
     */
    @Test
    public void getLoggingLevelGenericSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "GENERIC");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals( LoggingConfig.getLoggingLevelSetting(), MessageType.GENERIC,
                "Expected Logging Level Setting GENERIC.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'SUCCESS'
     */
    @Test
    public void getLoggingLevelSuccessSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "SUCCESS");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.SUCCESS,
                "Expected Logging Level Setting SUCCESS.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'WARNING'
     */
    @Test
    public void getLoggingLevelWarningSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "WARNING");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.WARNING,
                "Expected Logging Level Setting WARNING.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'ERROR'
     */
    @Test
    public void getLoggingLevelErrorSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "ERROR");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.ERROR,
                "Expected Logging Level Setting ERROR.");
    }

    /**
     * Test getting Logging Level Setting. Override Config to 'SUSPENDED'
     */
    @Test
    public void getLoggingLevelSuspendedSettingTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "SUSPENDED");
        Config.addGeneralTestSettingValues(newValueMap, true);
        Assert.assertEquals(LoggingConfig.getLoggingLevelSetting(), MessageType.SUSPENDED,
                "Expected Logging Level Setting SUSPENDED.");
    }

    /**
     * Test getting Logging Level Setting with Illegal Argument. Override Config to
     * 'INVALIDVALUE' - Expect IllegalArgumentException
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getLoggingLevelIllegalArgumentTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogLevel", "INVALIDVALUE");
        Config.addGeneralTestSettingValues(newValueMap, true);
        LoggingConfig.getLoggingLevelSetting();
    }

    /**
     * Test getting File Logger. Override Config LogType to 'TXT' which creates
     * FileLogger.
     */
    @Test
    public void getTextFileLoggerTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogType", "TXT");
        newValueMap.put("Log", "YES");
        Config.addGeneralTestSettingValues(newValueMap, true);
        String fileName = "TestLog.txt";
        Logger logger = LoggingConfig.getLogger(fileName);
        Assert.assertTrue(logger instanceof FileLogger, "Expected Logger to be of Type FileLogger.");
    }

    /**
     * Test getting File Logger. Override Config LogType to 'TXT' which creates
     * FileLogger.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getInvalidFileLoggerTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogType", "XML");
        newValueMap.put("Log", "YES");
        Config.addGeneralTestSettingValues(newValueMap, true);
        String fileName = "TestLog.txt";
        LoggingConfig.getLogger(fileName);
    }

    /**
     * Test getting File Logger. Override Config LogType to 'CONSOLE' which creates
     * ConsoleLogger.
     */
    @Test
    public void getConsoleLoggerTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("LogType", "CONSOLE");
        newValueMap.put("Log", "YES");
        Config.addGeneralTestSettingValues(newValueMap, true);
        String fileName = "TestLog.txt";
        Logger logger = LoggingConfig.getLogger(fileName);
        Assert.assertTrue(logger instanceof ConsoleLogger, "Expected Logger to be of Type ConsoleLogger.");
    }

    /**
     * Test getting File Logger. Override Config Log to 'NO' which creates
     * ConsoleLogger by default.
     */
    @Test
    public void getConsoleLoggerLoggingDisabledTest() {
        HashMap<String, String> newValueMap = new HashMap<>();
        newValueMap.put("Log", "NO");
        Config.addGeneralTestSettingValues(newValueMap, true);
        String fileName = "TestLog.txt";
        Logger logger = LoggingConfig.getLogger(fileName);
        Assert.assertTrue(logger instanceof ConsoleLogger, "Expected Logger to be of Type ConsoleLogger.");
    }

    /**
     * Test getting Log Directory.
     * Config value not defined - Compare to Default Path.
     */
    @Test
    public void getLogDirectoryTest() {
        String defaultPath = new File("").getAbsolutePath().concat(
            File.separator + "target" + File.separator + "logs");
        Assert.assertEquals(defaultPath, LoggingConfig.getLogDirectory(),
        StringProcessor.safeFormatter( "Expected Default Path '{0)'.", defaultPath));
    }
}
