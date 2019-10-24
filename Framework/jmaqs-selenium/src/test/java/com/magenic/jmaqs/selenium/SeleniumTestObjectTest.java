/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.utilities.logging.ConsoleLogger;
import java.lang.reflect.Method;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.*;

/**
 * The type Selenium test object test.
 */
public class SeleniumTestObjectTest {

  private String testName;
  private ConsoleLogger consoleLogger;

  /**
   * Sets up.
   *
   * @param method the method
   */
  @BeforeMethod
  public void setUp(Method method) {
    testName = method.getName();
    consoleLogger = new ConsoleLogger();

  }

}