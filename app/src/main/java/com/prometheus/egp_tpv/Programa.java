
package com.prometheus.egp_tpv;


import java.util.List;

public class Programa {
    public static Programa.Graficos Graficos;

    public static class Confronto {
        private String nome;
        private String URL;
        private String imagemURL;

        // getters e setters
    }

    public static class Graficos {
        private String URL;
        private String Trailler;
        private String ImagemURL;
        private String PosterURL;
        private String LogoURL;
        private List<Confronto> confronto;

        public Graficos(){

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

    public static class Resumo {
        private String sinopse;
        private String resumoImprensa;
        private String destaque;

        // getters e setters
    }

    public static class CustomInfo {
        private String baseUTCOffset;
        private Graficos graficos;
        private Resumo resumo;

        // getters e setters
    }

    public static class Program {
        private String id;
        private String name;
        private String category;
        private String parentalGuide;
        private String webmediaProgramId;

        // getters e setters
    }

    private String mediaId;
    private String title;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebmediaTitleId() {
        return webmediaTitleId;
    }

    public void setWebmediaTitleId(String webmediaTitleId) {
        this.webmediaTitleId = webmediaTitleId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getHumanStartTime() {
        return humanStartTime;
    }

    public void setHumanStartTime(String humanStartTime) {
        this.humanStartTime = humanStartTime;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }
    public String getHumanEndTime() {
        return humanEndTime;
    }

    public void setHumanEndTime(String humanEndTime) {
        this.humanEndTime = humanEndTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public CustomInfo getCustomInfo() {
        return customInfo;
    }

    public void setCustomInfo(CustomInfo customInfo) {
        this.customInfo = customInfo;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    private String description;
    private String webmediaTitleId;
    private long startTime;
    private long endTime;
    private String humanStartTime;
    private String humanEndTime;
    private int durationInMinutes;
    private CustomInfo customInfo;
    private Program program;

    private String ImageURL;

}
