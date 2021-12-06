package io.github.jaqat.skipper.junit5.extension.impl;

import io.github.jaqat.skipper.core.base.impl.instrument.services.instrument.impl.AllureTestOpsService;
import io.github.jaqat.skipper.core.base.impl.instrument.skipper.InstrumentBaseTestSkipper;
import io.github.jaqat.skipper.junit5.extension.BaseTestSkipperExtension;

public class AllureTestOpsSkipperExtension extends BaseTestSkipperExtension {
    public AllureTestOpsSkipperExtension() {
        super(new InstrumentBaseTestSkipper(new AllureTestOpsService()));
    }
}
