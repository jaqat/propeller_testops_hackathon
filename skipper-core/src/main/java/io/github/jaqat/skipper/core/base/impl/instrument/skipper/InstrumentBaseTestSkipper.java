package io.github.jaqat.skipper.core.base.impl.instrument.skipper;

import io.github.jaqat.skipper.core.base.TestSkipper;
import io.github.jaqat.skipper.core.base.impl.instrument.services.instrument.InstrumentService;
import io.github.jaqat.skipper.core.domain.SkipTestInfo;
import io.github.jaqat.skipper.core.domain.TestData;

/**
 * Base skipper to work with instrument algorithm.
 */
public class InstrumentBaseTestSkipper implements TestSkipper {
    private InstrumentService instrumentService;

    public InstrumentBaseTestSkipper(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @Override
    public SkipTestInfo skipTestIfNeeded(TestData testData) {
        if (instrumentService.needToSkipTest(testData)) {
            return new SkipTestInfo();
        }
        return new SkipTestInfo();
    }
}
