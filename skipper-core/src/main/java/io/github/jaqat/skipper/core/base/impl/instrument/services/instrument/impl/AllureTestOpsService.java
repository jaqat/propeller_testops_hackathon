package io.github.jaqat.skipper.core.base.impl.instrument.services.instrument.impl;

import io.github.jaqat.skipper.core.base.impl.instrument.services.instrument.InstrumentService;
import io.github.jaqat.skipper.core.domain.SkipTestInfo;
import io.github.jaqat.skipper.core.domain.TestData;
import io.github.jaqat.skipper.core.utils.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

public class AllureTestOpsService implements InstrumentService {

    private static final Logger LOGGER = new Logger(AllureTestOpsService.class);
    private final static String PROPERTIES_FILE_NAME = "allure-test-ops-fail-skipper.properties";
    private Properties allureProperties;
    private List<DefectMatcher> activeDefectMatchers;


    public AllureTestOpsService() {
        allureProperties = loadProperties();
        activeDefectMatchers = new ArrayList<>();
        if (!allureProperties.isEmpty()) {
            try {
                AllureClient allureClient = new AllureClient(allureProperties);

                List<Defect> projectDefects = allureClient.getDefects()
                        .stream()
                        .filter(defect -> !defect.getClosed())
                        .collect(Collectors.toList());

                List<DefectMatcher> defectMatchers = projectDefects.stream()
                        .flatMap(defect -> {
                            try {
                                return allureClient.getDefectMatchers(defect.getId()).stream();
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                                LOGGER.error("Error while getting defect #" + defect.getId() + " matchers");
                            }
                            return Stream.empty();
                        })
                        .collect(Collectors.toList());
                activeDefectMatchers.addAll(
                        defectMatchers
                );
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("Can't get defects from Allure: " + e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = ClassLoader.getSystemResourceAsStream(PROPERTIES_FILE_NAME)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            LOGGER.error("Can't find file:" + PROPERTIES_FILE_NAME);
        }
        return properties;
    }

    @Override
    public SkipTestInfo needToSkipTest(TestData testData) {
        if (!activeDefectMatchers.isEmpty()) {
            DefectMatcher suitableDefectMatcher = activeDefectMatchers.stream().parallel()
                    .filter(defectMatcher -> {
                                Pattern pattern = Pattern.compile(defectMatcher.getMessageRegex());
                                Matcher matcher = pattern.matcher(testData.getErrorMessage());
                                return matcher.find();
                            }
                    )
                    .findFirst()
                    .orElse(null);

            if (suitableDefectMatcher != null) {
                return new SkipTestInfo(
                        true,
                        format(
                                "Skipped by Defect #%d with rule: %s",
                                suitableDefectMatcher.getDefectId(),
                                suitableDefectMatcher.getName()

                        )
                );
            }
        }

        return new SkipTestInfo(false, null);
    }
}
