package io.github.jaqat.skipper.junit5.extension.impl;

import io.github.jaqat.skipper.core.base.TestSkipper;
import io.github.jaqat.skipper.core.base.impl.storagetracker.skipper.StorageTrackerBaseTestSkipper;
import io.github.jaqat.skipper.junit5.extension.BaseTestSkipperExtension;

public class HazelcastJiraSkipperExtension extends BaseTestSkipperExtension {
    public HazelcastJiraSkipperExtension() {
        super(new StorageTrackerBaseTestSkipper(null, null)); //TODO: need default implementations for hazelcast and jira
    }
}
