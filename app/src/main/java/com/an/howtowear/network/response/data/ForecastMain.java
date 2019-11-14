package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class ForecastMain extends Main {

    @SerializedName("sea_level")
    String sea_level; //    list.main.sea_level Atmospheric pressure on the sea level, hPa

    @SerializedName("grnd_level")
    String grnd_level; //    list.main.grnd_level Atmospheric pressure on the ground level, hPa

    @SerializedName("temp_kf")
    String temp_kf; //    list.main.temp_kf Internal parameter

    public String getSea_level() {
        return sea_level;
    }

    public void setSea_level(String sea_level) {
        this.sea_level = sea_level;
    }

    public String getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(String grnd_level) {
        this.grnd_level = grnd_level;
    }

    public String getTemp_kf() {
        return temp_kf;
    }

    public void setTemp_kf(String temp_kf) {
        this.temp_kf = temp_kf;
    }

    @Override
    public String toString() {
        return "ForecastMain{" + super.toString() +
                "sea_level='" + sea_level + '\'' +
                ", grnd_level='" + grnd_level + '\'' +
                ", temp_kf='" + temp_kf + '\'' +
                '}';
    }
}
