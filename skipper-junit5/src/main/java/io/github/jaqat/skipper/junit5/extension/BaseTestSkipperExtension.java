package io.github.jaqat.skipper.junit5.extension;

import io.github.jaqat.skipper.core.base.TestSkipper;
import io.github.jaqat.skipper.core.domain.SkipTestInfo;
import io.github.jaqat.skipper.core.domain.TestData;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import static org.junit.jupiter.api.Assumptions.assumeFalse;

public class BaseTestSkipperExtension implements TestExecutionExceptionHandler {
    private TestSkipper testSkipper;

    public BaseTestSkipperExtension(TestSkipper testSkipper) {
        this.testSkipper = testSkipper;
    }


    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (context.getTestMethod().isPresent()) {
            TestData testData =
                    new TestData()
                            .setTestMethod(context.getTestMethod().get())
                            .setTestClass(context.getTestClass().get())
                            .setErrorMessage(throwable.getMessage());
            SkipTestInfo skipTestInfo = testSkipper.skipTestIfNeeded(testData);
            if (skipTestInfo.isNeedToSkipTest()) {
                assumeFalse(
                        true,
                        skipTestInfo.getSkipMessage()
                );
            } else {
                throw throwable;
            }
        }
    }

}
