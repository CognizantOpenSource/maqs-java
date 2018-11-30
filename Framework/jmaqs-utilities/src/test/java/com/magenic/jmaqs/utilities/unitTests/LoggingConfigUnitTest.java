package com.magenic.jmaqs.utilities.unitTests;

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
        Assert.assertEquals(LoggingEnabled.YES, LoggingConfig.getLoggingEnabledSetting());
    }

    /**
     * Test getting Logging Level Setting - Config set to 'VERBOSE'
     */
    @Test
    public void getLoggingLevelSettingTest() {
        Assert.assertEquals(MessageType.VERBOSE, LoggingConfig.getLoggingLevelSetting());
    }

    /**
     * Test getting Logger - Config set to 'TXT' which creates FileLogger
     */
    @Test
    public void getLoggerTest() {
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
        Assert.assertEquals(defaultPath, LoggingConfig.getLogDirectory());
    }
}
