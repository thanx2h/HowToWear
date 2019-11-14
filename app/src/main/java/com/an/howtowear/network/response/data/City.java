package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("id")
    private String id; // city.id City ID

    @SerializedName("name")
    private String name; // city.name City name

    @SerializedName("coord")
    private Coord coord;

    @SerializedName("country")
    private String country; // city.country Country code (GB, JP etc.)

    @SerializedName("timezone")
    private String timezone; // city.timezone Shift in seconds from UTC

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

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    //    @SerializedName("sunrise")
//    private String sunrise;
//
//    @SerializedName("sunset")
//    private String sunset;

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", coord=" + coord +
                ", country='" + country + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
