package com.javainuse.model;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private String name;
    private List<String> features;
    private List<String> risks;
    private List<String> techs;

    public Application(String name) {
        this.name = name;
        features = new ArrayList<String>();
        risks = new ArrayList<String>();
        techs = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public List<String> getFeatures() {
        return features;
    }

    public List<String> getRisks() {
        return risks;
    }

    public List<String> getTechs() {
        return techs;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public void setRisks(List<String> biz) {
        this.risks = biz;
    }

    public void setTechs(List<String> tech) {
        this.techs = tech;
    }
}
