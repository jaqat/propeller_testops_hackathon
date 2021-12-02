package io.github.jaqat.skipper.core.base.impl.instrument.services.instrument.impl;

public class DefectMatcher {
    private int id;
    private int defectId;
    private String name;
    private String messageRegex;

    public int getId() {
        return id;
    }

    public int getDefectId() {
        return defectId;
    }

    public String getName() {
        return name;
    }

    public String getMessageRegex() {
        return messageRegex;
    }
}
