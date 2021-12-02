package io.github.jaqat.skipper.junit5.extension.impl;

import io.github.jaqat.skipper.core.base.impl.storagetracker.services.mapper.impl.DefaultIssueResolveMapper;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.impl.HazelcastStorageService;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.impl.jira.JiraTaskTrackerService;
import io.github.jaqat.skipper.core.base.impl.storagetracker.skipper.StorageTrackerBaseTestSkipper;
import io.github.jaqat.skipper.junit5.extension.BaseTestSkipperExtension;

import java.net.URI;

public class HazelcastJiraSkipperExtension extends BaseTestSkipperExtension {

    public HazelcastJiraSkipperExtension() {
        super(new StorageTrackerBaseTestSkipper(
                new HazelcastStorageService(URI.create(System.getenv("HAZELCAST_URL"))),
                new JiraTaskTrackerService(
                        System.getenv("JIRA_USER"),
                        System.getenv("JIRA_PASSWORD"),
                        URI.create(System.getenv("JIRA_URL"))),
                new DefaultIssueResolveMapper()));

    }
}
