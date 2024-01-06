package com.prometheus.egp_tpv.model;

import java.util.List;

public class Graficos {
    private String URL;
    private String Trailler;
    private String ImagemURL;
    private String PosterURL;
    private String LogoURL;
    private List<Confronto> confronto;

    public Graficos() {

    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTrailler() {
        return Trailler;
    }

    public void setTrailler(String trailler) {
        Trailler = trailler;
    }

    public String getImagemURL() {
        return ImagemURL;
    }

    public void setImagemURL(String imagemURL) {
        ImagemURL = imagemURL;
    }

    public String getPosterURL() {
        return PosterURL;
    }

    public void setPosterURL(String posterURL) {
        PosterURL = posterURL;
    }

    public String getLogoURL() {
        return LogoURL;
    }

    public void setLogoURL(String logoURL) {
        LogoURL = logoURL;
    }

    public List<Confronto> getConfronto() {
        return confronto;
    }

    public void setConfronto(List<Confronto> confronto) {
        this.confronto = confronto;
    }
// getters e setters
}
