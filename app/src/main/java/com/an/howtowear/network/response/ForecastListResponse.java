package com.an.howtowear.network.response;

import com.an.howtowear.network.response.data.*;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastListResponse {

    @SerializedName("cod")
    private String cod; // code Internal parameter

    @SerializedName("message")
    private String message; // message Internal parameter

    @SerializedName("list")
    List<ForecastData> forecastDataList;

    @SerializedName("cnt")
    private String cnt; // cnt Number of lines returned by this API call

    @SerializedName("city")
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ForecastData> getForecastDataList() {
        return forecastDataList;
    }

    public void setForecastDataList(List<ForecastData> forecastDataList) {
        this.forecastDataList = forecastDataList;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

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



