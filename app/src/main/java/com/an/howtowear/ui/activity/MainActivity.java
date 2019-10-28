package com.an.howtowear.ui.activity;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.an.howtowear.R;
import com.an.howtowear.network.HttpRequestHelper;
import com.an.howtowear.network.response.WeatherInfoResponse;
import com.an.howtowear.uitls.LocationUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView tvWeatherInfo;
    private TextView tvLocationData;
    private Button btnWeatherInfo;

    private double latitude = 0;
    private double longitude = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWeatherInfo = (TextView) findViewById(R.id.tv_weatherInfo);
        tvLocationData = (TextView) findViewById(R.id.tv_locationData);
        btnWeatherInfo = (Button) findViewById(R.id.btn_weatherInfo);
        btnWeatherInfo.setOnClickListener(this);

        getLocationData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationUtil.getInstance().removeLocationUpdates(locationListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_weatherInfo:
                requestWeatherInfo();
                break;
            default:
                break;
        }
    }

    private void getLocationData(){
        if(LocationUtil.getInstance().isLocationProviderEnabled()){
            LocationUtil.getInstance().requestLocationUpdates(locationListener);
        } else {
            String[] locationData = LocationUtil.getInstance().requestLocationManually().split(":");
            latitude = Double.parseDouble(locationData[0]);
            longitude = Double.parseDouble(locationData[1]);
            btnWeatherInfo.setText("Manually\n\n, latitude: "+ locationData[0] +", longitude: "+ locationData[1]);
        }
    }

    private void requestWeatherInfo(){
        Call<WeatherInfoResponse> call
                = HttpRequestHelper.getInstance().getApiService().getWeatherInfo(latitude, longitude);

        Callback<WeatherInfoResponse> callback = new Callback<WeatherInfoResponse>() { //리스폰 시, 대응할 구현체
            @Override
            public void onResponse(Call<WeatherInfoResponse> call, Response<WeatherInfoResponse> response) {

                Log.e("HowToWear", call.toString());
                Log.e("HowToWear", response.toString());

                StringBuilder sb = new StringBuilder();
                sb.append("onResponse\n\n");
                sb.append(call.toString());
                sb.append("\n\n"+response.body().toString());
                tvWeatherInfo.setText("" + sb.toString());
            }

            @Override
            public void onFailure(Call<WeatherInfoResponse> call, Throwable t) {
                Log.e("HowToWear", call.toString());
                Log.e("HowToWear", t.getMessage());

                StringBuilder sb = new StringBuilder();
                sb.append("onFailure\n\n");
                sb.append(call.toString());
                sb.append("\n\n"+t.getMessage());
                tvWeatherInfo.setText("" + sb.toString());
            }
        };

        call.enqueue(callback);
    }

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            tvLocationData.setText("latitude: "+ latitude +", longitude: "+ longitude);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            tvLocationData.setText("onStatusChanged, status : " + status + " bundle : " + extras.toString());
        }

        public void onProviderEnabled(String provider) {
            tvLocationData.setText("onProviderEnabled, prvider : " + provider);
        }

        public void onProviderDisabled(String provider) {
            tvLocationData.setText("onProviderDisabled, prvider : " + provider);
        }
    };
}
