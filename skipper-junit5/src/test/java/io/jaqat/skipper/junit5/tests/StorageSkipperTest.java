package io.jaqat.skipper.junit5.tests;


import io.github.jaqat.skipper.junit5.extension.impl.HazelcastJiraSkipperExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(HazelcastJiraSkipperExtension.class)
public class StorageSkipperTest {

    @Test
    @DisplayName("Passed test")
    void passedTest() {
        assertTrue(true);
    }

    @Test
    @DisplayName("Failed simple test")
    void failedTest() {
        assertTrue(false);
    }

    @ParameterizedTest(name = "Parametrized failed test: {0}")
    @ValueSource(strings = {"12", "13", "14"})
    void failedParametrizedTest(String name) {
        assertTrue(false);
    }

}
