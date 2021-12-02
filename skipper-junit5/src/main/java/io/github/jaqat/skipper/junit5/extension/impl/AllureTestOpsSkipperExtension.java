package io.github.jaqat.skipper.junit5.extension.impl;

import io.github.jaqat.skipper.core.base.impl.instrument.services.instrument.impl.AllureTestOpsService;
import io.github.jaqat.skipper.core.base.impl.instrument.skipper.InstrumentBaseTestSkipper;
import io.github.jaqat.skipper.junit5.extension.BaseTestSkipperExtension;

import java.net.URI;

public class AllureTestOpsSkipperExtension extends BaseTestSkipperExtension {
    public AllureTestOpsSkipperExtension() {
        super(new InstrumentBaseTestSkipper(new AllureTestOpsService(
                URI.create(System.getenv("ALLURE_URL")),
                System.getenv("ALLURE_API_TOKEN"),
                Integer.parseInt(System.getenv("ALLURE_PROJECT_ID"))
        )));
    }
}
