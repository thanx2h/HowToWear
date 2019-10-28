package com.an.howtowear.network;

import com.an.howtowear.network.response.WeatherInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("weather?appId="+HttpRequestHelper.API_KEY)
    Call<WeatherInfoResponse> getWeatherInfo(
            @Query("lat") double lat,
            @Query("lon") double lon);
}
