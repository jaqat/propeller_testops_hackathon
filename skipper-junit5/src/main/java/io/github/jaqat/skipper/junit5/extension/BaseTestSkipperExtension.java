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
        context.getTestMethod().ifPresent(
                testMethod -> {
                    TestData testData = new TestData();
                    testData.setTestMethod(testMethod);
                    testData.setErrorMessage(throwable.getMessage());
                    SkipTestInfo skipTestInfo = testSkipper.skipTestIfNeeded(testData);
                    assumeFalse(
                            skipTestInfo.isNeedToSkipTest(),
                            skipTestInfo.getSkipMessage()
                    );
                }
        );

    }

}
