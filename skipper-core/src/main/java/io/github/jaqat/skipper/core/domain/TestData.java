package io.github.jaqat.skipper.core.domain;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * data about test
 */
public class TestData {
    private Method testMethod;
    private Map<String, Object> testData; //TODO: just an ANY additional data
}
