package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    String speed;
    @SerializedName("deg")
    String deg;

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
