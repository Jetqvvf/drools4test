package com.javainuse.model;

public class GenericFeatures {
    private String name;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof GenericFeatures
                && ((GenericFeatures) obj).getName().equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public GenericFeatures(String name) {
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
        return "GenericFeatures: " + name;
    }
}
