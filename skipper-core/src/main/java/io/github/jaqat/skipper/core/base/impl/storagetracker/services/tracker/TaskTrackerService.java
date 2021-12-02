package io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker;

import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.domain.TaskData;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.domain.TaskSearchCriteria;

/**
 * interface to work with task trackers
 */
public interface TaskTrackerService {

    /**
     * get task browse url - need to use from form
     * @return url to task for browse
     */
    String getTaskBrowseUrl();

    /**
     * get data about task from task tracker
     * @param searchCriteria some search parameters
     * @return task data or null if no data found
     */
    TaskData getTaskData(TaskSearchCriteria searchCriteria);



}
