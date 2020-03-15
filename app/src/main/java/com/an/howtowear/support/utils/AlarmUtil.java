package com.an.howtowear.support.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.format.Time;

import com.an.howtowear.HTWApp;
import com.an.howtowear.config.PreferenceKey;
import com.an.howtowear.receiver.AlarmEventReceiver;
import com.an.howtowear.ui.dialog.TimePreference;

import java.util.Calendar;

public class AlarmUtil {

    public static final String ACTION_ALARM = "com.an.howtowear.action.alarm";

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

    public void setAlarm(long timeValue, long periodTime) {

//        // 입력한 시간을 파라미터로 사용하기 위해 타입 변환
//        Calendar calendar = Calendar.getInstance();
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(HTWApp.getContext());
//        String timeValue = (String) sharedPreferences.getString(PreferenceKey.TIME_SETTING.getValue(), "0");


        // 알람 셋팅을 위한 pendingIntent 구현
        Intent alarmIntent = new Intent(HTWApp.getContext(), AlarmEventReceiver.class);
        alarmIntent.setAction(AlarmUtil.ACTION_ALARM);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(HTWApp.getContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

//        getAlarmManager().setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
//        getAlarmManager().setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60, pendingIntent);
//        calendar.setTimeInMillis(Long.parseLong(timeValue));
//        HTWLog.d("Alarm Time : " + calendar.getTime());

        if(Build.VERSION.SDK_INT >= 23) {
            getAlarmManager().setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeValue + periodTime, pendingIntent);
        } else if(Build.VERSION.SDK_INT >= 19){
            getAlarmManager().setExact(AlarmManager.RTC_WAKEUP, timeValue + periodTime, pendingIntent);
        } else {
            getAlarmManager().set(AlarmManager.RTC_WAKEUP, timeValue + periodTime, pendingIntent);
        }



    }

    // 알람 해제
    public void cancelAlarm(Context context){
        HTWLog.i("cancelAlarm()");
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent Intent = new Intent(AlarmUtil.ACTION_ALARM);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, Intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pIntent);

        // 주석을 풀면 먼저 실행되는 알람이 있을 경우, 제거하고
        // 새로 알람을 실행하게 된다. 상황에 따라 유용하게 사용 할 수 있다.
//		alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 3000, pIntent);
    }
}
