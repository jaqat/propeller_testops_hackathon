package io.jaqat.skipper.junit5.tests;

import io.github.jaqat.skipper.junit5.extension.impl.AllureTestOpsSkipperExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureTestOpsSkipperExtension.class)
public class AllureTestOpsSkipperTest {

    @Test
    void passedTest() {
        assertTrue(true, "Is not true");
    }

    @Test
    void failedTest() {
        assertTrue(false, "Debug defect error message");
    }

}
