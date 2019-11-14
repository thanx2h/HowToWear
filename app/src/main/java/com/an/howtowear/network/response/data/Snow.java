package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class Snow {
    @SerializedName("3h")
    String threeH; // list.snow.3h Snow volume for last 3 hours

    public String getThreeH() {
        return threeH;
    }

    public void setThreeH(String threeH) {
        this.threeH = threeH;
    }

    @Override
    public String toString() {
        return "Snow{" +
                "threeH='" + threeH + '\'' +
                '}';
    }
}
