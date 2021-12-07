package io.github.jaqat.skipper.core.base.impl.storagetracker.services.mapper;

import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.domain.TaskData;

/**
 * interface to map issue data and make decision that issue is resolved or not
 */
public interface IssueResolveMapper {

    /**
     * verify - is issue resolved or not
     *
     * @param taskData task data
     * @return true if issue resolved
     */
    boolean isIssueResolved(TaskData taskData);
}
