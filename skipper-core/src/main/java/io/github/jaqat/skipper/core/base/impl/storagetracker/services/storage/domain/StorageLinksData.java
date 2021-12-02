package io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.domain;

/**
 * data about links of test and task
 */
public class StorageLinksData {
    private String testId;
    private String taskId;

    public String getTestId() {
        return testId;
    }

    public StorageLinksData setTestId(String testId) {
        this.testId = testId;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public StorageLinksData setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }
}
