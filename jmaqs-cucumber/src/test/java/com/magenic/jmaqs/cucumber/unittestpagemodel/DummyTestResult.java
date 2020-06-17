package com.magenic.jmaqs.cucumber.unittestpagemodel;

import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.Set;

/**
 * MIGHT NOT NEED
 */
public class DummyTestResult implements ITestResult {
    @Override
    public int getStatus() {
        return this.SUCCESS;
    }

    private ITestContext testContext;

    public DummyTestResult() {
        this.testContext = null;
    }

    public DummyTestResult(ITestContext testContext) {
        this.testContext = testContext;
    }

    @Override
    public ITestContext getTestContext() {
        return testContext;
    }

    @Override
    public void setStatus(int i) {

    }

    @Override
    public ITestNGMethod getMethod() {
        return null;
    }

    @Override
    public Object[] getParameters() {
        return new Object[0];
    }

    @Override
    public void setParameters(Object[] objects) {

    }

    @Override
    public IClass getTestClass() {
        return null;
    }

    @Override
    public Throwable getThrowable() {
        return null;
    }

    @Override
    public void setThrowable(Throwable throwable) {

    }

    @Override
    public long getStartMillis() {
        return 0;
    }

    @Override
    public long getEndMillis() {
        return 0;
    }

    @Override
    public void setEndMillis(long l) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public Object getInstance() {
        return null;
    }

    @Override
    public Object[] getFactoryParameters() {
        return new Object[0];
    }

    @Override
    public String getTestName() {
        return null;
    }

    @Override
    public String getInstanceName() {
        return null;
    }

    @Override
    public void setTestName(String s) {

    }

    @Override
    public boolean wasRetried() {
        return false;
    }

    @Override
    public void setWasRetried(boolean b) {

    }

    @Override
    public int compareTo(ITestResult o) {
        return 0;
    }

    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public Set<String> getAttributeNames() {
        return null;
    }

    @Override
    public Object removeAttribute(String s) {
        return null;
    }
}
