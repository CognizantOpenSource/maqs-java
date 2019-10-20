/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The enum Operating system.
 */
public enum OperatingSystem {
  /**
   * Linux Operating System.
   */
  LINUX("Linux", new ArrayList<>(Arrays.asList("nix", "nux", "aix"))),
  /**
   * Mac Operating System.
   */
  MACOS("Mac OS", new ArrayList<>(Collections.singletonList("mac os x"))),
  /**
   * Windows Operating System.
   */
  WINDOWS("Windows", new ArrayList<>(Collections.singletonList("win")));

  /**
   * The Operating system name.
   */
  private String operatingSystemName;
  /**
   * The Operating system abbreviations.
   */
  private ArrayList<String> operatingSystemAbbreviations;

  /**
   * Instantiates a new Operating system.
   *
   * @param operatingSystemString the operating system string
   * @param osAbbr                the operating system abbreviations
   */
  OperatingSystem(String operatingSystemString, ArrayList<String> osAbbr) {
    this.operatingSystemName = operatingSystemString;
    this.operatingSystemAbbreviations = osAbbr;
  }

  /**
   * Getter for property 'operatingSystemName'.
   *
   * @return Value for property 'operatingSystemName'.
   */
  public String getOperatingSystemName() {
    return operatingSystemName;
  }

  public static OperatingSystem getOperatingSystem() {
    String systemProperty = System.getProperty("os.name").toLowerCase();
    return getOperatingSystemWithAbbreviation(systemProperty);
  }

  public static OperatingSystem getOperatingSystemWithAbbreviation(String operatingSystemAbbr) {

    if (LINUX.operatingSystemAbbreviations.contains(operatingSystemAbbr)) {
      return OperatingSystem.LINUX;
    } else if (MACOS.operatingSystemAbbreviations.contains(operatingSystemAbbr)) {
      return OperatingSystem.MACOS;
    } else if (WINDOWS.operatingSystemAbbreviations.contains((operatingSystemAbbr))) {
      return OperatingSystem.WINDOWS;
    } else {
      throw new IllegalArgumentException(
          String.format("Unknown Operating System detected: %s", operatingSystemAbbr));
    }
  }
}