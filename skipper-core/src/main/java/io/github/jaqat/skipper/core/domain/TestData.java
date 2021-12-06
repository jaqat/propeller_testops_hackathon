package io.github.jaqat.skipper.core.domain;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * data about test
 */
public class TestData {
    private Method testMethod;
    private String errorMessage;
    private Map<String, Object> testData; //TODO: just an ANY additional data

    public Method getTestMethod() {
        return testMethod;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, Object> getTestData() {
        return testData;
    }

    public void setTestMethod(Method testMethod) {
        this.testMethod = testMethod;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setTestData(Map<String, Object> testData) {
        this.testData = testData;
    }
}
