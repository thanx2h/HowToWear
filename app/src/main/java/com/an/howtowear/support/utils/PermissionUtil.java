package com.an.howtowear.support.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.an.howtowear.HTWApp;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {

    private static PermissionUtil permissionUtil;

    public final static int CODE_REQEUST_PERMISSION = 1;

    private static String[] appRermissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    private PermissionUtil() {

    }

    public static synchronized PermissionUtil getPermissionUtil() {
        if (permissionUtil == null) {
            permissionUtil = new PermissionUtil();
        }

        return permissionUtil;
    }

    public static ArrayList<String> checkPermission() {
        ArrayList<String> needPermissionList = new ArrayList<String>();

        for (String permission : appRermissions) {
            if (ContextCompat.checkSelfPermission(HTWApp.getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                needPermissionList.add(permission);
            }
        }

        return needPermissionList;

    }

    public static void requestPermission(List<String> requestRermissionList, Activity activity) {
        ActivityCompat.requestPermissions(activity, requestRermissionList.toArray(new String[requestRermissionList.size()]),
                CODE_REQEUST_PERMISSION);
    }
}
