package com.an.howtowear.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.an.howtowear.R;
import com.an.howtowear.network.response.data.Main;
import com.an.howtowear.utils.PermissionUtil;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Handler delayHandler = new Handler();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> requestPermissionList = PermissionUtil.checkPermission();
                if(requestPermissionList.isEmpty()){
                    startMainActivity();
                } else {
                    startPermissionActivity(requestPermissionList);
                }
            }
        }, 3000);



    }

    public void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startPermissionActivity(ArrayList<String> requestPermissionList){
        Intent intent = new Intent(this, PermissionActivity.class);
        intent.putExtra("requestPermissionList", requestPermissionList);
        startActivity(intent);
    }
}
