package com.magenic.jmaqs.base.interfaces;

import com.magenic.jmaqs.utilities.logging.TestResultType;

public interface TestResult {
    TestResultType getStatus();

    boolean isSuccess();
}
