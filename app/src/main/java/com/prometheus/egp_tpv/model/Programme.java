package com.prometheus.egp_tpv.model;

import java.util.List;

public class Programme {
    String date;
    List<Programa> entries;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Programa> getEntries() {
        return entries;
    }

    public void setEntries(List<Programa> entries) {
        this.entries = entries;
    }
}
