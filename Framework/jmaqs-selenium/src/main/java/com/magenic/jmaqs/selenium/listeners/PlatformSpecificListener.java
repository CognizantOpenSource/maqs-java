/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.listeners;

import com.magenic.jmaqs.selenium.annotations.PlatformSpecific;
import com.magenic.jmaqs.selenium.constants.OperatingSystem;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class PlatformSpecificListener implements IAnnotationTransformer {
  @Override
  public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor,
      Method testMethod) {
    OperatingSystem operatingSystem;

    if (testMethod != null && testMethod.isAnnotationPresent(PlatformSpecific.class)) {
      operatingSystem = testMethod.getAnnotation(PlatformSpecific.class).value();
      if (operatingSystem != OperatingSystem.getOperatingSystem()) {
        annotation.setEnabled(false);
      }
    }

  }

}
