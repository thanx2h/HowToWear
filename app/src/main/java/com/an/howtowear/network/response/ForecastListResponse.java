package com.an.howtowear.network.response;

import com.an.howtowear.network.response.data.*;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastListResponse {

    @SerializedName("cod")
    private String cod;

    @SerializedName("message")
    private String message;

    @SerializedName("cnt")
    private String cnt;

    @SerializedName("city")
    private City city;

    @SerializedName("list")
    List<ForecastData> forecastDataList;

    @Override
    public String toString() {
        return "ForecastListResponse{" +
                "cod='" + cod + '\'' +
                ", message='" + message + '\'' +
                ", cnt='" + cnt + '\'' +
                ", city=" + city +
                ", forecastDataList=" + forecastDataList +
                '}';
    }
}



