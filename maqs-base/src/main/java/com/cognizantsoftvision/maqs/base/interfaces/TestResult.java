package com.cognizantsoftvision.maqs.base.interfaces;

import com.cognizantsoftvision.maqs.utilities.logging.TestResultType;

public interface TestResult {
    TestResultType getStatus();

    boolean isSuccess();
}
