package com.an.howtowear.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.an.howtowear.HTWApp;
import com.an.howtowear.R;
import com.an.howtowear.network.HttpRequestHelper;
import com.an.howtowear.network.response.ForecastListResponse;
import com.an.howtowear.network.response.WeatherResponse;
import com.an.howtowear.network.response.data.ForecastData;
import com.an.howtowear.support.utils.HTWLog;
import com.an.howtowear.support.utils.LocationUtil;
import com.an.howtowear.support.utils.NotificationUtil;
import com.an.howtowear.support.utils.UIUtil;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private static final int DATA_COUNT = 20;

    private TextView tvCurWeatherData;
    private ImageView ivCurWeatherImg;
    private TextView tvForecastData;
//    private TextView tvLocationData;
    private Button btnReqForecast;
    private Button btnReqCurWeather;
//    private Button btnNotification;

    private double latitude = 0;
    private double longitude = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initLayout();
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
            case R.id.btn_cur_weather:
                requestCurWeather();
                break;

            case R.id.btn_forecast_list:
                requestForecastList();
                break;

            case R.id.btn_notification:
                NotificationUtil.getInstance().showNotificationMessage();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent = null;

        switch (menuItem.getItemId())
        {
            case R.id.settings:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
//            case R.id.menuitem2:
//                Toast.makeText(getApplicationContext(), "SelectedItem 2", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menuitem3:
//                Toast.makeText(getApplicationContext(), "SelectedItem 3", Toast.LENGTH_SHORT).show();
//                break;
            default:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void initLayout(){
        //toolBar를 통해 App Bar 생성
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //App Bar의 좌측 영영에 Drawer를 Open 하기 위한 Icon 추가
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        DrawerLayout drawLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawLayout,
                toolbar,
                R.string.open,
                R.string.close
        );

        drawLayout.addDrawerListener(actionBarDrawerToggle);

        tvCurWeatherData = (TextView) findViewById(R.id.tv_curWeatherData);
        ivCurWeatherImg = (ImageView)findViewById(R.id.main_weather);
//        tvLocationData = (TextView) findViewById(R.id.tv_locationData);
        tvForecastData = (TextView) findViewById(R.id.tv_forecastData);
        btnReqForecast = (Button) findViewById(R.id.btn_forecast_list);
        btnReqCurWeather = (Button) findViewById(R.id.btn_cur_weather);
//        btnNotification = (Button) findViewById(R.id.btn_notification);
        btnReqForecast.setOnClickListener(this);
//        btnNotification.setOnClickListener(this);
        btnReqCurWeather.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
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

            LocationUtil.getInstance().removeLocationUpdates(locationListener);
        }
    }

    private void requestForecastList(){
        Call<ForecastListResponse> call
                = HttpRequestHelper.getInstance().getApiService().getForecastList(latitude, longitude, DATA_COUNT);

        final Callback<ForecastListResponse> callback = new Callback<ForecastListResponse>() { //리스폰 시, 대응할 구현체
            @Override
            public void onResponse(Call<ForecastListResponse> call, Response<ForecastListResponse> response) {

                StringBuilder sb = new StringBuilder();

                sb.append("onResponse, "+response.code()+"\n\n");
                ForecastListResponse parseData = response.body();

                HTWLog.d(parseData.toString());

                if (response.code() == 200) {
                    for (ForecastData fd : parseData.getForecastDataList()) {
//                        sb.append("-예측시간(정수) : " + fd.getDt() + "\n");
//                        sb.append("-기온 : " + fd.getMain().getTemp() + "\n");
//                        sb.append("-최저 기온 : " + fd.getMain().getTemp_min() + "\n");
//                        sb.append("-최고 기온 : " + fd.getMain().getTemp_max() + "\n");
//                        sb.append("-기압 : " + fd.getMain().getPressure() + "\n");
//                        sb.append("-해수면에서의 기압 : " + fd.getMain().getSea_level() + "\n");
//                        sb.append("-대륙에서의 기압 : " + fd.getMain().getGrnd_level() + "\n");
//                        sb.append("-습기 : " + fd.getMain().getHumidity() + "\n");
//
//                        sb.append("-날씨 정보 리스트\n");
//                        for (Weather w : fd.getWeather()) {
//                            sb.append("--날씨 상태 Id : " + w.getId() + "\n");
//                            sb.append("--날씨 상태 : " + w.getMain() + "\n");
//                            sb.append("--날씨 상태 설명 : " + w.getDescription() + "\n");
//                            sb.append("--날짜 아이콘 Id : " + w.getIcon() + "\n");
//                        }
//
//                        sb.append("-바람 \n");
//                        sb.append("--속도 : " + fd.getWind().getSpeed() + "\n");
//                        sb.append("--방향(각도) : " + fd.getWind().getDeg() + "\n");
//
//                        String rain = fd.getRain() == null ? "X" : fd.getRain().getThreeH();
//                        String snow = fd.getSnow() == null ? "X" : fd.getSnow().getThreeH();
//
//                        sb.append("-눈(3시간 전) : " + snow + "\n");
//                        sb.append("-비(3시간 전) : " + rain + "\n");
//
//                        String pod = fd.getSys() == null ? "X" : fd.getSys().getPod();
//
//                        sb.append("Sys : " + pod + "\n");
//                        sb.append("예측 날짜(텍스트): " + fd.getDt_txt() + "\n");
//                        sb.append("\n");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.hsc_layout);

                        TextView textView = new TextView(HTWApp.getContext());
                        textView.setText(sb.toString());
                        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );

                        // iv 객체 초기화
                        ImageView iv = new ImageView(HTWApp.getContext());
                        // 이미지 소스 지정
                        int resId = UIUtil.getResIdValue("w_"+fd.getWeather().get(0).getIcon());
                        HTWLog.i(R.drawable.w_04n + ", "+resId + " " + "w_"+fd.getWeather().get(0).getIcon());

                        iv.setBackgroundResource(resId);
                        // 이미지 크기
                        // iv 객체를 layout 객체에 추가
                        layout.addView(iv);
//                        layout.addView(textView, p);
                    }
//                    sb.append("\n\n");
//
//                    sb.append("도시 : \n");
//                    sb.append("-도시 id : " + parseData.getCity().getId()+"\n");
//                    sb.append("-도시 이름 : " + parseData.getCity().getName()+"\n");
//                    sb.append("-위도 : " + parseData.getCity().getCoord().getLat()+"\n");
//                    sb.append("-경도 : " + parseData.getCity().getCoord().getLon()+"\n");
//                    sb.append("-국가 : " + parseData.getCity().getCountry()+"\n");
//                    sb.append("-타임존: " + parseData.getCity().getTimezone()+"\n");
//
//                    sb.append("날짜 정보 갯수 : " + parseData.getCnt()+"\n");
//
//                    for(ForecastData fd : parseData.getForecastDataList()){
//                        sb.append("-예측시간(정수) : " + fd.getDt()+"\n");
//                        sb.append("-기온 : " + fd.getMain().getTemp()+"\n");
//                        sb.append("-최저 기온 : " + fd.getMain().getTemp_min()+"\n");
//                        sb.append("-최고 기온 : " + fd.getMain().getTemp_max()+"\n");
//                        sb.append("-기압 : " + fd.getMain().getPressure()+"\n");
//                        sb.append("-해수면에서의 기압 : " + fd.getMain().getSea_level()+"\n");
//                        sb.append("-대륙에서의 기압 : " + fd.getMain().getGrnd_level()+"\n");
//                        sb.append("-습기 : " + fd.getMain().getHumidity()+"\n");
//
//                        sb.append("-날씨 정보 리스트\n");
//                        for (Weather w : fd.getWeather()){
//                            sb.append("--날씨 상태 Id : " + w.getId()+"\n");
//                            sb.append("--날씨 상태 : " + w.getMain()+"\n");
//                            sb.append("--날씨 상태 설명 : " + w.getDescription()+"\n");
//                            sb.append("--날짜 아이콘 Id : " + w.getIcon()+"\n");
//                        }
//
//                        sb.append("-바람 \n");
//                        sb.append("--속도 : " + fd.getWind().getSpeed()+"\n");
//                        sb.append("--방향(각도) : " + fd.getWind().getDeg()+"\n");
//
//                        String rain = fd.getRain() == null ? "X" : fd.getRain().getThreeH();
//                        String snow = fd.getSnow() == null ? "X" : fd.getSnow().getThreeH();
//
//                        sb.append("-눈(3시간 전) : " + snow +"\n");
//                        sb.append("-비(3시간 전) : " + rain +"\n");
//
//                        String pod = fd.getSys() == null ? "X" : fd.getSys().getPod();
//
//                        sb.append("Sys : " + pod+"\n");
//                        sb.append("예측 날짜(텍스트): " + fd.getDt_txt()+"\n");
//                        sb.append("\n");
//                    }

                } else {
                    sb.append("\n\n" + parseData.toString());
                }
            }

            @Override
            public void onFailure(Call<ForecastListResponse> call, Throwable t) {
                HTWLog.e(call.toString());
                HTWLog.e(t.getMessage());

                StringBuilder sb = new StringBuilder();
                sb.append("onFailure\n\n");
                sb.append(call.toString());
                sb.append("\n\n"+t.getMessage());
                tvForecastData.setText("" + sb.toString());
            }
        };

        call.enqueue(callback);
    }

    private void requestCurWeather(){
        Call<WeatherResponse> call
                = HttpRequestHelper.getInstance().getApiService().getCurWeather(latitude, longitude);

        final Callback<WeatherResponse> callback = new Callback<WeatherResponse>() { //리스폰 시, 대응할 구현체
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                StringBuilder sb = new StringBuilder();

                WeatherResponse wr = response.body();

                HTWLog.d(wr.toString());
//                sb.append(""+wr.toString());

                Address address = LocationUtil.getInstance().getAddressFromLatLon(latitude, longitude);
                sb.append(address.getAdminArea() + " " + address.getSubLocality() + " " + address.getThoroughfare()+"\n");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss a");
                String dateString = sdf.format(Long.parseLong(wr.getDt())*1000);
                sb.append(dateString+"\n");

                ImageView iv = (ImageView)findViewById(R.id.main_weather);
                // 이미지 소스 지정
                int resId = UIUtil.getResIdValue("w_"+wr.getWeather().get(0).getIcon());
                HTWLog.i(R.drawable.w_04n + ", "+resId + " " + "w_"+wr.getWeather().get(0).getIcon());
                iv.setImageResource(resId);
                // 이미지 크기
                // iv 객체를 layout 객체에 추가
                tvCurWeatherData.setText("" + sb.toString());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                HTWLog.e(call.toString());
                HTWLog.e(t.getMessage());

                StringBuilder sb = new StringBuilder();
                sb.append("onFailure\n\n");
                sb.append(call.toString());
                sb.append("\n\n"+t.getMessage());
                tvCurWeatherData.setText("" + sb.toString());
            }
        };

        call.enqueue(callback);
    }

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            Address address = LocationUtil.getInstance().getAddressFromLatLon(latitude, longitude);

            if(address.getAddressLine(0) != null){
                HTWLog.i("latitude: "+ latitude +", longitude: "+ longitude + " address : " + address.getAddressLine(0));

//                MainActivity.this.requestCurWeather();
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
