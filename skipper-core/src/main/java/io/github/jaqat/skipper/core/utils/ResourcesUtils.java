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
        } catch (NullPointerException e) {
            throw new IllegalStateException(format("Файл \"%s\" не найден в ресурсах", path), e);
        } catch (Exception e) {
            throw new IllegalStateException(format("Ошибка при чтении файла \"%s\"", path), e);
        }
    }

    /**
     * Get resource by path as File instance
     * For example: for sending multipart POST-request
     *
     * @param path - relative path from "resources" folder
     * @return instance of File class
     */
    public static File getResourceFile(String path) {

        try {
            return new File(ClassLoader.getSystemResource(path).toURI());
        } catch (NullPointerException e) {
            throw new IllegalStateException(format("Файл \"%s\" не найден в ресурсах", path), e);
        } catch (URISyntaxException e) {
            throw new IllegalStateException(format("Ошибка при чтении файла \"%s\"", path), e);
        }

    }
}
