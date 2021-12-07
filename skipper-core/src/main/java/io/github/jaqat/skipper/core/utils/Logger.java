package io.github.jaqat.skipper.core.utils;

public class Logger {
    private Class clazz;

    public Logger(Class clazz) {
        this.clazz = clazz;
    }

    public void info(String message) {
        System.out.println(clazz.getName() + " : " + message);
    }

    public void error(String message) {
        System.err.println(clazz.getName() + " : " + message);
    }
}
