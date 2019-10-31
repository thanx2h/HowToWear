//package com.an.howtowear.network.response;
//
//import com.google.gson.annotations.SerializedName;
//
//import java.util.List;
//
//public class WeatherInfoResponse {
//    @SerializedName("coord")
//    private Coord coord;
//    @SerializedName("weather")
//    private List<Weather> weather;
//    @SerializedName("base")
//    private String base;
//    @SerializedName("main")
//    private Main main;
//    @SerializedName("visibility")
//    private String visibility;
//    @SerializedName("wind")
//    private Wind wind;
//    @SerializedName("rain")
//    private Rain rain;
//    @SerializedName("clouds")
//    private Clouds clouds;
//    @SerializedName("dt")
//    private String dt;
//    @SerializedName("sys")
//    private Sys sys;
//    @SerializedName("id")
//    private String id;
//    @SerializedName("name")
//    private String name;
//    @SerializedName("cod")
//    private String cod;
//
//    public Coord getCoord() {
//        return coord;
//    }
//
//    public void setCoord(Coord coord) {
//        this.coord = coord;
//    }
//
//    public List<Weather> getWeather() {
//        return weather;
//    }
//
//    public void setWeather(List<Weather> weather) {
//        this.weather = weather;
//    }
//
//    public String getBase() {
//        return base;
//    }
//
//    public void setBase(String base) {
//        this.base = base;
//    }
//
//    public Main getMain() {
//        return main;
//    }
//
//    public void setMain(Main main) {
//        this.main = main;
//    }
//
//    public String getVisibility() {
//        return visibility;
//    }
//
//    public void setVisibility(String visibility) {
//        this.visibility = visibility;
//    }
//
//    public Wind getWind() {
//        return wind;
//    }
//
//    public void setWind(Wind wind) {
//        this.wind = wind;
//    }
//
//    public Rain getRain() {
//        return rain;
//    }
//
//    public void setRain(Rain rain) {
//        this.rain = rain;
//    }
//
//    public Clouds getClouds() {
//        return clouds;
//    }
//
//    public void setClouds(Clouds clouds) {
//        this.clouds = clouds;
//    }
//
//    public String getDt() {
//        return dt;
//    }
//
//    public void setDt(String dt) {
//        this.dt = dt;
//    }
//
//    public Sys getSys() {
//        return sys;
//    }
//
//    public void setSys(Sys sys) {
//        this.sys = sys;
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCod() {
//        return cod;
//    }
//
//    public void setCod(String cod) {
//        this.cod = cod;
//    }
//
//    @Override
//    public String toString() {
//        return "WeatherInfoResponse{" +
//                "coord=" + coord +
//                ", weather=" + weather +
//                ", base='" + base + '\'' +
//                ", main=" + main +
//                ", visibility='" + visibility + '\'' +
//                ", wind=" + wind +
//                ", rain=" + rain +
//                ", clouds=" + clouds +
//                ", dt='" + dt + '\'' +
//                ", sys=" + sys +
//                ", id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", cod='" + cod + '\'' +
//                '}';
//    }
//}
//
//class Weather{
//    @SerializedName("id")
//    private String id;
//    @SerializedName("main")
//    private String main;
//    @SerializedName("description")
//    private String description;
//    @SerializedName("icon")
//    private String icon;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getMain() {
//        return main;
//    }
//
//    public void setMain(String main) {
//        this.main = main;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }
//
//    @Override
//    public String toString() {
//        return "Weather{" +
//                "id='" + id + '\'' +
//                ", main='" + main + '\'' +
//                ", description='" + description + '\'' +
//                ", icon='" + icon + '\'' +
//                '}';
//    }
//}
//
//class Coord {
//    @SerializedName("lon")
//    private String lon;
//    @SerializedName("lat")
//    private String lat;
//
//    public String getLon() {
//        return lon;
//    }
//
//    public void setLon(String lon) {
//        this.lon = lon;
//    }
//
//    public String getLat() {
//        return lat;
//    }
//
//    public void setLat(String lat) {
//        this.lat = lat;
//    }
//
//    @Override
//    public String toString() {
//        return "Coord{" +
//                "lon='" + lon + '\'' +
//                ", lat='" + lat + '\'' +
//                '}';
//    }
//}
//
//class Main {
//    @SerializedName("temp")
//    private String temp;
//    @SerializedName("pressure")
//    private String pressure;
//    @SerializedName("humidity")
//    private String humidity;
//    @SerializedName("temp_min")
//    private String temp_min;
//    @SerializedName("temp_max")
//    private String temp_max;
//
//    public String getTemp() {
//        return temp;
//    }
//
//    public void setTemp(String temp) {
//        this.temp = temp;
//    }
//
//    public String getPressure() {
//        return pressure;
//    }
//
//    public void setPressure(String pressure) {
//        this.pressure = pressure;
//    }
//
//    public String getHumidity() {
//        return humidity;
//    }
//
//    public void setHumidity(String humidity) {
//        this.humidity = humidity;
//    }
//
//    public String getTemp_min() {
//        return temp_min;
//    }
//
//    public void setTemp_min(String temp_min) {
//        this.temp_min = temp_min;
//    }
//
//    public String getTemp_max() {
//        return temp_max;
//    }
//
//    public void setTemp_max(String temp_max) {
//        this.temp_max = temp_max;
//    }
//
//    @Override
//    public String toString() {
//        return "Main{" +
//                "temp='" + temp + '\'' +
//                ", pressure='" + pressure + '\'' +
//                ", humidity='" + humidity + '\'' +
//                ", temp_min='" + temp_min + '\'' +
//                ", temp_max='" + temp_max + '\'' +
//                '}';
//    }
//}
//
//class Wind {
//    @SerializedName("speed")
//    String speed;
//    @SerializedName("deg")
//    String deg;
//
//    public String getSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(String speed) {
//        this.speed = speed;
//    }
//
//    public String getDeg() {
//        return deg;
//    }
//
//    public void setDeg(String deg) {
//        this.deg = deg;
//    }
//
//    @Override
//    public String toString() {
//        return "Wind{" +
//                "speed='" + speed + '\'' +
//                ", deg='" + deg + '\'' +
//                '}';
//    }
//}
//
//class Rain {
//    @SerializedName("1h")
//    String oneH;
//
//    public String getOneH() {
//        return oneH;
//    }
//
//    public void setOneH(String oneH) {
//        this.oneH = oneH;
//    }
//
//    @Override
//    public String toString() {
//        return "Rain{" +
//                "oneH='" + oneH + '\'' +
//                '}';
//    }
//}
//
//class Clouds{
//    @SerializedName("all")
//    String all;
//
//    public String getAll() {
//        return all;
//    }
//
//    public void setAll(String all) {
//        this.all = all;
//    }
//
//    @Override
//    public String toString() {
//        return "Clouds{" +
//                "all='" + all + '\'' +
//                '}';
//    }
//}
//
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