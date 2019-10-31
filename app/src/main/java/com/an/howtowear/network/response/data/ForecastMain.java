package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class ForecastMain extends Main {

    @SerializedName("sea_level")
    String sea_level;

    @SerializedName("grnd_level")
    String grnd_level;

    @SerializedName("temp_kf")
    String temp_kf;

    @Override
    public String toString() {
        return "ForecastMain{" + super.toString() +
                "sea_level='" + sea_level + '\'' +
                ", grnd_level='" + grnd_level + '\'' +
                ", temp_kf='" + temp_kf + '\'' +
                '}';
    }
}
