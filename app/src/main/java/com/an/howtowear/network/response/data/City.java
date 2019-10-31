package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("coord")
    private Coord coord;

    @SerializedName("country")
    private String country;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("sunrise")
    private String sunrise;

    @SerializedName("sunset")
    private String sunset;

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", coord=" + coord +
                ", country='" + country + '\'' +
                ", timezone='" + timezone + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }
}
