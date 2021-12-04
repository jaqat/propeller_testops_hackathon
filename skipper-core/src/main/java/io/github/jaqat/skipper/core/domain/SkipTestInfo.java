package io.github.jaqat.skipper.core.domain;

/**
 * info about test skip
 */
public class SkipTestInfo {
    private boolean needToSkipTest;
    private String skipMessage;

    public SkipTestInfo(boolean needToSkipTest, String skipMessage) {
        this.needToSkipTest = needToSkipTest;
        this.skipMessage = skipMessage;
    }

    public boolean isNeedToSkipTest() {
        return needToSkipTest;
    }

    public SkipTestInfo setNeedToSkipTest(boolean needToSkipTest) {
        this.needToSkipTest = needToSkipTest;
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
