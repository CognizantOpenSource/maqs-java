/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base.interfaces;

import com.cognizantsoftvision.maqs.utilities.logging.TestResultType;

public interface TestResult {
  TestResultType getStatus();

  boolean isSuccess();
}
