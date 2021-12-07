package io.github.jaqat.skipper.core.domain;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * data about test
 */
public class TestData {
    private Method testMethod;
    private Class testClass;
    private String errorMessage;
    private Map<String, Object> testAdditionalData; //TODO: just an ANY additional data

    public Method getTestMethod() {
        return testMethod;
    }

    public Class getTestClass() {
        return testClass;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Map<String, Object> getTestAdditionalData() {
        return testAdditionalData;
    }

    public TestData setTestMethod(Method testMethod) {
        this.testMethod = testMethod;
        return this;
    }

    public TestData setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public TestData setTestAdditionalData(Map<String, Object> testAdditionalData) {
        this.testAdditionalData = testAdditionalData;
        return this;
    }

    public TestData setTestClass(Class testClass) {
        this.testClass = testClass;
        return this;
    }

    public TestData addAdditionalData(String key, Object data) {
        if (testAdditionalData == null) {
            testAdditionalData = new HashMap<>();
        }
        testAdditionalData.put(key, data);
        return this;
    }
}
