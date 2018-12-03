/*
 * Copyright 2018 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.utilities.unitTests;

import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Logging Configuration unit test class.
 */
public class LoggingConfigUnitTest {
    /**
     * Test getting Logging Enabled Setting - Config set to 'YES'
     */
    @Test
    public void getLoggingEnabledSettingTest() {
        Assert.assertEquals(LoggingConfig.getLoggingEnabledSetting(), LoggingEnabled.YES,
                "Expected Logging Enabled Setting YES");
    }

    /**
     * Test getting Logging Level Setting - Config set to 'VERBOSE'
     */
    @Test
    public void getLoggingLevelVerboseSettingTest() {
        Assert.assertEquals(MessageType.VERBOSE, LoggingConfig.getLoggingLevelSetting(),
                "Expected Logging Level Setting VERBOSE");
    }

    /**
     * Test getting File Logger
     * Config LogType set to 'TXT' which creates FileLogger
     */
    @Test
    public void getFileLoggerTest() {
        String fileName = "TestLog.txt";
        Logger logger = LoggingConfig.getLogger(fileName);
        Assert.assertTrue(logger instanceof FileLogger, "Expected Logger to be of Type FileLogger");
    }

    /**
     * Test getting Log Directory
     * Config value not defined - Compare to Default Path
     */
    @Test
    public void getLogDirectoryTest() {
        String defaultPath = new File("").getAbsolutePath().concat("\\Logs");
        Assert.assertEquals(LoggingConfig.getLogDirectory(), defaultPath, StringProcessor.safeFormatter(
            "Expected Default Path '{0)'", defaultPath));
    }
}
