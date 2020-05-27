package com.magenic.jmaqs.base.watcher;

import com.magenic.jmaqs.base.BaseTest;
import com.magenic.jmaqs.base.interfaces.JunitTestResult;
import com.magenic.jmaqs.utilities.logging.TestResultType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class JunitTestWatcher implements TestWatcher {
    /**
     * Test instance of the test being executed per each thread
     */
    BaseTest testInstance;

    public JunitTestWatcher(BaseTest testInstance) {
        this.testInstance = testInstance;
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        this.testInstance.setTestResult(new JunitTestResult(TestResultType.PASS), context);
        this.testInstance.teardown_junit();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        this.testInstance.setTestResult(new JunitTestResult(TestResultType.FAIL), context);
        this.testInstance.teardown_junit();
    }
}