package io.github.jaqat.skipper.core.domain;

/**
 * info about test skip
 */
public class SkipTestInfo {
    private boolean needSkipTest;
    private String skipMessage;

    public boolean isNeedSkipTest() {
        return needSkipTest;
    }

    public SkipTestInfo setNeedSkipTest(boolean needSkipTest) {
        this.needSkipTest = needSkipTest;
        return this;
    }

    public String getSkipMessage() {
        return skipMessage;
    }

    public SkipTestInfo setSkipMessage(String skipMessage) {
        this.skipMessage = skipMessage;
        return this;
    }
}
