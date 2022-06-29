/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.text.StringEscapeUtils;

/**
 * The HTML File Logger class.
 * Helper class for adding logs to an HTML file. Allows configurable file path.
 */
public class HtmlFileLogger extends FileLogger implements IHtmlFileLogger {

  /**
   * The default log name.
   */
  private static final String DEFAULT_LOG_NAME = "HtmlFileLog.html";

  private static final File FILE_DIRECTORY = new File(
      "src/main/java/com/cognizantsoftvision/maqs/utilities/logging/resources");

  private static final String FILES = FILE_DIRECTORY.getAbsolutePath() + File.separator;

  /**
   * The beginning to the cards section.
   */
  private static final String CARD_START = "<div class='container-fluid'><div class='row'>";

  private static final String LOG_ERROR_MESSAGE = "Failed to write to event log because: %s";

  /**
   * Initializes a new instance of the FileLogger class.
   */
  public HtmlFileLogger() {
    this(false, "", DEFAULT_LOG_NAME, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param append Append document if true
   */
  public HtmlFileLogger(boolean append) {
    this(append, "", DEFAULT_LOG_NAME, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param name File name
   */
  public HtmlFileLogger(String name) {
    this(false, "", name, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param messageLevel Messaging Level
   */
  public HtmlFileLogger(MessageType messageLevel) {
    this(false, "", DEFAULT_LOG_NAME, messageLevel);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param append Append document if true
   * @param name   File name
   */
  public HtmlFileLogger(boolean append, String name) {
    this(append, "", name, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param logFolder Where log files should be saved
   * @param append    Append document if true
   */
  public HtmlFileLogger(String logFolder, boolean append) {
    this(append, logFolder, DEFAULT_LOG_NAME, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param logFolder Where log files should be saved
   * @param name      File Name
   */
  public HtmlFileLogger(String logFolder, String name) {
    this(false, logFolder, name, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param logFolder    Where log files should be saved
   * @param messageLevel Messaging Level
   */
  public HtmlFileLogger(String logFolder, MessageType messageLevel) {
    this(false, logFolder, DEFAULT_LOG_NAME, messageLevel);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param append       Append document if true
   * @param messageLevel Messaging Level
   */
  public HtmlFileLogger(boolean append, MessageType messageLevel) {
    this(append, "", DEFAULT_LOG_NAME, messageLevel);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param messageLevel Messaging Level
   * @param name         File Name
   */
  public HtmlFileLogger(MessageType messageLevel, String name) {
    this(false, "", name, messageLevel);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param append    Append document if true
   * @param logFolder Where log files should be saved
   * @param name      File Name
   */
  public HtmlFileLogger(boolean append, String logFolder, String name) {
    this(append, logFolder, name, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param append       Append document if true
   * @param logFolder    Where log files should be saved
   * @param messageLevel Messaging Level
   */
  public HtmlFileLogger(boolean append, String logFolder, MessageType messageLevel) {
    this(append, logFolder, DEFAULT_LOG_NAME, messageLevel);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param name         File Name
   * @param append       Append document if true
   * @param messageLevel Messaging Level
   */
  public HtmlFileLogger(String name, boolean append, MessageType messageLevel) {
    this(append, "", name, messageLevel);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param logFolder    Where log files should be saved
   * @param name         File Name
   * @param messageLevel Messaging Level
   */
  public HtmlFileLogger(String logFolder, String name, MessageType messageLevel) {
    this(false, logFolder, name, messageLevel);
  }

  /**
   * Initializes a new instance of the HtmlFileLogger class.
   *
   * @param append       True to append to an existing log file or false to overwrite it.
   *                     If the file does not exist this, flag will have no affect.
   * @param logFolder    Where log files should be saved
   * @param name         File Name
   * @param messageLevel Messaging Level
   */
  public HtmlFileLogger(Boolean append, String logFolder, String name, MessageType messageLevel) {
    super(append, logFolder, name, messageLevel);

    try (FileWriter writer = new FileWriter(this.getFilePath(), true)) {
      String defaultCDNTags = Files.readString(Paths.get(FILES + "defaultHeader.html"));
      defaultCDNTags = defaultCDNTags.replace("{0}", this.getFilePath());

      String css = String.valueOf(Files.readString(Paths.get(FILES + "htmlLogger.css")));
      defaultCDNTags = defaultCDNTags.replace("{1}", css);

      writer.write(defaultCDNTags);
      writer.write(System.lineSeparator() + Files.readString(Paths.get(FILES + "script.html")));
      writer.write(System.lineSeparator() + Files.readString(Paths.get(FILES + "filterDropdown.html")));
      writer.write(System.lineSeparator() + CARD_START);
    } catch (IOException e) {
      ConsoleLogger console = new ConsoleLogger();
      console.logMessage(MessageType.ERROR, StringProcessor.safeFormatter(LOG_ERROR_MESSAGE, e.getMessage()));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void logMessage(String message, Object... args) {
    this.logMessage(MessageType.INFORMATION, message, args);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void logMessage(MessageType messageType, String message, Object... args) {
    // If the message level is greater that the current log level then do not log it.
    if (this.shouldMessageBeLogged(messageType)) {
      try {
        String logMessage = Files.readString(Paths.get(FILES + "logMessage.html"));
        logMessage = logMessage.replace("{messageType}", messageType.name());
        logMessage = logMessage.replace("{0}", getTextWithColorFlag(messageType));
        logMessage = logMessage.replace("{1}", currentDateTime());
        logMessage = logMessage.replace("{2}", StringEscapeUtils.escapeHtml4(
            StringProcessor.safeFormatter(System.lineSeparator() + message, args)));
        insertHtml(logMessage);
      } catch (Exception e) {
        ConsoleLogger console = new ConsoleLogger();
        console.logMessage(MessageType.ERROR, StringProcessor.safeFormatter(LOG_ERROR_MESSAGE, e.getMessage()));
      }
    }
  }

  /**
   * Write the formatted message (one line) to the console as a generic message.
   * @param html Html content
   */
  private void insertHtml(String html) {
    // Log the message
    try (FileWriter writer = new FileWriter(this.getFilePath(), true)) {
      writer.write(System.lineSeparator() + html);
    } catch (Exception e) {
      // Failed to write to the event log, write error to the console instead
      ConsoleLogger console = new ConsoleLogger();
      console.logMessage(MessageType.ERROR, StringProcessor.safeFormatter(LOG_ERROR_MESSAGE, e.getMessage())
          + System.lineSeparator() + "Content: " + html);
    }
  }

  /**
   * Gets the file extension.
   *
   * @return File Extension
   */
  @Override
  protected String getExtension() {
    return ".html";
  }

  /**
   * Close the class and HTML file.
   */
  public void close() {
    File file = new File(this.getFilePath());
    if (file.exists()) {
      try (FileWriter writer = new FileWriter(this.getFilePath(), true)) {
        writer.write(Files.readString(Path.of(FILES + "modalDiv.html")));
        writer.write("</div></div></body></html>");
      } catch (IOException e) {
        ConsoleLogger console = new ConsoleLogger();
        console.logMessage(MessageType.ERROR, StringProcessor.safeFormatter(LOG_ERROR_MESSAGE, e.getMessage()));
      }
    }

  }

  /**
   * Embed a base 64 image.
   * @param base64String Base 64 image string
   */
  @Override
  public void embedImage(String base64String) {
    // Image DIV.
    try {
      String imageDiv = Files.readString(Path.of(FILES + "embedImage.html"));
      imageDiv = imageDiv.replace("{0}", currentDateTime());
      imageDiv = imageDiv.replace("{1}", base64String);
      insertHtml(imageDiv);
    } catch (Exception e) {
      ConsoleLogger console = new ConsoleLogger();
      console.logMessage(MessageType.ERROR, StringProcessor.safeFormatter(LOG_ERROR_MESSAGE, e.getMessage()));
    }
  }

  /**
   * Get the HTML style key for the given message type.
   *
   * @param type The message type
   * @return String - The HTML style key for the given message type
   */
  private String getTextWithColorFlag(MessageType type) {
    switch (type) {
      case VERBOSE:
        return "bg-secondary";
      case ACTION:
        return "text-info";
      case STEP:
        return "text-primary";
      case ERROR:
        return "text-danger";
      case GENERIC:
        return "";
      case INFORMATION:
        return "text-secondary";
      case SUCCESS:
        return "text-success";
      case WARNING:
        return "text-warning";
      default:
        logMessage(this.unknownMessageTypeMessage(type));
        return "text-white bg-dark";
    }
  }
}