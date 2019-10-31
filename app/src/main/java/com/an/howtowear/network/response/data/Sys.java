package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class Sys {
    @SerializedName("type")
    String type;
    @SerializedName("id")
    String id;
    @SerializedName("country")
    String country;
    @SerializedName("sunrise")
    String sunrise;
    @SerializedName("sunset")
    String sunset;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }
}
