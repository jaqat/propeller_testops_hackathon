package io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.domain;

/**
 * parameters to search in task tracker
 */
public class TaskSearchCriteria {
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public TaskSearchCriteria setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }
}
