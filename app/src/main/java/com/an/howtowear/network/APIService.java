package com.an.howtowear.network;

import com.an.howtowear.network.response.ForecastListResponse;
import com.an.howtowear.network.response.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("forecast?appId="+HttpRequestHelper.API_KEY+"&units=metric")
    Call<ForecastListResponse> getForecastList(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("cnt") int cnt);

    @GET("weather?appId="+HttpRequestHelper.API_KEY+"&units=metric")
    Call<WeatherResponse> getCurWeather(
            @Query("lat") double lat,
            @Query("lon") double lon);
}


