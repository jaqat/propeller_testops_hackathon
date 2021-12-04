package io.github.jaqat.skipper.core.base.impl.storagetracker.skipper;

import io.github.jaqat.skipper.core.base.TestSkipper;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.StorageService;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.domain.StorageLinksData;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.TaskTrackerService;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.domain.TaskSearchCriteria;
import io.github.jaqat.skipper.core.domain.SkipTestInfo;
import io.github.jaqat.skipper.core.domain.TestData;

import java.util.List;

/**
 * Base skipper to work with storage-tracker algorithm.
 */
public class StorageTrackerBaseTestSkipper implements TestSkipper {
    private StorageService storageService;
    private TaskTrackerService taskTrackerService;

    public StorageTrackerBaseTestSkipper(StorageService storageService, TaskTrackerService taskTrackerService) {
        this.storageService = storageService;
        this.taskTrackerService = taskTrackerService;
    }

    @Override
    public SkipTestInfo skipTestIfNeeded(TestData testData) {
        List<StorageLinksData> data = storageService.getStorageData();
        taskTrackerService.getTaskData(prepareTestData(data, testData));
        addForm();
        //here some logic to skip data. Maybe one more class with logic??? and skip at all
        return new SkipTestInfo(false, "");
    }

    private TaskSearchCriteria prepareTestData(List<StorageLinksData> data, TestData testData) {
        //find in testStorageData needed data for specified test
        return null;
    }

    private void addForm() {
        //add form: replace in form  api url. api need to be created with some rules.
        //add form: replace in form browse url.
    }
}
