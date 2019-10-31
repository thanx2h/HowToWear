package com.an.howtowear.network;

import com.an.howtowear.network.response.ForecastListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("forecast?appId="+HttpRequestHelper.API_KEY)
    Call<ForecastListResponse> getForecastList(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("cnt") double cnt);
}
