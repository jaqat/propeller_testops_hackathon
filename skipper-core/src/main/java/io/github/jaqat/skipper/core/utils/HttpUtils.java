package io.github.jaqat.skipper.core.utils;

import java.io.IOException;
import java.net.CookieManager;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtils {

    private HttpClient httpClient;

    public HttpUtils() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .cookieHandler(new CookieManager())
                .build();
    }

    public HttpClient getClient() {
        return httpClient;
    }

    public HttpResponse<String> sendRequest(HttpRequest request) {
        try {
            return getClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new IllegalStateException("Error while sending request: " + e.getMessage(), e);
        }
    }

}
