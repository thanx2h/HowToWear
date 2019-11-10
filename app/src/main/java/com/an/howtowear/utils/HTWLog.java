package com.an.howtowear.utils;

import android.util.Log;

import com.an.howtowear.BuildConfig;

public class HTWLog {

    private static String HTW_PREFIX = "HTW";
    private HTWLog htwLog;

//    private boolean isDebug =(HTWApp.getContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE)!=0;
    private static int LOG_LEVEL = BuildConfig.DEBUG? Log.WARN : Log.VERBOSE;


    private HTWLog() {

    }

    public synchronized HTWLog getInstance() {
        if (htwLog == null) {
            htwLog = new HTWLog();
        }

        return htwLog;
    }

    private static String printLog(String message) {
        String fullClassName = Thread.currentThread().getStackTrace()[4].getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();

        StringBuilder sb = new StringBuilder();
        sb.append("[" + className + "]");
        sb.append("[" + methodName + "] : ");
        sb.append(message);

        return sb.toString();
    }

    private static String getTag() {
        return HTW_PREFIX + DeviceInfoUtil.getInstance().getApplicationVersion();
    }

    public static void v(String message){
        if(LOG_LEVEL <= Log.VERBOSE){
            Log.v(getTag(), printLog(message));
        }
    }

    public void d(String message){
        if(LOG_LEVEL <= Log.DEBUG){
            Log.d(getTag(), printLog(message));
        }
    }

    public static void i(String message){
        if(LOG_LEVEL <= Log.INFO){
            Log.i(getTag(), printLog(message));
        }
    }

    public static void w(String message){
        if(LOG_LEVEL <= Log.WARN){
            Log.w(getTag(), printLog(message));
        }
    }

    public static void e(String message){
        if(LOG_LEVEL <= Log.ERROR){
            Log.e(getTag(), printLog(message));
        }
    }

}
