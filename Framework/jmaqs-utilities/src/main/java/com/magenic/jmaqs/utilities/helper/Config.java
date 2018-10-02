/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.utilities.helper;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.sync.ReadWriteSynchronizer;

/**
 * Configuration class.
 */
public final class Config {
  /**
   * Constant platform independent new line.
   */
  public static final String NEW_LINE = System.getProperty("line.separator");

  /**
   * The default section MagenicMaqs.
   */
  public static final ConfigSection DEFAULT_MAQS_SECTION = ConfigSection.MagenicMaqs;

  /**
   * The default config.xml file name.
   */
  public static final String CONFIG_FILE = "config.xml";

  /**
   * The xmlConfig object.
   */
  private static XMLConfiguration xmlConfig;

  /**
   * The base configs object.
   */
  private static Configurations configs = new Configurations();

  /**
   * initialize config object.
   */
  static {
    try {
      if ((new File(CONFIG_FILE).exists())) {
        FileBasedConfigurationBuilder<XMLConfiguration> builder = configs.xmlBuilder(CONFIG_FILE);

        xmlConfig = builder.getConfiguration();
        xmlConfig.setSynchronizer(new ReadWriteSynchronizer());
      }
    } catch (ConfigurationException exception) {
      throw new RuntimeException(StringProcessor
              .safeFormatter("Exception creating the xml configuration object from the file : %s", exception));
    }
  }

  /**
   * Gets a section from the configuration.
   * @param section
   *          The desired section
   * @return A HashMap of the values in the section
   */
  public static HashMap<String, String> getSection(ConfigSection section) {
    return getSection(section.toString());
  }

  /**
   * Gets a section from the configuration.
   * @param section
   *          The desired section
   * @return A HashMap of the values in the section
   */
  public static HashMap<String, String> getSection(String section) {
    HashMap<String, String> sectionValues = new HashMap();
    Iterator<String> paths = xmlConfig.getKeys(section);
    while (paths.hasNext()) {
      String keys = paths.next();
      sectionValues.put(keys.replaceFirst(section + "\\.", ""), xmlConfig.getString(keys));
    }

    return sectionValues;
  }

  /**
   * Add dictionary of values to maqs section.
   * @param configurations
   *          Dictionary of configuration values
   * @param overrideExisting
   *          True to override existing values, False otherwise
   */
  public static void addGeneralTestSettingValues(HashMap<String, String> configurations, boolean overrideExisting) {
    addTestSettingValues(configurations, DEFAULT_MAQS_SECTION, overrideExisting);
  }

  /**
   * Add dictionary of values to specified section.
   * @param configurations
   *          Dictionary of configuration values
   * @param section
   *          Section to add the value to
   * @param overrideExisting
   *          True to override existing values, False otherwise
   */
  public static void addTestSettingValues(HashMap<String, String> configurations, ConfigSection section,
                                          boolean overrideExisting) {
    addTestSettingValues(configurations, section.toString(), overrideExisting);
  }

  /**
   * Add dictionary of values to specified section.
   * @param configurations
   *          Dictionary of configuration values
   * @param section
   *          Section to add the value to
   * @param overrideExisting
   *          True to override existing values, False otherwise
   */
  public static void addTestSettingValues(HashMap<String, String> configurations, String section,
                                          boolean overrideExisting) {
    for (Map.Entry<String, String> entry : configurations.entrySet()) {
      String sectionedKey =  section + "." + entry.getKey();
      if (overrideExisting || !xmlConfig.containsKey(sectionedKey)) {
        xmlConfig.setProperty(sectionedKey, entry.getValue());
      }
    }
  }

  /**
   * Get the specified value out of the default section.
   * @param key
   *          The key
   * @return The configuration value
   */
  public static String getGeneralValue(String key) {
    return getValueForSection(DEFAULT_MAQS_SECTION, key);
  }

  /**
   * Get the specified value out of the default section.
   * @param key
   *          The key
   * @param defaultValue
   *          The value to return if the key does not exist
   * @return The configuration value
   */
  public static String getGeneralValue(String key, String defaultValue) {
    return getValueForSection(DEFAULT_MAQS_SECTION, key, defaultValue);
  }

  /**
   * Get the specified value out of the specified section.
   * @param section
   *          The section to search
   * @param key
   *          The key
   * @return The configuration value
   */
  public static String getValueForSection(ConfigSection section, String key) {
    return getValueForSection(section, key, "");
  }

  /**
   * Get the specified value out of the specified section.
   * @param section
   *          The section to search
   * @param key
   *          The key
   * @return The configuration value
   */
  public static String getValueForSection(String section, String key) {
    return getValueForSection(section, key, "");
  }

  /**
   * Get the specified value out of the specified section.
   * @param section
   *          The section to search
   * @param key
   *          The key
   * @param defaultValue
   *          The value to return if the key is not found
   * @return The configuration value
   */
  public static String getValueForSection(ConfigSection section, String key, String defaultValue) {
    return getValueForSection(section.toString(), key, defaultValue);
  }

  /**
   * Get the specified value out of the specified section.
   * @param section
   *          The section to search
   * @param key
   *          The key
   * @param defaultValue
   *          The value to return if the key is not found
   * @return The configuration value
   */
  public static String getValueForSection(String section, String key, String defaultValue) {
    String keyWithSection = section + "." + key;
    String retVal =  xmlConfig.getString(keyWithSection, "");
    return retVal == "" ? defaultValue : retVal.replaceFirst(section + "\\.", "");
  }

  /**
   * Get the configuration value for a specific key. Does not assume a section.
   * @param key
   *          The key
   * @return The configuration value - Returns the empty string if the key is not found
   */
  public static String getValue(String key) {
    return xmlConfig.getString(key, "");
  }

  /**
   * Get the configuration value for a specific key. Does not assume a section.
   * @param key
   *          The key
   * @param defaultValue
   *          Value to return if the key does not exist
   * @return The configuration value - Returns the default string if the key is not found
   */
  public static String getValue(String key, String defaultValue) {
    return xmlConfig.getString(key, defaultValue);
  }

  /**
   * Check the config for a specific key. Does not assume a section.
   * @param key
   *          The key
   * @return True if the key exists, false otherwise
   */
  public static boolean doesKeyExist(String key) {
    return xmlConfig.containsKey(key);
  }

  /**
   * Check the config for a specific key. Searches the specified section.
   * @param key
   *          The key
   * @param section
   *          The specified section
   * @return True if the key exists, false otherwise
   */
  public static boolean doesKeyExist(String key, ConfigSection section) {
    return doesKeyExist(key, section.toString());
  }

  /**
   * Check the config for a specific key. Searches the specified section.
   * @param key
   *          The key
   * @param section
   *          The specified section
   * @return True if the key exists, false otherwise
   */
  public static boolean doesKeyExist(String key, String section) {
    String keyWithSection = section + "." + key;
    return xmlConfig.containsKey(keyWithSection);
  }

  /**
   * Check the config for a specific key. Searches the default section.
   * @param key
   *          The key
   * @return True if the key exists, false otherwise
   */
  public static boolean doesGeneralKeyExist(String key) {
    return doesKeyExist(key, DEFAULT_MAQS_SECTION);
  }
}