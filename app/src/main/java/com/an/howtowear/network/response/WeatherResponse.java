package com.an.howtowear.network.response;

import com.an.howtowear.network.response.data.Clouds;
import com.an.howtowear.network.response.data.Coord;
import com.an.howtowear.network.response.data.Main;
import com.an.howtowear.network.response.data.Rain1;
import com.an.howtowear.network.response.data.Weather;
import com.an.howtowear.network.response.data.Wind;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {
    @SerializedName("coord")
    private Coord coord;
    @SerializedName("weather")
    private List<Weather> weather;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private Main main;
    @SerializedName("visibility")
    private String visibility;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("rain")
    private Rain1 rain;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("dt")
    private String dt;
//    @SerializedName("sys")
//    private Sys sys;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private String cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain1 getRain() {
        return rain;
    }

    public void setRain(Rain1 rain) {
        this.rain = rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

//    public Sys getSys() {
//        return sys;
//    }
//
//    public void setSys(Sys sys) {
//        this.sys = sys;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "WeatherInfoResponse{" +
                "coord=" + coord +
                ", weather=" + weather +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility='" + visibility + '\'' +
                ", wind=" + wind +
                ", rain=" + rain +
                ", clouds=" + clouds +
                ", dt='" + dt + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cod='" + cod + '\'' +
                '}';
    }
}

//class Sys {
//    @SerializedName("type")
//    String type;
//    @SerializedName("id")
//    String id;
//    @SerializedName("country")
//    String country;
//    @SerializedName("sunrise")
//    String sunrise;
//    @SerializedName("sunset")
//    String sunset;
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getSunrise() {
//        return sunrise;
//    }
//
//    public void setSunrise(String sunrise) {
//        this.sunrise = sunrise;
//    }
//
//    public String getSunset() {
//        return sunset;
//    }
//
//    public void setSunset(String sunset) {
//        this.sunset = sunset;
//    }
//
//    @Override
//    public String toString() {
//        return "Sys{" +
//                "type='" + type + '\'' +
//                ", id='" + id + '\'' +
//                ", country='" + country + '\'' +
//                ", sunrise='" + sunrise + '\'' +
//                ", sunset='" + sunset + '\'' +
//                '}';
//    }
//}