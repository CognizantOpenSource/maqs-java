/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities;

import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;

import com.cognizantsoftvision.maqs.utilities.logging.ConsoleLogger;
import com.cognizantsoftvision.maqs.utilities.logging.FileLogger;
import com.cognizantsoftvision.maqs.utilities.logging.HtmlFileLogger;
import com.cognizantsoftvision.maqs.utilities.logging.Logger;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingConfig;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * The File Logger unit test class.
 *
 * Don't check for serial version unique IDs
 * as we will never serialize the test data
 */
@Test(singleThreaded = true)
public class FileLoggerUnitTest {

  public static final String LOG_FOLDER_MESSAGING_LEVEL_DIRECTORY = LoggingConfig.getLogDirectory() + "/"
      + "Log Folder Messaging Level Directory";

  @DataProvider(name = "logLevels")
  public static Object[][] data() {
    return new Object[][] { { "VERBOSE", new HashMap<String, Boolean>() {
      {
        put("VERBOSE", true);
        put("INFORMATION", true);
        put("GENERIC", true);
        put("SUCCESS", true);
        put("WARNING", true);
        put("ERROR", true);
      }
    } }, { "INFORMATION", new HashMap<String, Boolean>() {
      {
        put("VERBOSE", false);
        put("INFORMATION", true);
        put("GENERIC", true);
        put("SUCCESS", true);
        put("WARNING", true);
        put("ERROR", true);
      }
    } }, { "GENERIC", new HashMap<String, Boolean>() {
      {
        put("VERBOSE", false);
        put("INFORMATION", false);
        put("GENERIC", true);
        put("SUCCESS", true);
        put("WARNING", true);
        put("ERROR", true);
      }
    } }, { "SUCCESS", new HashMap<String, Boolean>() {
      {
        put("VERBOSE", false);
        put("INFORMATION", false);
        put("GENERIC", false);
        put("SUCCESS", true);
        put("WARNING", true);
        put("ERROR", true);
      }
    } }, { "WARNING", new HashMap<String, Boolean>() {
      {
        put("VERBOSE", false);
        put("INFORMATION", false);
        put("GENERIC", false);
        put("SUCCESS", false);
        put("WARNING", true);
        put("ERROR", true);
      }
    } }, { "ERROR", new HashMap<String, Boolean>() {
      {
        put("VERBOSE", false);
        put("INFORMATION", false);
        put("GENERIC", false);
        put("SUCCESS", false);
        put("WARNING", false);
        put("ERROR", true);
      }
    } }, { "SUSPENDED", new HashMap<String, Boolean>() {
      {
        put("VERBOSE", false);
        put("INFORMATION", false);
        put("GENERIC", false);
        put("SUCCESS", false);
        put("WARNING", false);
        put("ERROR", false);
      }
    } } };
  }

  /**
   * Verify the text file logger respects hierarchical logging
   *
   * @param logLevel The type of logging.
   * @param levels   What should appear for each level.
   */
  @Test(dataProvider = "logLevels")
  public void testHierarchicalTxtFileLogger(String logLevel, HashMap<String, Boolean> levels) {
    FileLogger logger = new FileLogger(true, LoggingConfig.getLogDirectory(),
        this.getFileName("TestHierarchicalTxtFileLogger_" + logLevel, "txt"), MessageType.GENERIC);
    this.testHierarchicalLogging(logger, logger.getFilePath(), logLevel, levels);

    deleteFile(logger);
  }

  /**
   * Verify the console logger respects hierarchical logging
   *
   * @param logLevel The type of logging.
   * @param levels   What should appear for each level.
   */
  @Test(dataProvider = "logLevels")
  public void testHierarchicalConsoleLogger(String logLevel, HashMap<String, Boolean> levels) {
    // Calculate a file path
    String path = Paths
        .get(LoggingConfig.getLogDirectory(), this.getFileName("TestHierarchicalConsoleLogger" + logLevel, "txt"))
        .toString();

    try (ConsoleCopy consoleCopy = new ConsoleCopy(path)) {
      ConsoleLogger consoleLogger = new ConsoleLogger();
      this.testHierarchicalLogging(consoleLogger, path, logLevel, levels);
    }

    File file = new File(path);
    Assert.assertTrue(file.delete());
  }

  /**
   * Verify the Html File logger respects hierarchical logging
   *
   * @param logLevel The type of logging.
   * @param levels   What should appear for each level.
   */
  @Test(dataProvider = "logLevels")
  public void testHierarchicalHtmlFileLogger(String logLevel, HashMap<String, Boolean> levels) {
    HtmlFileLogger logger = new HtmlFileLogger(true, LoggingConfig.getLogDirectory(),
        this.getFileName("TestHierarchicalHtmlFileLogger" + logLevel, "html"), MessageType.GENERIC);

    this.testHierarchicalLogging(logger, logger.getFilePath(), logLevel, levels);

    File file = new File(logger.getFilePath());
    logger.close();
    Assert.assertTrue(file.delete());
  }

  /**
   * Test logging to a new file.
   */
  @Test
  public void noAppendTest() {
    FileLogger logger = new FileLogger(false, "", "WriteToFileLogger");
    logger.logMessage(MessageType.WARNING, "Hello, this is a test.");
    deleteFile(logger);
  }

  /**
   * Test logging to an existing file.
   */
  @Test
  public void appendFileTest() {
    FileLogger logger = new FileLogger(true, "", "WriteToExistingFileLogger");
    logger.logMessage(MessageType.WARNING, "This is a test to write to an existing file.");
    deleteFile(logger);
  }

  /**
   * Verify the logging suspension functions
   */
  @Test
  public void testSuspendLogger() {
    SoftAssert softAssert = new SoftAssert();

    // Start logging
    FileLogger logger = new FileLogger(true, LoggingConfig.getLogDirectory(),
        this.getFileName("TestHierarchicalTxtFileLogger", "txt"), MessageType.GENERIC);
    logger.setLoggingLevel(MessageType.VERBOSE);

    logger.logMessage(MessageType.VERBOSE, "HellO");

    // Suspend logging
    logger.suspendLogging();
    logger.logMessage(MessageType.ERROR, "GoodByE");

    // Continue logging
    logger.continueLogging();
    logger.logMessage(MessageType.VERBOSE, "BacK");

    // Get the log file content
    String logContents = this.readTextFile(logger.getFilePath());

    // Verify that logging was active
    boolean helloFound = logContents.contains("HellO");
    softAssert.assertTrue(helloFound, "'HellO' was not found.  Logging Failed");

    // Verify that logging was suspended
    boolean goodbyeFound = logContents.contains("GoodByE");
    softAssert.assertFalse(goodbyeFound, "'GoodByE' was found when it should not be written.  Logging Failed");

    // Verify that logging was active
    boolean backFound = logContents.contains("BacK");
    softAssert.assertTrue(backFound, "'BacK' was not found.  Logging Failed");

    // Fail the test if any soft asserts failed
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Test Writing to the File Logger
   */
  @Test
  public void writeToFileLogger() {
    FileLogger logger = new FileLogger("", "WriteToFileLogger");
    logger.logMessage(MessageType.WARNING, "Hello, this is a test.");

    deleteFile(logger);
  }

  /**
   * Test Writing to an Existing File Logger
   */
  @Test
  public void writeToExistingFileLogger() {
    FileLogger logger = new FileLogger(true, "", "WriteToExistingFileLogger", MessageType.GENERIC);
    logger.logMessage(MessageType.WARNING, "This is a test.");
    logger.logMessage(MessageType.WARNING, "This is a test to write to an existing file.");

    deleteFile(logger);
  }

  /**
   * Verify FileLogger constructor creates the correct directory if it does not already exist.
   * Delete Directory after each run.
   * 
   * @throws IOException if an exception is thrown
   */
    @Ignore
    @Test
    public void constructorCreateDirectory() throws IOException {
      String message = "Test to ensure that the file in the created directory can be written to.";
      FileLogger logger = new FileLogger(true,
      Paths.get(LoggingConfig.getLogDirectory(), "FileLoggerCreateDirectoryDelete").toString(),
          "FileLoggerCreateDirectory", MessageType.GENERIC);

      logger.logMessage(MessageType.WARNING,
      "Test to ensure that the file in the created directory can be written to.");

      File file = new File(logger.getFilePath()); String actualMessage =
      this.readTextFile(file.getCanonicalPath());
      Assert.assertTrue(actualMessage.contains(message), "Expected '" + message +
      "' but got '" + actualMessage + "' for: " + file.getCanonicalPath());
      deleteFile(logger);
   }



  /**
   * Verify that File Logger can log message without defining a Message Type
   */
  @Test
  public void loggerLogMessage() {
    FileLogger logger = new FileLogger(true, "", "FileLoggerLogMessage");
    logger.logMessage("Test to ensure LogMessage works as expected.");
    Assert.assertTrue(this.readTextFile(logger.getFilePath()).contains("Test to ensure LogMessage works as expected."),
        "Expected Log Message to be contained in log.");

    deleteFile(logger);
  }

  /**
   * Verify that File Logger can log message and defining a Message Type
   */
  @Test
  public void logMessageSelectType() {
    FileLogger logger = new FileLogger(true, "", "FileLoggerLogMessage");
    logger.logMessage(MessageType.GENERIC, "Test to ensure LogMessage works as expected.");
    Assert.assertTrue(this.readTextFile(logger.getFilePath()).contains("Test to ensure LogMessage works as expected."),
        "Expected Log Message to be contained in log.");

    deleteFile(logger);
  }

  /**
   * Verify that File Path field can be accessed and updated
   */
  @Test
  public void setFilePath() {
    FileLogger logger = new FileLogger(true, "", "FileLoggerSetFilePath", MessageType.GENERIC);
    logger.setFilePath("test file path");
    Assert.assertEquals(logger.getFilePath(), "test file path");

    deleteFile(logger);
  }

  /**
   * Verify that File Logger catches and handles errors caused by incorrect file
   * Paths
   */
  @Test
  public void catchThrownException() {
    FileLogger logger = new FileLogger(true, "", "FileLoggerCatchThrownException", MessageType.GENERIC);
    logger.setFilePath("<>");

    logger.logMessage(MessageType.GENERIC, "test throws error");
    deleteFile(logger);
  }

  /**
   * Test File Logger with empty file name throws Illegal Argument Exception.
   */
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void emptyFileNameException() {
    new FileLogger("");
  }

  /**
   * Verify File Logger with No Parameters assigns the correct default values.
   */
  @Test
  public void noParameters() {
    FileLogger logger = new FileLogger();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("FileLog.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only append parameter assigns the correct default
   * values.
   */
  @Test
  public void appendOnly() {
    FileLogger logger = new FileLogger(true);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("FileLog.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only File Name parameter assigns the correct default
   * values. Verify default extension is added '.txt'
   */
  @Test
  public void nameOnlyAddExtension() {
    FileLogger logger = new FileLogger("FileNameOnly");

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("FileNameOnly.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only Message Type parameter assigns the correct
   * default values.
   */
  @Test
  public void messageTypeOnly() {
    FileLogger logger = new FileLogger(MessageType.WARNING);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("FileLog.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.WARNING, logger.getMessageType(), "Expected Warning Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only Append and File Name parameters assigns the
   * correct default values.
   */
  @Test
  public void appendFileName() {
    FileLogger logger = new FileLogger(true, "AppendFileName");

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("AppendFileName.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only Log Folder and Append parameters assigns the
   * correct default values.
   */
  @Test
  public void appendLogFolder() {
    final String append_file_directory = LoggingConfig.getLogDirectory() + "/" + "Append File Directory";
    FileLogger logger = new FileLogger(append_file_directory, true);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(append_file_directory, logger.getDirectory(),
        "Expected Directory 'Append File Directory'.");
    softAssert.assertEquals("FileLog.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
    deleteDirectory(logger);
  }

  /**
   * Verify File Logger with only Log Folder and File Name parameters assigns the
   * correct default values.
   */
  @Test
  public void logFolderFileName() {
    final String log_folder_file_name_directory = LoggingConfig.getLogDirectory() + "/"
        + "Log Folder File Name Directory";
    FileLogger logger = new FileLogger(log_folder_file_name_directory, "LogFolderFileName.txt");

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(log_folder_file_name_directory, logger.getDirectory(),
        "Expected Directory 'Log Folder File Name Directory'.");
    softAssert.assertEquals("LogFolderFileName.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
    deleteDirectory(logger);
  }

  /**
   * Verify File Logger with only Log Folder and Messaging Level parameters
   * assigns the correct default values.
   */
  @Test
  public void logFolderMessagingLevel() {
    FileLogger logger = new FileLogger(LOG_FOLDER_MESSAGING_LEVEL_DIRECTORY, MessageType.WARNING);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(LOG_FOLDER_MESSAGING_LEVEL_DIRECTORY, logger.getDirectory(),
        "Expected Directory 'Log Folder Messaging Level Directory'.");
    softAssert.assertEquals("FileLog.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.WARNING, logger.getMessageType(), "Expected Warning Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
    deleteDirectory(logger);
  }

  /**
   * Verify File Logger with only Append and Messaging Level parameters assigns
   * the correct default values.
   */
  @Test
  public void appendMessagingLevel() {
    FileLogger logger = new FileLogger(true, MessageType.WARNING);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("FileLog.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.WARNING, logger.getMessageType(), "Expected Warning Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only Messaging Level and file name parameters assigns
   * the correct default values.
   */
  @Test
  public void messagingLevelFileName() {
    FileLogger logger = new FileLogger(MessageType.WARNING, "MessagingTypeFile.txt");

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("MessagingTypeFile.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.WARNING, logger.getMessageType(), "Expected Warning Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only Append, log folder and file name parameters
   * assigns the correct default values.
   */
  @Test
  public void appendLogFolderFileName() {
    final String appendLogFolderFileNameDirectory = LoggingConfig.getLogDirectory() + "/"
        + "AppendLogFolderFileNameDirectory";
    FileLogger logger = new FileLogger(true, appendLogFolderFileNameDirectory, "AppendLogFolderFileName.txt");

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(appendLogFolderFileNameDirectory, logger.getDirectory(),
        " Expected Directory AppendLogFolderFileNameDirectory");
    softAssert.assertEquals("AppendLogFolderFileName.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
    deleteDirectory(logger);
  }

  /**
   * Verify File Logger with only Append, log folder and Messaging Level
   * parameters assigns the correct default values.
   */
  @Test
  public void appendLogFolderMessagingLevel() {
    final String appendLogFolderMessagingLevelDirectory = LoggingConfig.getLogDirectory() + "/"
        + "AppendLogFolderMessagingLevelDirectory";
    FileLogger logger = new FileLogger(true, appendLogFolderMessagingLevelDirectory, MessageType.WARNING);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(appendLogFolderMessagingLevelDirectory, logger.getDirectory(),
        " Expected Directory AppendLogFolderMessagingLevelDirectory");
    softAssert.assertEquals("FileLog.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.WARNING, logger.getMessageType(), "Expected Warning Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
    deleteDirectory(logger);
  }

  /**
   * Verify File Logger with only File Name, Append and Messaging Level parameters
   * assigns the correct default values.
   */
  @Test
  public void fileNameAppendMessagingLevel() {
    FileLogger logger = new FileLogger("FileNameAppendMessagingLevel.txt", true, MessageType.WARNING);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("FileNameAppendMessagingLevel.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.WARNING, logger.getMessageType(), "Expected Warning Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only Log Folder, File Name and Messaging Level
   * parameters assigns the correct default values.
   */
  @Test
  public void logFolderFileNameMessagingLevel() {
    final String logFolderFileNameMessagingLevelDirectory = LoggingConfig.getLogDirectory() + "/"
        + "LogFolderFileNameMessagingLevelDirectory";
    FileLogger logger = new FileLogger(logFolderFileNameMessagingLevelDirectory, "LogFolderFileNameMessagingLevel.txt",
        MessageType.WARNING);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(logFolderFileNameMessagingLevelDirectory, logger.getDirectory(),
        "Expected Directory 'LogFolderFileNameMessagingLevelDirectory'");
    softAssert.assertEquals("LogFolderFileNameMessagingLevel.txt", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.WARNING, logger.getMessageType(), "Expected Warning Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
    deleteDirectory(logger);
  }

  /**
   * Verify hierarchical logging is respected
   *
   * @param logger       The logger we are checking
   * @param filePath     Where the log output can be found
   * @param logLevelText The type of logging
   * @param levels       What should appear for each level
   */
  private void testHierarchicalLogging(Logger logger, String filePath, String logLevelText,
      HashMap<String, Boolean> levels) {
    // Create a soft assert
    SoftAssert softAssert = new SoftAssert();

    // Get the log level
    MessageType logLevel = MessageType.valueOf(logLevelText);
    logger.setLoggingLevel(logLevel);

    // Set the logger options to set the log level and add log entries to the file
    logger.logMessage(logLevel, System.lineSeparator() + "The Log level is set to " + logLevel);

    // Message template
    String logLine = "Test Log item %s";

    // Log the test messages
    logger.logMessage(MessageType.VERBOSE, logLine, MessageType.VERBOSE);
    logger.logMessage(MessageType.INFORMATION, logLine, MessageType.INFORMATION);
    logger.logMessage(MessageType.GENERIC, logLine, MessageType.GENERIC);
    logger.logMessage(MessageType.SUCCESS, logLine, MessageType.SUCCESS);
    logger.logMessage(MessageType.WARNING, logLine, MessageType.WARNING);
    logger.logMessage(MessageType.ERROR, logLine, MessageType.ERROR);

    // Gives the writing time
    try {
      TimeUnit.MILLISECONDS.sleep(250);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Get the file content
    String logContents = this.readTextFile(filePath);

    // Verify that only the logged messages at the log level or below are logged
    for (HashMap.Entry<String, Boolean> level : levels.entrySet()) {
      if ((!level.getKey().equals("Row")) && (!level.getKey().equals("LogLevel"))) {
        // Verify if the Message Type is found
        boolean logMessageFound = logContents.contains(String.format(logLine, level.getKey()));
        softAssert.assertEquals(Boolean.toString(logMessageFound), level.getValue().toString(),
            "Looking for '" + String.format(logLine, level.getKey()) + "' with Logger of type '" + logLevel.name()
                + "'." + System.lineSeparator() + "Log Contents: " + logContents);
      }
    }

    // Set the log level so that the soft asserts log
    logger.setLoggingLevel(MessageType.VERBOSE);

    // Fail test if any soft asserts failed
    softAssert.assertAll();
  }

  /**
   * Read a file and return it as a string
   *
   * @param filePath The file path to read
   * @return The contents of the file
   */
  private String readTextFile(String filePath) {
    String text = "";
    try {
      text = new String(Files.readAllBytes(Paths.get(filePath)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return text;
  }

  /**
   * Get a unique file name
   *
   * @param testName  Prepend text
   * @param extension The file extension
   * @return A unique file name
   */
  private String getFileName(String testName, String extension) {
    return StringProcessor.safeFormatter("UtilitiesUnitTesting.%s-%s.%s", testName, UUID.randomUUID().toString(),
        extension);
  }

  private void deleteFile(FileLogger logger) {
    File file = new File(logger.getFilePath());

    if (file.exists()) {
      Assert.assertTrue(file.delete());
    }
  }

  private void deleteDirectory(FileLogger logger) {
    File file = new File(logger.getDirectory());

    if (file.exists()) {
      Assert.assertTrue(file.delete());
    }
  }
}
