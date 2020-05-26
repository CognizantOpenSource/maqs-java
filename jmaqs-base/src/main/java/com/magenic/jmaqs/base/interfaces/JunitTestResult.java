package com.magenic.jmaqs.base.interfaces;


import com.magenic.jmaqs.utilities.logging.TestResultType;

public class JunitTestResult implements TestResult {
    private TestResultType currentStatus;

    public JunitTestResult(TestResultType status) {
        this.currentStatus = status;
    }

    @Override
    public TestResultType getStatus() {
        return this.currentStatus;
    }

    @Override
    public boolean isSuccess() {
        return currentStatus == TestResultType.PASS;
    }
}
