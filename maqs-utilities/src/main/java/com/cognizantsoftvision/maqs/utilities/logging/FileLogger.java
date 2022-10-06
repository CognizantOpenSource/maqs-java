/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

/**
 * The File Logger class.
 * Helper class for adding logs to a plain text file.
 * Allows configurable file path.
 */
public class FileLogger extends Logger implements IFileLogger {

  /**
   * Default extension type.
   */
  private static final String TXT = ".txt";

  /**
   * The default log file save location.
   */
  private static final String DEFAULT_LOG_FOLDER = System.getProperty("java.io.tmpdir");

  /**
   * Initializes a new instance of the FileLogger class.
   */
  private static final String DEFAULT_LOG_NAME = "FileLog.txt";

  /**
   * Creates a private string for the name of the file.
   */
  private final String fileName;

  /**
   * Create a private string for the path of the file.
   */
  private String filePath;

  /**
   * Creates a private Message Type.
   */
  private final MessageType messageType;

  /**
   * Creates a private string for the directory of the folder.
   */
  private final String directory;

  /**
   * Initializes a new instance of the FileLogger class.
   */
  public FileLogger() {
    this(false, "", DEFAULT_LOG_NAME, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param append Append document if true
   */
  public FileLogger(boolean append) {
    this(append, "", DEFAULT_LOG_NAME, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param name File name
   */
  public FileLogger(String name) {
    this(false, DEFAULT_LOG_FOLDER, name, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param messageLevel Messaging Level
   */
  public FileLogger(MessageType messageLevel) {
    this(false, DEFAULT_LOG_FOLDER, DEFAULT_LOG_NAME, messageLevel);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param append Append document if true
   * @param name   File name
   */
  public FileLogger(boolean append, String name) {
    this(append, DEFAULT_LOG_FOLDER, name, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param logFolder Where log files should be saved
   * @param append    Append document if true
   */
  public FileLogger(String logFolder, boolean append) {
    this(append, logFolder, DEFAULT_LOG_NAME, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param logFolder Where log files should be saved
   * @param name      File Name
   */
  public FileLogger(String logFolder, String name) {
    this(false, logFolder, name, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param logFolder    Where log files should be saved
   * @param messageLevel Messaging Level
   */
  public FileLogger(String logFolder, MessageType messageLevel) {
    this(false, logFolder, DEFAULT_LOG_NAME, messageLevel);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param append       Append document if true
   * @param messageLevel Messaging Level
   */
  public FileLogger(boolean append, MessageType messageLevel) {
    this(append, DEFAULT_LOG_FOLDER, DEFAULT_LOG_NAME, messageLevel);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param messageLevel Messaging Level
   * @param name         File Name
   */
  public FileLogger(MessageType messageLevel, String name) {
    this(false, DEFAULT_LOG_FOLDER, name, messageLevel);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param append    Append document if true
   * @param logFolder Where log files should be saved
   * @param name      File Name
   */
  public FileLogger(boolean append, String logFolder, String name) {
    this(append, logFolder, name, MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param append       Append document if true
   * @param logFolder    Where log files should be saved
   * @param messageLevel Messaging Level
   */
  public FileLogger(boolean append, String logFolder, MessageType messageLevel) {
    this(append, logFolder, DEFAULT_LOG_NAME, messageLevel);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param name         File Name
   * @param append       Append document if true
   * @param messageLevel Messaging Level
   */
  public FileLogger(String name, boolean append, MessageType messageLevel) {
    this(append, DEFAULT_LOG_FOLDER, name, messageLevel);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param logFolder    Where log files should be saved
   * @param name         File Name
   * @param messageLevel Messaging Level
   */
  public FileLogger(String logFolder, String name, MessageType messageLevel) {
    this(false, logFolder, name, messageLevel);
  }

  /**
   * Initializes a new instance of the FileLogger class.
   *
   * @param append       True to append to an existing log file or false to
   *                     overwrite it. If the file does not exist this, flag will
   *                     have no affect.
   * @param logFolder    Where log files should be saved
   * @param name         File Name
   * @param messageLevel Messaging Level
   */
  public FileLogger(boolean append, String logFolder, String name, MessageType messageLevel) {
    super(messageLevel);

    if (logFolder == null || logFolder.isEmpty()) {
      this.directory = DEFAULT_LOG_FOLDER;
    } else {
      this.directory = logFolder;
    }

    if (!Paths.get(this.directory).toFile().exists()) {
      File dir = new File(this.directory);
      dir.mkdir();
    }

    name = makeValidFileName(name);

    if (!name.toLowerCase().endsWith(this.getExtension())) {
      name += this.getExtension();
    }

    this.fileName = name;
    this.filePath = Paths.get(this.directory, name).normalize().toString();
    this.messageType = messageLevel;

    File file = new File(this.filePath);
    if (file.exists() && !append) {
      try (FileWriter writer = new FileWriter(this.filePath, false)) {
        writer.write("");
        writer.flush();
      } catch (IOException e) {
        // Failed to write to the event log, write error to the console instead
        ConsoleLogger console = new ConsoleLogger();
        console.logMessage(MessageType.ERROR, StringProcessor.safeFormatter(
            "Failed to write to event log because: " + e.getMessage(), (Object[]) e.getStackTrace()));
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getFilePath() {
    return this.filePath;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setFilePath(String path) {
    this.filePath = path;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MessageType getMessageType() {
    return this.messageType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDirectory() {
    return this.directory;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public String getFileName() {
    return this.fileName;
  }

  /**
   * {@inheritDoc}
   */
  protected String getExtension() {
    return TXT;
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
    // If the message level is greater than the
    // current log level then do not log it.
    if (this.shouldMessageBeLogged(messageType)) {
      try (FileWriter fw = new FileWriter(this.filePath, true);
          BufferedWriter bw = new BufferedWriter(fw);
          PrintWriter writer = new PrintWriter(bw)) {
        writer.println(StringProcessor.safeFormatter("%s%s", System.lineSeparator(), currentDateTime()));
        writer.print(StringProcessor.safeFormatter("%s:\t", messageType.toString()));

        writer.println(StringProcessor.safeFormatter(message, args));
        writer.flush();
      } catch (IOException e) {
        // Failed to write to the event log, write error to the console instead
        ConsoleLogger console = new ConsoleLogger();
        console.logMessage(MessageType.ERROR,
            StringProcessor.safeFormatter("Failed to write to event log because: %s", e.getMessage()));
        console.logMessage(messageType, message, args);
      }
    }
  }

  /**
   * Take a name sting and make it a valid file name.
   *
   * @param name The string to clean up
   * @return returns the string of a valid filename
   */
  private static String makeValidFileName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Blank or null file name was provided");
    }

    // Replace invalid characters
    String replacedName = name;
    try {
      replacedName = name.replaceAll("[^a-zA-Z0-9._\\- ]+", "~");
    } catch (NullPointerException e) {
      ConsoleLogger console = new ConsoleLogger();
      console.logMessage(MessageType.ERROR,
          StringProcessor.safeFormatter("Failed to Replace Invalid Characters because: %s", e.getMessage()));
    }

    return replacedName;
  }

  /**
   * Get the message for an unknown message type.
   *
   * @param type The Message Type.
   * @return The Unknown Message Type Message.
   */
  protected String unknownMessageTypeMessage(MessageType type) {
    return StringProcessor.safeFormatter("Unknown MessageType: %s%s%s%s", type.name(), System.lineSeparator(),
        "Message will be displayed with the MessageType of: ", MessageType.GENERIC.name());
  }
}
