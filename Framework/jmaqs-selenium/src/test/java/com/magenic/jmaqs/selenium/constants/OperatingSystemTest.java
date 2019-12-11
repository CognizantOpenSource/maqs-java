/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.constants;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * The type Operating system test.
 */
public class OperatingSystemTest {

  /**
   * Test get operating system name.
   */
  @Test
  public void testGetOperatingSystemName() {
    OperatingSystem operatingSystem = OperatingSystem.WINDOWS;
    Assert.assertEquals(operatingSystem.getOperatingSystemName(), "Windows");
  }

  /**
   * Test get operating system with abbreviation linux.
   */
  @Test
  public void testGetOperatingSystemWithAbbreviationWindows() {
    Assert.assertEquals(OperatingSystem.getOperatingSystemWithAbbreviation("win"),
        OperatingSystem.WINDOWS);
  }

  /**
   * Test get operating system with abbreviation linux.
   */
  @Test
  public void testGetOperatingSystemWithAbbreviationWindowsServer() {
    Assert.assertEquals(OperatingSystem.getOperatingSystemWithAbbreviation("windows server 2016"),
        OperatingSystem.WINDOWS);
  }

  /**
   * Test get operating system with abbreviation windows.
   */
  @Test
  public void testGetOperatingSystemWithAbbreviationLinux() {
    Assert.assertEquals(OperatingSystem.getOperatingSystemWithAbbreviation("nix"),
        OperatingSystem.LINUX);
  }

  /**
   * Test get operating system with abbreviation mac.
   */
  @Test
  public void testGetOperatingSystemWithAbbreviationMac() {
    Assert.assertEquals(OperatingSystem.getOperatingSystemWithAbbreviation("mac os x"),
        OperatingSystem.MACOS);
  }

  /**
   * Test get operating system with abbreviation error.
   */
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetOperatingSystemWithAbbreviationError() {
    OperatingSystem.getOperatingSystemWithAbbreviation("operating system");
  }

  /**
   * Test get operating system.
   */
  @Test
  public void testGetOperatingSystem() {
    OperatingSystem operatingSystem = OperatingSystem.getOperatingSystem();
    Assert.assertEquals(operatingSystem, OperatingSystem.WINDOWS);
  }
}