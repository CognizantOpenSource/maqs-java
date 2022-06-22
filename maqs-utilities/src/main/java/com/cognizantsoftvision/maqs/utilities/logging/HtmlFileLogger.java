/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import org.apache.commons.text.StringEscapeUtils;

/**
 * The HTML File Logger class.
 * Helper class for adding logs to an HTML file. Allows configurable file path.
 */
public class HtmlFileLogger extends FileLogger implements IHtmlFileLogger {

  /**
   * The default log name.
   */
  private static final String DEFAULT_LOG_NAME = "FileLog.html";

  private static final String FILES = "../com/cognizantsoftvision/maqs/utilities/logging/files/";

  /**
   * Default header for the HTML file, this gives us our colored text.
   */
  private static final File DEFAULT_HTML_HEADER = new File(FILES + "defaultHeader.html");

//      "<!DOCTYPE html><html lang='en'><head><meta charset='utf-8'>"
//          + "<meta name='viewport' content='width=device-width, initial-scale=1'><title>{0}</title>"
//          + "<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'>"
//          + "<script src='https://code.jquery.com/jquery-3.4.1.slim.min.js' integrity='sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n' crossorigin='anonymous'></script> <script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js' integrity='sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo' crossorigin='anonymous'></script> <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js' integrity='sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6' crossorigin='anonymous'></script> <script src='https://use.fontawesome.com/releases/v5.0.8/js/all.js'></script> </head><body>";
//      "<!DOCTYPE html><html><header><title>Test Log</title></header><body>";

  private static final File SCRIPT_AND_CSS_TAGS = new File(FILES + "scriptAndCssTags.html");

  private static final File FILTER_DROPDOWN = new File(FILES + "filterDropdown.html");

  private static final File EMBED_IMAGE = new File(FILES + "embedImage.html");

  private static final File LOG_MESSAGE = new File(FILES + "logMessage.html");

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

//    try (FileWriter writer = new FileWriter(this.getFilePath(), true)) {
//      writer.write(new String(Files.readAllBytes(Paths.get(FILES + "defaultHeader.html"))));
//    } catch (IOException e) {
//      ConsoleLogger console = new ConsoleLogger();
//      console.logMessage(MessageType.ERROR, StringProcessor.safeFormatter(LOG_ERROR_MESSAGE, e.getMessage()));
//    }

    try (FileWriter writer = new FileWriter(this.getFilePath(), true)) {
      String defaultCDNTags = Files.readString(DEFAULT_HTML_HEADER.toPath());
      defaultCDNTags = defaultCDNTags.replace("{0}", this.getFilePath());

      writer.write(System.lineSeparator() + defaultCDNTags + this.getFilePath());
      writer.write(System.lineSeparator() + Files.readString(SCRIPT_AND_CSS_TAGS.toPath()));
      writer.write(System.lineSeparator() + Files.readString(FILTER_DROPDOWN.toPath()));
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
        String logMessage = Files.readString(LOG_MESSAGE.toPath());
        logMessage = logMessage.replace("{0}", messageType.name());
        logMessage = logMessage.replace("{1}", getTextWithColorFlag(messageType));
        logMessage = logMessage.replace("{2}", messageType.name());
        logMessage = logMessage.replace("{3}", currentDateTime());
        logMessage = logMessage.replace("{4}", StringEscapeUtils.escapeHtml4(
            StringProcessor.safeFormatter(System.lineSeparator() + message, args)));
        insertHtml(logMessage);
      } catch (IOException e) {
        ConsoleLogger console = new ConsoleLogger();
        console.logMessage(MessageType.ERROR, StringProcessor.safeFormatter(LOG_ERROR_MESSAGE, e.getMessage()));
      }

//      insertHtml("<div class='collapse col-12 show' data-logtype='" + messageType + "'>"
//          + "<div class='card'><div class='card-body " + getTextWithColorFlag(messageType) + "'>"
//          + "<h5 class='card-title mb-1'>" + messageType + "</h5><h6 class='card-subtitle mb-1'>"
//          + currentDateTime() + "</h6><p class='card-text'>"
//          + StringEscapeUtils.escapeHtml4(StringProcessor.safeFormatter(
//                  System.lineSeparator() + message, args)) + "</p></div></div></div>");
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
        writer.write("</body></html>");
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
      String imageDiv = Files.readString(EMBED_IMAGE.toPath());
      imageDiv = imageDiv.replace("{0}", currentDateTime());
      imageDiv = imageDiv.replace("{1}", base64String);
      insertHtml(imageDiv);
    } catch (IOException e) {
      ConsoleLogger console = new ConsoleLogger();
      console.logMessage(MessageType.ERROR, StringProcessor.safeFormatter(LOG_ERROR_MESSAGE, e.getMessage()));
    }

//    String imageDiv = "<div class='collapse col-12 show' data-logtype='IMAGE'><div class='card'>"
//        + "<div class='card-body'><h5 class='card-title mb-1'>IMAGE</h5>"
//        + "<h6 class='card-subtitle mb-1'>{0}</h6></div><a class='pop'>"
//        + "<img class='card-img-top rounded' src='data:image/png;base64, {1}'style='width: 200px;'></a></div></div>";
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
        System.out.println(this.unknownMessageTypeMessage(type));
        return "text-white bg-dark";
    }
  }
}