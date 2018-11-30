/*
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.utilities.unitTests;

import com.magenic.jmaqs.utilities.helper.Config;
import com.magenic.jmaqs.utilities.logging.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

/**
 * Logging Configuration unit test class.
 */
public class LoggingConfigUnitTest {
    /**
     * Test getting Logging Enabled Setting
     * Override Config to 'YES'
     */
    @Test
    public void getLoggingEnabledSettingTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("Log", "YES");
        Config.addGeneralTestSettingValues(newValueMap, true);

        Assert.assertEquals(LoggingEnabled.YES, LoggingConfig.getLoggingEnabledSetting());
    }

    /**
     * Test getting Logging Enabled Setting
     * Override Config to 'ONFAIL'
     */
    @Test
    public void getLoggingEnabledOnFailSettingTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("Log", "ONFAIL");
        Config.addGeneralTestSettingValues(newValueMap, true);

        Assert.assertEquals(LoggingEnabled.ONFAIL, LoggingConfig.getLoggingEnabledSetting());
    }

    /**
     * Test getting Logging Enabled Setting
     * Override Config to 'NO'
     */
    @Test
    public void getLoggingDisabledSettingTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("Log", "NO");
        Config.addGeneralTestSettingValues(newValueMap, true);

        Assert.assertEquals(LoggingEnabled.NO, LoggingConfig.getLoggingEnabledSetting());
    }

    /**
     * Test getting Logging Level Setting
     * Override Config to 'VERBOSE'
     */
    @Test
    public void getLoggingLevelVerboseSettingTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("LogLevel", "VERBOSE");
        Config.addGeneralTestSettingValues(newValueMap, true);

        Assert.assertEquals(MessageType.VERBOSE, LoggingConfig.getLoggingLevelSetting());
    }

    /**
     * Test getting Logging Level Setting
     * Override Config to 'INFORMATION'
     */
    @Test
    public void getLoggingLevelInformationSettingTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("LogLevel", "INFORMATION");
        Config.addGeneralTestSettingValues(newValueMap, true);

        Assert.assertEquals(MessageType.INFORMATION, LoggingConfig.getLoggingLevelSetting());
    }

    /**
     * Test getting Logging Level Setting
     * Override Config to 'GENERIC'
     */
    @Test
    public void getLoggingLevelGenericSettingTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("LogLevel", "GENERIC");
        Config.addGeneralTestSettingValues(newValueMap, true);

        Assert.assertEquals(MessageType.GENERIC, LoggingConfig.getLoggingLevelSetting());
    }

    /**
     * Test getting Logging Level Setting
     * Override Config to 'SUCCESS'
     */
    @Test
    public void getLoggingLevelSuccessSettingTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("LogLevel", "SUCCESS");
        Config.addGeneralTestSettingValues(newValueMap, true);

        Assert.assertEquals(MessageType.SUCCESS, LoggingConfig.getLoggingLevelSetting());
    }

    /**
     * Test getting Logging Level Setting
     * Override Config to 'WARNING'
     */
    @Test
    public void getLoggingLevelWarningSettingTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("LogLevel", "WARNING");
        Config.addGeneralTestSettingValues(newValueMap, true);

        Assert.assertEquals(MessageType.WARNING, LoggingConfig.getLoggingLevelSetting());
    }

    /**
     * Test getting Logging Level Setting
     * Override Config to 'SUSPENDED'
     */
    @Test
    public void getLoggingLevelSuspendedSettingTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("LogLevel", "SUSPENDED");
        Config.addGeneralTestSettingValues(newValueMap, true);

        Assert.assertEquals(MessageType.SUSPENDED, LoggingConfig.getLoggingLevelSetting());
    }

    /**
     * Test getting File Logger
     * Override Config LogType to 'TXT' which creates FileLogger
     */
    @Test
    public void getFileLoggerTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("LogType", "TXT");
        newValueMap.put("Log", "YES");
        Config.addGeneralTestSettingValues(newValueMap, true);

        String fileName = "TestLog.txt";
        Logger logger = LoggingConfig.getLogger(fileName);
        Assert.assertTrue(logger instanceof FileLogger, "Expected Logger to be of Type FileLogger");
    }

    /**
     * Test getting File Logger
     * Override Config LogType to 'CONSOLE' which creates ConsoleLogger
     */
    @Test
    public void getConsoleLoggerTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("LogType", "CONSOLE");
        newValueMap.put("Log", "YES");
        Config.addGeneralTestSettingValues(newValueMap, true);

        String fileName = "TestLog.txt";
        Logger logger = LoggingConfig.getLogger(fileName);
        Assert.assertTrue(logger instanceof ConsoleLogger, "Expected Logger to be of Type ConsoleLogger");
    }

    /**
     * Test getting File Logger
     * Override Config Log to 'NO' which creates ConsoleLogger by default
     */
    @Test
    public void getConsoleLoggerLoggingDisabledTest() {
        HashMap<String, String> newValueMap = new HashMap();
        newValueMap.put("Log", "NO");
        Config.addGeneralTestSettingValues(newValueMap, true);

        String fileName = "TestLog.txt";
        Logger logger = LoggingConfig.getLogger(fileName);
        Assert.assertTrue(logger instanceof ConsoleLogger, "Expected Logger to be of Type ConsoleLogger");
    }

    /**
     * Test getting Log Directory
     * Config value not defined - Compare to Default Path
     */
    @Test
    public void getLogDirectoryTest() {
        String defaultPath = new File("").getAbsolutePath().concat("\\Logs");
        Assert.assertEquals(defaultPath, LoggingConfig.getLogDirectory());
    }
}
