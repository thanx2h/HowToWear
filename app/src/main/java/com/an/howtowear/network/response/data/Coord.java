package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lon")
    private String lon; // city.coord.lat City geo location, latitude

    @SerializedName("lat")
    private String lat; // city.coord.lon City geo location, longitude

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
