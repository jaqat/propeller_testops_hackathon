package io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.StorageService;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.domain.StorageLinksData;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.impl.hazelcast.client.HazelcastClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HazelcastStorageService implements StorageService {

    private static final String STORAGE_DATA_KEY = "/links/tasksLinks.json";
    private static final Gson GSON = new GsonBuilder().create();
    private final HazelcastClient hazelcastClient;

    public HazelcastStorageService(URI hazelcastUrl) {
        this.hazelcastClient = new HazelcastClient(hazelcastUrl);
    }

    @Override
    public URI getStorageApiUrl() {
        return hazelcastClient.getApiUrl();
    }

    @Override
    public List<StorageLinksData> getStorageData() {
        String data = hazelcastClient.getData(STORAGE_DATA_KEY);
        if (data == null) {
            return new ArrayList<>();
        }
        return GSON.fromJson(data, new TypeToken<List<StorageLinksData>>() {
        }.getType());
    }

    @Override
    public void initStorage() {
        hazelcastClient.saveData(STORAGE_DATA_KEY, "[]");
    }

    @Override
    public String getDataKey() {
        return STORAGE_DATA_KEY;
    }

}
