package com.an.howtowear.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpRequestHelper {
    public final String END_POINT_URL = "https://api.openweathermap.org/data/2.5/";
    public static final String API_KEY = "078d03cad6843adefcf4e46ea5f1c535";

    private Retrofit retrofit;
    private APIService apiService;
    public static HttpRequestHelper httpRequestHelper;


    public HttpRequestHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(END_POINT_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);
    }

    public static synchronized HttpRequestHelper getInstance() {
        if(httpRequestHelper == null){
            httpRequestHelper = new HttpRequestHelper();
        }
        return httpRequestHelper;
    }


    public APIService getApiService() { // API Interface 객체 얻는 용
        return apiService;
    }
}
