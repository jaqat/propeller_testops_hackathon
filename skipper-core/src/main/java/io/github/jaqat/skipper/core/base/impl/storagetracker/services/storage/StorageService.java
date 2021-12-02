package io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage;

import io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.domain.StorageLinksData;

import java.net.URI;
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
    URI getStorageApiUrl();

    /**
     * get  tests with tasks links from storage
     *
     * @return list of links objects or empty list
     */
    List<StorageLinksData> getStorageData();

    /**
     * method to init storage
     */
    void initStorage();

    /**
     * get key for storage - need to store data via api from form
     *
     * @return key
     */
    String getDataKey();
}
