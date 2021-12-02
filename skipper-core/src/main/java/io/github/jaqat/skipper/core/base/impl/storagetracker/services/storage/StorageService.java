package io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage;

import io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.domain.StorageLinksData;

import java.net.URL;
import java.util.List;

/**
 * interface to work with storages
 */
public interface StorageService {

    /**
     * base url for storage api. TODO: need to add description about api rules
     *
     * @return api base url
     */
    URL getStorageApiUrl();

    /**
     * get  tests with tasks links from storage
     *
     * @return list of links objects
     */
    List<StorageLinksData> getStorageData();
}
