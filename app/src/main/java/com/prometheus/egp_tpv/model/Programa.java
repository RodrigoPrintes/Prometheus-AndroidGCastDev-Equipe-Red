package com.prometheus.egp_tpv.model;

import com.google.gson.annotations.SerializedName;

public class Programa {

    @SerializedName("media_id")
    private String mediaId;

    private String title;
    private String description;
    private String webmediaTitleId;
    private long startTime;
    private long endTime;
    private String humanStartTime;
    private String humanEndTime;
    private int durationInMinutes;
    @SerializedName("custom_info")
    private CustomInfo customInfo;
    private Program program;

    // Membros públicos
    public static Graficos graficos;

    // Métodos getters e setters
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
}

