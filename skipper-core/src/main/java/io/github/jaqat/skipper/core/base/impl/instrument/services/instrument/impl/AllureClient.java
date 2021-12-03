package io.github.jaqat.skipper.core.base.impl.instrument.services.instrument.impl;

import com.google.gson.Gson;
import com.jayway.jsonpath.*;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import net.minidev.json.JSONObject;
import org.objectweb.asm.TypeReference;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class AllureClient {

    private String url;
    private Integer projectId;
    private String login;
    private String password;
    private String token;

    private HttpClient httpClient = HttpClient.newBuilder()
            .cookieHandler(new CookieManager())
            .build();

    public AllureClient(Properties properties) {
        this.url = properties.getProperty("allure.url");
        this.projectId = Integer.valueOf(properties.getProperty("allure.project_id"));
        this.login = properties.getProperty("allure.user");
        this.password = properties.getProperty("allure.password");
        this.token = properties.getProperty("allure.user_api_token");

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

    public void auth() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/api/login/system"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString("username=" + login + "&password=" + password))
                .build();
        try {
            httpClient.send(request,HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Defect> getDefects() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/api/rs/defect?projectId="+projectId+"&size=1000"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
            DocumentContext jsonContext = JsonPath.parse(response.body());
            return jsonContext.read("$['content'][*]", new TypeRef<>() {
            });
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }


    }

    public List<DefectMatcher> getDefectMatchers(int defectId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/api/rs/defect/"+defectId+"/matcher?page=0&size=1000"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
            DocumentContext jsonContext = JsonPath.parse(response.body());
            return jsonContext.read("$['content'][*]", new TypeRef<>() {
            });
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}
