package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastData {

    @SerializedName("dt")
    private String dt; // list.dt Time of data forecasted, unix, UTC

    @SerializedName("main")
    private ForecastMain main;

    @SerializedName("weather")
    private List<Weather> weather; // list.weather (more info Weather condition codes)

    @SerializedName("clouds")
    private Clouds clouds;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("Rain")
    private Rain rain;

    @SerializedName("Snow")
    private Snow snow;

    @SerializedName("Sys")
    private Sys sys;

    @SerializedName("dt_txt")
    private String dt_txt; // list.dt_txt Data/time of calculation, UTC

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public ForecastMain getMain() {
        return main;
    }

    public void setMain(ForecastMain main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    @Override
    public String toString() {
        return "ForecastData{" +
                "dt='" + dt + '\'' +
                ", main=" + main +
                ", weather=" + weather +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", rain=" + rain +
                ", snow=" + snow +
                ", sys=" + sys +
                ", dt_txt='" + dt_txt + '\'' +
                '}';
    }
}
