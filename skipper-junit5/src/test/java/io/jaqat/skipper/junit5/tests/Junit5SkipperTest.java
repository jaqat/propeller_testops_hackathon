package io.jaqat.skipper.junit5.tests;

import io.github.jaqat.skipper.junit5.extension.impl.AllureTestOpsSkipperExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Junit5SkipperTest {

    @Test
    @ExtendWith(AllureTestOpsSkipperExtension.class)
    void firstTest(){
        assertTrue(false, "Debug defect error message");
    }
}
