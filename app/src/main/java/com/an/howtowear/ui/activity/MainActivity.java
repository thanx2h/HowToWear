package com.an.howtowear.ui.activity;

import android.content.IntentFilter;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.an.howtowear.R;
import com.an.howtowear.network.HttpRequestHelper;
import com.an.howtowear.network.response.ForecastListResponse;
import com.an.howtowear.receiver.EventReceiver;
import com.an.howtowear.utils.AlarmUtil;
import com.an.howtowear.utils.LocationUtil;
import com.an.howtowear.utils.NotificationUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private static final int DATA_COUNT = 5;

    private TextView tvWeatherInfo;
    private TextView tvLocationData;
    private Button btnRequestForecast;
    private Button btnNotification;

    private EventReceiver eventReceiver;

    private double latitude = 0;
    private double longitude = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initLayout();
        getLocationData();
        AlarmUtil.getInstance().setAlarm();

        eventReceiver = new EventReceiver();
        registerReceiver(eventReceiver,new IntentFilter(AlarmUtil.ACTION_ALARM));

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

            case R.id.btn_notification:
                NotificationUtil.getNotificationUtil().showNotificationMessage();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.menuitem1:
                Toast.makeText(getApplicationContext(), "SelectedItem 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuitem2:
                Toast.makeText(getApplicationContext(), "SelectedItem 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuitem3:
                Toast.makeText(getApplicationContext(), "SelectedItem 3", Toast.LENGTH_SHORT).show();
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

        //App Bar의 좌측 영영에 Drawer를 Open 하기 위한 Incon 추가
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);

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

        tvWeatherInfo = (TextView) findViewById(R.id.tv_weatherInfo);
        tvLocationData = (TextView) findViewById(R.id.tv_locationData);
        btnRequestForecast = (Button) findViewById(R.id.btn_weatherInfo);
        btnNotification = (Button) findViewById(R.id.btn_notification);
        btnRequestForecast.setOnClickListener(this);
        btnNotification.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void getLocationData(){
        if(LocationUtil.getInstance().isLocationProviderEnabled()){
            LocationUtil.getInstance().requestLocationUpdates(locationListener);
        } else {
            String[] locationData = LocationUtil.getInstance().requestLocationManually().split(":");
            latitude = Double.parseDouble(locationData[0]);
            longitude = Double.parseDouble(locationData[1]);
            btnRequestForecast.setText("Manually\n\n, latitude: "+ locationData[0] +", longitude: "+ locationData[1]);
        }
    }

    private void requestWeatherInfo(){
        Call<ForecastListResponse> call
                = HttpRequestHelper.getInstance().getApiService().getForecastList(latitude, longitude, DATA_COUNT);

        final Callback<ForecastListResponse> callback = new Callback<ForecastListResponse>() { //리스폰 시, 대응할 구현체
            @Override
            public void onResponse(Call<ForecastListResponse> call, Response<ForecastListResponse> response) {

                Log.e("HowToWear", call.toString());
                Log.e("HowToWear", response.toString());

                StringBuilder sb = new StringBuilder();
                sb.append("onResponse\n\n");

                if (response.code() == 200) {
                    sb.append(call.toString());
                    sb.append("\n\n"+response.body().toString());
                } else {
                    sb.append(call.toString());
                    sb.append("\n\n"+response.code());
                }

                tvWeatherInfo.setText("" + sb.toString());
            }

            @Override
            public void onFailure(Call<ForecastListResponse> call, Throwable t) {
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
