package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    String speed;    //    list.wind.speed Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.

    @SerializedName("deg")
    String deg; //    list.wind.deg Wind direction, degrees (meteorological)

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed='" + speed + '\'' +
                ", deg='" + deg + '\'' +
                '}';
    }
}
