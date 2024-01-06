package com.prometheus.egp_tpv.model;

import com.google.gson.annotations.SerializedName;

public class CustomInfo {
    private String baseUTCOffset;
    @SerializedName("Graficos")
    private Graficos graficos;
    private Resumo resumo;

    public Graficos getGraficos() {
        return graficos;
    }

    public void setGraficos(Graficos graficos) {
        this.graficos = graficos;
    }
// getters e setters
}
