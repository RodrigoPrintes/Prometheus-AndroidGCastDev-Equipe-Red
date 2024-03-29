package com.prometheus.egp_tpv.model;

import com.google.gson.annotations.SerializedName;

public class Programa {


    private int mediaId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @SerializedName("date")
    private String date;
    private String title;
    private String description;
    private String webmediaTitleId;
    @SerializedName("start_time")
    private long startTime;
    @SerializedName("end_time")
    private long endTime;
    @SerializedName("duration_in_minutes")
    private int durationInMinutes;
    @SerializedName("custom_info")
    private CustomInfo customInfo;
    private Program program;

    public String getHuman_start_time() {
        return human_start_time;
    }

    public void setHuman_start_time(String human_start_time) {
        this.human_start_time = human_start_time;
    }

    public static Graficos getGraficos() {
        return graficos;
    }

    public static void setGraficos(Graficos graficos) {
        Programa.graficos = graficos;
    }

    @SerializedName("human_start_time")
    private String human_start_time;
    public static Graficos graficos;

    // Métodos getters e setters
    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
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

    public Programa( int mediaId, String title, String description, String webmediaTitleId, long startTime, long endTime, String humanStartTime, int durationInMinutes) {
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
        this.webmediaTitleId = webmediaTitleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.human_start_time = humanStartTime;
        this.durationInMinutes = durationInMinutes;
    }
    public Programa( int mediaId, String title, String description, String webmediaTitleId, long startTime, long endTime, String humanStartTime, int durationInMinutes, String date) {
        this.mediaId = mediaId;
        this.date = date;
        this.title = title;
        this.description = description;
        this.webmediaTitleId = webmediaTitleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.human_start_time = humanStartTime;
        this.durationInMinutes = durationInMinutes;
    }
}

