package io.github.jaqat.skipper.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

import static java.lang.String.format;
public class ResourcesUtils {

    /**
     * Get text from file in resources by relative path
     *
     * @param path - relative path from "resources" folder
     * @return text from file
     */
    public static String getResourceText(String path) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path)))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }  catch (Exception e) {
            throw new IllegalStateException(format("Error while read file `%s`", path), e);
        }
    }

}
