package io.github.jaqat.skipper.core.base.impl.instrument.services.instrument.impl;

import com.jayway.jsonpath.*;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import io.github.jaqat.skipper.core.utils.HttpUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class AllureClient {

    private URI url;
    private String token;

    private static final HttpUtils HTTP_UTILS = new HttpUtils();

    public AllureClient(URI url, String apiToken) {
        this.url = url;
        this.token = apiToken;

        Configuration.setDefaults(new Configuration.Defaults() {

            private final JsonProvider jsonProvider = new GsonJsonProvider();
            private final MappingProvider mappingProvider = new GsonMappingProvider();

            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }

            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }

            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }
        });
    }

    public List<Defect> getDefects(int projectId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/api/rs/defect?projectId=" + projectId + "&size=1000"))
                .header("Authorization", "Api-Token " + token)
                .GET()
                .build();

            HttpResponse<String> response = HTTP_UTILS.sendRequest(request);
            if (response.statusCode() == 200) {
                DocumentContext jsonContext = JsonPath.parse(response.body());
                return jsonContext.read("$['content'][*]", new TypeRef<>() {
                });
            } else if (response.statusCode() == 401){
                throw new IllegalArgumentException("Wrong Allure api token");
            } else {
                throw new IllegalStateException("Can't get defects from AllureTestOps");
            }
    }

    public List<DefectMatcher> getDefectMatchers(int defectId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/api/rs/defect/" + defectId + "/matcher?page=0&size=1000"))
                .header("Authorization", "Api-Token " + token)
                .GET()
                .build();
            HttpResponse<String> response = HTTP_UTILS.sendRequest(request);
            DocumentContext jsonContext = JsonPath.parse(response.body());
            return jsonContext.read("$['content'][*]", new TypeRef<List<DefectMatcher>>() {
            });
    }
}
