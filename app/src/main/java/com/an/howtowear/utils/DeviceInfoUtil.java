package com.an.howtowear.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.an.howtowear.HTWApp;

public class DeviceInfoUtil {

    private static DeviceInfoUtil deviceInfoUtil;

    private DeviceInfoUtil() {
    }

    public static synchronized DeviceInfoUtil getInstance() {
        if (deviceInfoUtil == null) {
            deviceInfoUtil = new DeviceInfoUtil();
        }

        return deviceInfoUtil;
    }

    public String getApplicationVersion(){

        String version = "Unknown";
        PackageInfo packageInfo;

        try {
            packageInfo = HTWApp.getContext().getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(HTWApp.getContext().getApplicationContext().getPackageName(), 0 );
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
//            Logger.e(TAG, "getVersionInfo :" + e.getMessage());
        }
        return version;
    }


}
