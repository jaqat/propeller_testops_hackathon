package io.github.jaqat.skipper.core.base.impl.instrument.services.instrument.impl;

public class Defect {
    private Integer id;
    private String name;
    private Boolean closed;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getClosed() {
        return closed;
    }
}
