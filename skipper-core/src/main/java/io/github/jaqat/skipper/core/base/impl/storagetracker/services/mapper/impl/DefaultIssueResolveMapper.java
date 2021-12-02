package io.github.jaqat.skipper.core.base.impl.storagetracker.services.mapper.impl;

import io.github.jaqat.skipper.core.base.impl.storagetracker.services.mapper.IssueResolveMapper;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.domain.TaskData;

public class DefaultIssueResolveMapper implements IssueResolveMapper {

    @Override
    public boolean isIssueResolved(TaskData taskData) {
        String issueStatus = taskData.getIssueStatus();
        return "Done".equalsIgnoreCase(issueStatus) ||
                "Closed".equalsIgnoreCase(issueStatus) ||
                "Resolved".equalsIgnoreCase(issueStatus);
    }
}
