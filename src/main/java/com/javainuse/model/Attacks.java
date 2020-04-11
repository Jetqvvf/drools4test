package com.javainuse.model;

public class Attacks {
    private String name;

    public Attacks(String name) {
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
        return "Attacks: " + name;
    }
}
