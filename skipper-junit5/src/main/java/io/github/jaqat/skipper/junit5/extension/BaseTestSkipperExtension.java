package io.github.jaqat.skipper.junit5.extension;

import io.github.jaqat.skipper.core.base.TestSkipper;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BaseTestSkipperExtension implements AfterTestExecutionCallback {
    private TestSkipper testSkipper;

    public BaseTestSkipperExtension(TestSkipper testSkipper) {
        this.testSkipper = testSkipper;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        //implement logic with test skipper. maybe need to also use TestExecutionExceptionHandler
    }
}
