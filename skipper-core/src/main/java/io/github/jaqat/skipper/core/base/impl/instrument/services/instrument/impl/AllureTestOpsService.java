package io.github.jaqat.skipper.core.base.impl.instrument.services.instrument.impl;

import io.github.jaqat.skipper.core.base.impl.instrument.services.instrument.InstrumentService;
import io.github.jaqat.skipper.core.domain.TestData;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class AllureTestOpsService implements InstrumentService {

    private final static String PROPERTIES_FILE_NAME = "allure-test-ops-fail-skipper.properties";

    private Properties allureProperties;

    public AllureClient allureClient;

    private List<DefectMatcher> activeDefectMatchers;

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = ClassLoader.getSystemResourceAsStream(PROPERTIES_FILE_NAME)) {
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Can't find file:" + PROPERTIES_FILE_NAME);
        }
        return properties;
    }

    public AllureTestOpsService() {
        allureProperties = loadProperties();

        AllureClient allureClient = new AllureClient(allureProperties);
        allureClient.auth();
        activeDefectMatchers = allureClient.getDefects()
                .stream()
                .filter(defect -> defect.getClosed() == false)
                .flatMap(defect -> allureClient.getDefectMatchers(defect.getId()).stream())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

    }

    @Override
    public boolean needToSkipTest(TestData testData) {
        if (allureProperties.isEmpty()) {
            return false;
        }

        int allureId = (Integer) testData.getTestData().get("allureId");

        // get Allure defects

        return false;
    }

    private HttpCookie allureAuth(Properties properties) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(properties.get("allure.url") + "/api/login/system"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        String.format(
                                "username=%s&password=%s",
                                properties.get("allure.user"),
                                properties.get("allure.password")
                        )
                ))
                .build();

        HttpResponse<Void> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

        HttpHeaders headers = response.headers();
        String setCookieValue = headers.firstValue("set-cookie").orElse("");
        List<HttpCookie> setCookies = HttpCookie.parse(setCookieValue);
        return setCookies.stream().filter(cookie -> cookie.getName().equals("ALLURE_TESTOPS_SESSION")).findFirst().orElse(null);

        //        const response = await axios.post(
//                `${finalAllureUrl}/api/login/system`,
//            `username=${config.allureUser}&password=${config.allurePassword}`,
//        {
//            headers: {
//                'Content-Type': 'application/x-www-form-urlencoded',
//                        Authorization: `Bearer ${config.userApiToken}`,
//            },
//        },
//        );
//        const setCookiesHeader = setCookie.parse(response.headers['set-cookie']);
//        const authCookie = setCookiesHeader.filter(it => it.name === 'ALLURE_TESTOPS_SESSION')[0];
//        return `${authCookie.name}=${authCookie.value}`;
    }
}
