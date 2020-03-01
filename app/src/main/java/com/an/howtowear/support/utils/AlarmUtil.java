package com.an.howtowear.support.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.an.howtowear.HTWApp;
import com.an.howtowear.config.PreferenceKey;
import com.an.howtowear.receiver.AlarmEventReceiver;

import java.util.Calendar;

public class AlarmUtil {

    public static final String ACTION_ALARM = "com.an.howtowear.action.alarm";
    private static final int PERIOD_HOUR = 1;

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

        Intent alarmIntent = new Intent(HTWApp.getContext(), AlarmEventReceiver.class);
        alarmIntent.setAction(AlarmUtil.ACTION_ALARM);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(HTWApp.getContext(), 0, alarmIntent, 0);

        Calendar calendar = Calendar.getInstance();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(HTWApp.getContext());
        String timeValue = (String) sharedPreferences.getString(PreferenceKey.TIME_SETTING.getValue(), "00:00");

//        HTWLog.i("timeValue : " + timeValue);

        String[] value = timeValue.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(value[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(value[1]));

        getAlarmManager().setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * PERIOD_HOUR, pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getAlarmManager().setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }

//    // 알람 등록
//    private void setAlarm(Context context, long second){
//        Log.i(TAG, "setAlarm()");
//        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//
//        Intent Intent = new Intent(INTENT_ACTION);
//        PendingIntent pIntent = PendingIntent.getActivity(context, 0, Intent, 0);
//
////		alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + second, pIntent);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + second, 1000 * 60 * 2, pIntent);
//    }
//
//    // 알람 해제
//    private void releaseAlarm(Context context){
//        Log.i(TAG, "releaseAlarm()");
//        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//
//        Intent Intent = new Intent(INTENT_ACTION);
//        PendingIntent pIntent = PendingIntent.getActivity(context, 0, Intent, 0);
//        alarmManager.cancel(pIntent);
//
//        // 주석을 풀면 먼저 실행되는 알람이 있을 경우, 제거하고
//        // 새로 알람을 실행하게 된다. 상황에 따라 유용하게 사용 할 수 있다.
////		alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 3000, pIntent);
//    }
}
