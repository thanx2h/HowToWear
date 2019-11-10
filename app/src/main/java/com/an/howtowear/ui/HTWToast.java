package com.an.howtowear.ui;

import android.widget.Toast;

import com.an.howtowear.HTWApp;

public class HTWToast {
    private static HTWToast htwToast;
    private HTWToast(){}

    public static synchronized HTWToast getInstance(){
        if(htwToast == null){
            htwToast = new HTWToast();
        }

        return htwToast;
    }

    public void showToast(String msg){
        Toast.makeText(HTWApp.getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
