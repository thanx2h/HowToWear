package com.an.howtowear;

import android.app.Application;
import android.content.Context;

public class HTWApp extends Application {
    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}