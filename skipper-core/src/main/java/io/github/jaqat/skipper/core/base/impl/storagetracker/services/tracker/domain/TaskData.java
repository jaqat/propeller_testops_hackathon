package io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.domain;

/**
 * data about task from task tracker
 */
public class TaskData {
    private String issueKey;
    private String summary;
    private String issueStatus;
    private String issuePriority;

    public String getIssueKey() {
        return issueKey;
    }

    public TaskData setIssueKey(String issueKey) {
        this.issueKey = issueKey;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public TaskData setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public TaskData setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
        return this;
    }

    public String getIssuePriority() {
        return issuePriority;
    }

    public TaskData setIssuePriority(String issuePriority) {
        this.issuePriority = issuePriority;
        return this;
    }
}
