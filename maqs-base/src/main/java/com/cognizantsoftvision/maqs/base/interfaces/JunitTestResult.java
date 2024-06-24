/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base.interfaces;

import com.cognizantsoftvision.maqs.utilities.logging.TestResultType;

public class JunitTestResult implements TestResult {
  private final TestResultType currentStatus;

  public JunitTestResult(TestResultType status) {
    this.currentStatus = status;
  }

  @Override public TestResultType getStatus() {
    return this.currentStatus;
  }

  @Override public boolean isSuccess() {
    return currentStatus == TestResultType.PASS;
  }
}
