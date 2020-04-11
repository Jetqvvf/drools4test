package com.javainuse.model;

import java.util.HashSet;
import java.util.Set;

public class ThreatSet {
    Set<String> threatSet;

    public ThreatSet(){
        threatSet = new HashSet<String>();
    }

    public Set<String> getThreatSet() {
        return threatSet;
    }

    public void setThreatSet(Set<String> threatSet) {
        this.threatSet = threatSet;
    }
}
