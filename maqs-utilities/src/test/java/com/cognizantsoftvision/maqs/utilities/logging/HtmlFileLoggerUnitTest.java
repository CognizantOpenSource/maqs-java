/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The Html File Logger unit test class.
 */
@Test
public class HtmlFileLoggerUnitTest {

  /**
   * Test logging to a new file.
   */
  @Test
  public void doNotAppendLoggerTest() {
    HtmlFileLogger logger = new HtmlFileLogger(false, "", "WriteToHtmlFileLogger");
    logger.logMessage(MessageType.WARNING, "Hello, this is a test.");
    deleteFile(logger);
  }

  /**
   * Test logging to an existing file.
   */
  @Test
  public void appendFileTest() {
    HtmlFileLogger logger = new HtmlFileLogger(true, "", "WriteToExistingHtmlFileLogger");
    logger.logMessage(MessageType.WARNING, "This is a test to write to an existing file.");
    logger.logMessage(MessageType.WARNING, "This is a test to append to current file.");
    deleteFile(logger);
  }

  /**
   * Test Writing to the Html File Logger.
   */
  @Test
  public void writeToHtmlFileLogger() {
    HtmlFileLogger logger = new HtmlFileLogger("", "WriteToHtmlFileLogger");
    logger.logMessage(MessageType.WARNING, "Hello, this is a test.");
    deleteFile(logger);
  }

  /**
   * Test Writing to an Existing Html File Logger.
   */
  @Test
  public void writeToExistingHtmlFileLogger() {
    HtmlFileLogger logger = new HtmlFileLogger(true, "",
        "WriteToExistingHtmlFileLogger", MessageType.GENERIC);
    logger.logMessage(MessageType.WARNING, "This is a test.");
    logger.logMessage(MessageType.WARNING, "This is a test to write to an existing file.");
    deleteFile(logger);
  }

  /**
   * Verify the constructor creates the correct directory if it does not already exist.
   * Delete Directory after each run.
   */
   @Test(singleThreaded = true)
   public void constructorCreateDirectory() {
      String expectedText = "Test to ensure that the file in the created directory can be written to.";

      HtmlFileLogger logger = new HtmlFileLogger(true, Paths.get(LoggingConfig.getLogDirectory(),
         "createDirectoryDelete").toString(), "createDirectory", MessageType.GENERIC);
      logger.logMessage(MessageType.WARNING, expectedText);

      String fileText = this.readTextFile(logger.getFilePath());
      Assert.assertNotNull(fileText, "the text in the file was empty");
      Assert.assertTrue(fileText.contains(expectedText),
          "Expected Log Message to be contained in log."
              + System.lineSeparator() + "In the Log: " + fileText + "" + System.lineSeparator());
      deleteFile(logger);
      deleteDirectory(logger);
   }

  /**
   * Verify that HtmlFileLogger can log message without defining a Message Type.
   */
  @Test
  public void testLoggingMessage() {
    HtmlFileLogger logger = new HtmlFileLogger(true, "", "HtmlFileLoggerLogMessage");
    logger.logMessage("Test to ensure LogMessage works as expected.");
    String htmlText = this.readTextFile(logger.getFilePath());
    Assert.assertTrue(htmlText.contains("Test to ensure LogMessage works as expected."),
        "Expected Log Message to be contained in log.");
    deleteFile(logger);
  }

  /**
   * Verify that HTML File Logger can log message and defining a Message Type.
   */
  @Test
  public void logMessageSelectType() {
    HtmlFileLogger logger = new HtmlFileLogger(true, "", "HtmlFileLoggerLogMessageType");
    logger.logMessage(MessageType.GENERIC, "Test to ensure LogMessage works as expected.");

    String htmlText = this.readTextFile(logger.getFilePath());
    Assert.assertTrue(htmlText.contains("Test to ensure LogMessage works as expected."),
        "Expected Log Message to be contained in log.");
    deleteFile(logger);
  }

  /**
   * Verify that File Path field can be accessed and updated.
   */
  @Test
  public void setFilePath() {
    HtmlFileLogger logger = new HtmlFileLogger(true, "", "HtmlFileLoggerSetFilePath", MessageType.GENERIC);
    logger.setFilePath("test file path");
    String filePath = logger.getFilePath();

    File file = new File(logger.getFilePath());
    logger.close();
    Assert.assertFalse(file.exists());
    Assert.assertEquals(filePath, "test file path", "Expected 'test file path' as file path");
  }

  /**
   * Verify that HTML File Logger catches and handles errors caused by incorrect file Paths
   */
  @Test
  public void catchThrownException() {
    HtmlFileLogger logger = new HtmlFileLogger(true, "", "HtmlFileLoggerCatchThrownException", MessageType.GENERIC);
    logger.setFilePath("<>");

    logger.logMessage(MessageType.GENERIC, "Test throws error as expected.");
    new File(logger.getFilePath());
    logger.close();
  }

  /**
   * Verify that HTML File Logger catches and
   * handles errors caused by incorrect file Paths.
   */
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void emptyFileNameException() {
    HtmlFileLogger logger = new HtmlFileLogger("");
    logger.close();
  }

  /**
   * Verify File Logger with No Parameters assigns the correct default values.
   */
  @Test
  public void testWithNoParameters() {
    HtmlFileLogger logger = new HtmlFileLogger();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("FileLog.html", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only append parameter assigns the correct default
   * values.
   */
  @Test
  public void appendTheLoggerOnly() {
    HtmlFileLogger logger = new HtmlFileLogger(true);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("FileLog.html", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only File Name
   * parameter assigns the correct default values.
   * Verify default extension is added: '.html'
   */
  @Test
  public void nameOnlyAddExtension() {
    HtmlFileLogger logger = new HtmlFileLogger("FileNameOnly");

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("FileNameOnly.html", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only Message Type
   * parameter assigns the correct default values.
   */
  @Test
  public void messageTypeOnly() {
    HtmlFileLogger logger = new HtmlFileLogger(MessageType.WARNING);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("FileLog.html", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.WARNING, logger.getMessageType(), "Expected Warning Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only Append and
   * File Name parameters assigns the correct default values.
   */
  @Test
  public void appendFileName() {
    HtmlFileLogger logger = new HtmlFileLogger(true, "AppendFileName");

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("AppendFileName.html", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only Log Folder and
   * Append parameters assigns the correct default values.
   */
  @Test
  public void appendLogFolder() {
    final String append_file_directory_path = LoggingConfig.getLogDirectory() + File.separator + "Append File Directory";
    HtmlFileLogger logger = new HtmlFileLogger(append_file_directory_path, true);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(append_file_directory_path, logger.getDirectory(),
        "Expected Directory 'Append File Directory'.");
    softAssert.assertEquals("FileLog.html", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
    deleteDirectory(logger);
  }

  /**
   * Verify File Logger with only Log Folder and
   * File Name parameters assigns the correct default values.
   */
  @Test
  public void logFolderFileName() {
    final String log_folder_file_name_directory = LoggingConfig.getLogDirectory() + File.separator
        + "Log Folder File Name Directory";
    HtmlFileLogger logger = new HtmlFileLogger(log_folder_file_name_directory, "LogFolderFileName.html");

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(log_folder_file_name_directory, logger.getDirectory(),
        "Expected Directory 'Log Folder File Name Directory'.");
    softAssert.assertEquals("LogFolderFileName.html", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.INFORMATION, logger.getMessageType(), "Expected Information Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
    deleteDirectory(logger);
  }

  /**
   * Verify File Logger with only Log Folder and
   * Messaging Level parameters assigns the correct default values.
   */
  @Test
  public void logFolderMessagingLevel() {
    final String log_folder_messaging_level_directory_path = LoggingConfig.getLogDirectory() + File.separator
        + "Log Folder Messaging Level Directory";
    HtmlFileLogger logger = new HtmlFileLogger(log_folder_messaging_level_directory_path, MessageType.WARNING);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(log_folder_messaging_level_directory_path, logger.getDirectory(),
        "Expected Directory 'Log Folder Messaging Level Directory'.");
    softAssert.assertEquals("FileLog.html", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.WARNING, logger.getMessageType(), "Expected Warning Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
    deleteDirectory(logger);
  }

  /**
   * Verify File Logger with only Append and
   * Messaging Level parameters assigns the correct default values.
   */
  @Test
  public void appendMessagingLevel() {
    HtmlFileLogger logger = new HtmlFileLogger(true, MessageType.WARNING);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("FileLog.html", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.WARNING, logger.getMessageType(), "Expected Warning Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
  }

  /**
   * Verify File Logger with only Messaging Level and
   * file name parameters assigns the correct default values.
   */
  @Test
  public void messagingLevelFileName() {
    HtmlFileLogger logger = new HtmlFileLogger(MessageType.WARNING, "MessagingTypeFile.html");

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("MessagingTypeFile.html", logger.getFileName(), "Expected correct File Name.");
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
    final String appendLogFolderFileNameDirectoryPath = LoggingConfig.getLogDirectory() + File.separator
        + "AppendLogFolderFileNameDirectory";
    HtmlFileLogger logger = new HtmlFileLogger(true, appendLogFolderFileNameDirectoryPath,
        "AppendLogFolderFileName.html");

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(appendLogFolderFileNameDirectoryPath, logger.getDirectory(),
        " Expected Directory AppendLogFolderFileNameDirectory");
    softAssert.assertEquals("AppendLogFolderFileName.html", logger.getFileName(), "Expected correct File Name.");
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
    final String appendLogFolderFileNameDirectory = LoggingConfig.getLogDirectory() + "/"
        + "AppendLogFolderFileNameDirectory";
    HtmlFileLogger logger = new HtmlFileLogger(true, appendLogFolderFileNameDirectory, MessageType.WARNING);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(appendLogFolderFileNameDirectory, logger.getDirectory(),
        " Expected Directory AppendLogFolderFileNameDirectory");
    softAssert.assertEquals("FileLog.html", logger.getFileName(), "Expected correct File Name.");
    softAssert.assertEquals(MessageType.WARNING, logger.getMessageType(), "Expected Warning Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
    deleteDirectory(logger);
  }

  /**
   * Verify File Logger with only File Name, Append and
   * Messaging Level parameters assigns the correct default values.
   */
  @Test
  public void fileNameAppendMessagingLevel() {
    HtmlFileLogger logger = new HtmlFileLogger(
        "FileNameAppendMessagingLevel.html", true, MessageType.WARNING);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(System.getProperty("java.io.tmpdir"), logger.getDirectory(),
        StringProcessor.safeFormatter("Expected Directory '%s'.", System.getProperty("java.io.tmpdir")));
    softAssert.assertEquals("FileNameAppendMessagingLevel.html",
        logger.getFileName(), "Expected correct File Name.");
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
    final String logFolderFileNameMessagingLevelDirectoryPath = LoggingConfig.getLogDirectory() + "/"
        + "LogFolderFileNameMessagingLevelDirectory";
    HtmlFileLogger logger = new HtmlFileLogger(logFolderFileNameMessagingLevelDirectoryPath,
        "LogFolderFileNameMessagingLevel.html", MessageType.WARNING);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(logFolderFileNameMessagingLevelDirectoryPath, logger.getDirectory(),
        "Expected Directory 'LogFolderFileNameMessagingLevelDirectory'");
    softAssert.assertEquals("LogFolderFileNameMessagingLevel.html", logger.getFileName(),
        "Expected correct File Name.");
    softAssert.assertEquals(MessageType.WARNING, logger.getMessageType(), "Expected Warning Message Type.");
    softAssert.assertAll();

    deleteFile(logger);
    deleteDirectory(logger);
  }

  /**
   * Read a file and return it as a string.
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
   * Deletes the file in the logger.
   * @param logger the HTML File Logger
   */
  private void deleteFile(HtmlFileLogger logger) {
    File file = new File(logger.getFilePath());
    logger.close();

    if (file.exists()) {
      Assert.assertTrue(file.delete());
    }
  }

  private void deleteDirectory(HtmlFileLogger logger) {
    File file = new File(logger.getDirectory());

    if (file.exists()) {
      Assert.assertTrue(file.delete());
    }
  }
}
