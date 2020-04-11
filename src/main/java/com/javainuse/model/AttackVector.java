package com.javainuse.model;

public class AttackVector {
    private String name;

    public AttackVector(String name) {
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
        return "Attack Vector: " + name;
    }
}
