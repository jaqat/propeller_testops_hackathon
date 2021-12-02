package io.github.jaqat.skipper.core.base;

import io.github.jaqat.skipper.core.domain.SkipTestInfo;
import io.github.jaqat.skipper.core.domain.TestData;

/**
 * base interface for test skip
 */
public interface TestSkipper {

    /**
     * get data about skip test or not. Not skip test at all: all skip logic in specific test frameworks
     *
     * @param testData data about test
     * @return skip test info
     */
    SkipTestInfo skipTestIfNeeded(TestData testData);
}
