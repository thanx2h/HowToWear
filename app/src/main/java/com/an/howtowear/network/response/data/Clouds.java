package com.an.howtowear.network.response.data;

import com.google.gson.annotations.SerializedName;

public class Clouds{
    @SerializedName("all")
    String all;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all='" + all + '\'' +
                '}';
    }
}