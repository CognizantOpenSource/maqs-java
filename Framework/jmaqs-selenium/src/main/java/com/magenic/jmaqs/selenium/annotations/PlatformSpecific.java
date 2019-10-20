/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.annotations;

import com.magenic.jmaqs.selenium.constants.OperatingSystem;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PlatformSpecific {
  OperatingSystem[] operatingSystem() default { OperatingSystem.WINDOWS, OperatingSystem.MACOS,
      OperatingSystem.LINUX };
}
