package com.an.howtowear.service;

import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.an.howtowear.network.HttpRequestHelper;
import com.an.howtowear.network.response.WeatherResponse;
import com.an.howtowear.support.utils.HTWLog;
import com.an.howtowear.support.utils.LocationUtil;
import com.an.howtowear.support.utils.NotificationUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkService extends HTWService {

    private double latitude = 0;
    private double longitude = 0;

    public static NetworkService networkService = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        HTWLog.i("onBind in");
        return super.onBind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        networkService = this;
        HTWLog.i("onCreate in");

//        Intent intent = new Intent(this, EventReceiver.class);
//        intent.setAction(HTWIntent.INTENT_WEATHER_NOTI);
//        sendBroadcast(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        HTWLog.i("onStartCommand in");
        getLocationData();
        NotificationUtil.getInstance().showNotificationMessage("알람", "알람 도착");
//        this.onDestroy();
        return START_STICKY; // 강제종료 시 재생성
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HTWLog.i("onDestroy in");
        LocationUtil.getInstance().removeLocationUpdates(locationListener);
    }

    private void requestCurWeather() {
        Call<WeatherResponse> call
                = HttpRequestHelper.getInstance().getApiService().getCurWeather(latitude, longitude);

        final Callback<WeatherResponse> callback = new Callback<WeatherResponse>() { //리스폰 시, 대응할 구현체
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                StringBuilder sb = new StringBuilder();
                WeatherResponse wr = response.body();
                HTWLog.d(wr.toString());
                LocationUtil.getInstance().removeLocationUpdates(locationListener);
//                sb.append(""+wr.toString());
                NotificationUtil.getInstance().showNotificationMessage("알람", "날씨 도착 : " + wr.toString());
                networkService.onDestroy();
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                HTWLog.e(call.toString());
                HTWLog.e(t.getMessage());
                networkService.onDestroy();
            }
        };

        call.enqueue(callback);
    }

    private void getLocationData(){
        if(LocationUtil.getInstance().isLocationProviderEnabled()){
            LocationUtil.getInstance().requestLocationUpdates(locationListener);

        } else {
            String[] locationData = LocationUtil.getInstance().requestLocationManually().split(":");
            latitude = Double.parseDouble(locationData[0]);
            longitude = Double.parseDouble(locationData[1]);

//            Address address = LocationUtil.getInstance().getAddressFromLatLon(latitude, longitude);
//            if(address != null)
//                HTWLog.i("Manually, latitude: "+ locationData[0] +", longitude: "+ locationData[1]+ "address : " + address.getAddressLine(0));

            LocationUtil.getInstance().requestLocationUpdates(locationListener);
        }
    }


    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            Address address = LocationUtil.getInstance().getAddressFromLatLon(latitude, longitude);

            if(address.getAddressLine(0) != null){
                HTWLog.i("latitude: "+ latitude +", longitude: "+ longitude + " address : " + address.getAddressLine(0));
                requestCurWeather();
                NotificationUtil.getInstance().showNotificationMessage("알람", "현재의 위치 가져오는중");
//                MainActivity.this.requestForecastList();
                LocationUtil.getInstance().removeLocationUpdates(this);
            }
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            HTWLog.i("onStatusChanged, status : " + status + " bundle : " + extras.toString());
        }

        public void onProviderEnabled(String provider) {
            HTWLog.i("onProviderEnabled, prvider : " + provider);
        }

        public void onProviderDisabled(String provider) {
            HTWLog.i("onProviderDisabled, prvider : " + provider);
        }
    };
}
