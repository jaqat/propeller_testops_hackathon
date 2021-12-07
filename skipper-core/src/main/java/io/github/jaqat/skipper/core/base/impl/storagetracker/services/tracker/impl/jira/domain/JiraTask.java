package io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.impl.jira.domain;

public class JiraTask {
    private String key;
    private String id;
    private JiraTaskFields fields;

    public String getKey() {
        return key;
    }

    public String getId() {
        return id;
    }

    public JiraTaskFields getFields() {
        return fields;
    }
}
