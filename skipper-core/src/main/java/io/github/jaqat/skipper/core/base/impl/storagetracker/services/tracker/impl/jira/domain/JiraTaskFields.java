package io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.impl.jira.domain;

public class JiraTaskFields {
    private StatusFields status;
    private String summary;
    private PriorityFields priority;

    public StatusFields getStatus() {
        return status;
    }

    public String getSummary() {
        return summary;
    }

    public PriorityFields getPriority() {
        return priority;
    }
}
