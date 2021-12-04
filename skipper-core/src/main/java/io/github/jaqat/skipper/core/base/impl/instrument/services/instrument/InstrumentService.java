package io.github.jaqat.skipper.core.base.impl.instrument.services.instrument;

import io.github.jaqat.skipper.core.domain.SkipTestInfo;
import io.github.jaqat.skipper.core.domain.TestData;

/**
 * interface to work with instruments
 */
public interface InstrumentService {

    /**
     * check in instrument api with test data - need/not need to skip test
     * @param testData test data
     * @return true if need to skip test
     */
    SkipTestInfo needToSkipTest(TestData testData);
}
