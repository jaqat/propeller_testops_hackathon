package io.github.jaqat.skipper.core.base.impl.storagetracker.skipper;

import io.github.jaqat.skipper.core.base.TestSkipper;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.mapper.IssueResolveMapper;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.StorageService;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.domain.StorageLinksData;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.TaskTrackerService;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.domain.TaskData;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.domain.TaskSearchCriteria;
import io.github.jaqat.skipper.core.domain.SkipTestInfo;
import io.github.jaqat.skipper.core.domain.TestData;
import io.github.jaqat.skipper.core.utils.ResourcesUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Base skipper to work with storage-tracker algorithm.
 */
public class StorageTrackerBaseTestSkipper implements TestSkipper {
    public static final String LINK_FORM_FILE_PATH = "linkForm.html";
    private StorageService storageService;
    private TaskTrackerService taskTrackerService;
    private IssueResolveMapper issueResolveMapper;

    public StorageTrackerBaseTestSkipper(StorageService storageService, TaskTrackerService taskTrackerService, IssueResolveMapper issueResolveMapper) {
        this.storageService = storageService;
        this.taskTrackerService = taskTrackerService;
        this.issueResolveMapper = issueResolveMapper;
    }

    @Override
    public SkipTestInfo skipTestIfNeeded(TestData testData) {
        addForm(testData);
        List<StorageLinksData> data = storageService.getStorageData();
        if (data.isEmpty()) {
            return new SkipTestInfo(false, "No need to skip test: no data found in storage: data empty");
        } else {
            TaskSearchCriteria searchCriteria = prepareTestData(data, testData);
            if (searchCriteria == null) {
                return new SkipTestInfo(false, "No need to skip test: no data for specified test found in storage");
            } else {
                TaskData taskData = taskTrackerService.getTaskData(searchCriteria);
                if (taskData == null) {
                    return new SkipTestInfo(false, "No need to skip test: no data for task found in task tracker");
                } else {
                    if (!issueResolveMapper.isIssueResolved(taskData)) {
                        //TODO: verify message
                        return new SkipTestInfo(
                                true,
                                String.format(
                                        "Skipped because test link with issue: %s",
                                        taskTrackerService.getTasksBrowseUrl() + "/" + taskData.getIssueKey())
                        );
                    } else {
                        //TODO: clear link?
                    }
                }
            }
        }
        return new SkipTestInfo(false, "No need to skip test..."); //TODO: some message in default case
    }

    private TaskSearchCriteria prepareTestData(List<StorageLinksData> data, TestData testData) {
        return data
                .stream()
                .filter(linkData -> linkData.getTestId().equals(getTestId(testData)))
                .findFirst()
                .map(storageLinksData -> new TaskSearchCriteria().setTaskId(storageLinksData.getTaskId()))
                .orElse(null);
    }

    private String getMD5FromTestName(String testName) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(testName.getBytes(), 0, testName.length());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Error while prepare test id for storage");
        }
    }

    private String getTestId(TestData testData) {
        return getMD5FromTestName(testData.getTestClass().getName()
                + testData.getTestMethod().getName());
    }

    private void addForm(TestData testData) {
        Allure.addAttachment(
                "Link form",
                "text/html",
                ResourcesUtils.getResourceText(LINK_FORM_FILE_PATH)
                        .replace("{testId}", getTestId(testData))
                        .replace("{bugTrackerBrowseUrl}", taskTrackerService.getTasksBrowseUrl())
                        .replace("{storageDataKey}", storageService.getDataKey())
                        .replace("{requestBase}", storageService.getStorageApiUrl().toString()),
                "html"

        );
    }

}
