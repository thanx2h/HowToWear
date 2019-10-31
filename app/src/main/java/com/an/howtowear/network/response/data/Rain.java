package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class Rain {
    @SerializedName("1h")
    String oneH;

    public String getOneH() {
        return oneH;
    }

    public void setOneH(String oneH) {
        this.oneH = oneH;
    }

    @Override
    public String toString() {
        return "Rain{" +
                "oneH='" + oneH + '\'' +
                '}';
    }
}
