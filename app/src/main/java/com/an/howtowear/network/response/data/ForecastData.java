package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastData {

    @SerializedName("dt")
    private String dt;

    @SerializedName("main")
    private ForecastMain main;

    @SerializedName("weather")
    private List<Weather> weather;

    @SerializedName("clouds")
    private Clouds clouds;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("dt_txt")
    private String dt_txt;

    @Override
    public String toString() {
        return "ForecastData{" +
                "dt='" + dt + '\'' +
                ", main=" + main +
                ", weather=" + weather +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", sys=" + sys +
                ", dt_txt='" + dt_txt + '\'' +
                '}';
    }
}
