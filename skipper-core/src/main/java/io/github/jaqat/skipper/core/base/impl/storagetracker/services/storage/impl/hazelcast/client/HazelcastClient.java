package io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.impl.hazelcast.client;

import io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.impl.hazelcast.client.exceptions.ErrorWhileDeleteDataException;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.impl.hazelcast.client.exceptions.ErrorWhileSaveDataException;
import io.github.jaqat.skipper.core.utils.HttpUtils;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HazelcastClient {
    public static final String API_URL = "/hazelcast/rest/maps";
    private URI hazelcastUrl;
    private static final HttpUtils HTTP_UTILS = new HttpUtils();

    public HazelcastClient(URI hazelcastUrl) {
        this.hazelcastUrl = hazelcastUrl;
    }


    public String saveData(String key, String value) {
        String path = getFullFilePath(key);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .header("Content-Type", getContentTypeByKey(key))
                .POST(HttpRequest.BodyPublishers.ofString(value))
                .build();
        HttpResponse<?> response = HTTP_UTILS.sendRequest(request);
        int responseCode = response.statusCode();
        if (responseCode != 200) {
            throw new ErrorWhileSaveDataException("Error while save data to hazelcast : response code - " + responseCode);
        }
        return path;
    }

    private String getContentTypeByKey(String key) {
        if (key.contains(".json")) {
            return "application/json; charset=utf-8";
        } else if (key.contains(".html")) {
            return "application/text; charset=utf-8";
        }
        return "application/text; charset=utf-8";
    }

    public String getData(String key) {
        try {
            String path = getFullFilePath(key);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(path))
                    .header("Content-Type", getContentTypeByKey(key))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP_UTILS.sendRequest(request);
            int responseCode = response.statusCode();
            if (responseCode != 200) {
                return null;
            }
            return response.body();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(String key) {
        String path = getFullFilePath(key);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(path))
                .header("Content-Type", getContentTypeByKey(key))
                .DELETE()
                .build();
        HttpResponse<?> response = HTTP_UTILS.sendRequest(request);
        int responseCode = response.statusCode();
        if (responseCode != 200) {
            throw new ErrorWhileDeleteDataException("Error while delete data to hazelcast : response code - " + responseCode);
        }
    }

    private String getFormattedKey(String key) {
        if (!key.startsWith("/")) {
            return "/" + key;
        }
        return key;
    }

    public String getFullFilePath(String key) {
        return hazelcastUrl + API_URL + getFormattedKey(key);
    }

    public URI getApiUrl() {
        return URI.create(hazelcastUrl + API_URL);
    }
}
