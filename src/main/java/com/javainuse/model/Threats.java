package com.javainuse.model;

public class Threats {
    private String name;

    public Threats(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Threats: " + name;
    }
}
