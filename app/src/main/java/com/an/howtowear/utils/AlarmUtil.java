package com.an.howtowear.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;

import com.an.howtowear.HTWApp;

public class AlarmUtil {

    public static final String ACTION_ALARM = "action_alarm";

    private static AlarmUtil alarmUtil;
    private static AlarmManager alarmManager;

    private AlarmUtil() {
    }

    public static synchronized AlarmUtil getInstance() {
        if (alarmUtil == null) {
            alarmUtil = new AlarmUtil();
        }

        return alarmUtil;
    }

    private synchronized AlarmManager getAlarmManager() {
        if (alarmManager == null) {
            alarmManager = (AlarmManager) HTWApp.getContext().getSystemService(Context.ALARM_SERVICE);
        }

        return alarmManager;
    }

    public void setAlarm() {
        Intent intent = new Intent(ACTION_ALARM);
        PendingIntent sender = PendingIntent.getBroadcast(HTWApp.getContext(), 0, intent, 0);
        long firstTime = SystemClock.elapsedRealtime();
        firstTime += 10 * 1000; //10초 후 알람 이벤트 발생

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //API 19 이상 API 23미만
                getAlarmManager().setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, sender);
            } else {
                //API 19미만
                getAlarmManager().set(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, sender);
            }
        } else {
            //API 23 이상
            getAlarmManager().setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, sender);
        }
    }
}
