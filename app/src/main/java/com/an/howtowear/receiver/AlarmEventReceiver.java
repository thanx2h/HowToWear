package com.an.howtowear.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import com.an.howtowear.HTWApp;
import com.an.howtowear.R;
import com.an.howtowear.config.PreferenceKey;
import com.an.howtowear.network.HttpRequestHelper;
import com.an.howtowear.network.response.WeatherResponse;
import com.an.howtowear.service.NetworkService;
import com.an.howtowear.support.utils.AlarmUtil;
import com.an.howtowear.support.utils.HTWLog;
import com.an.howtowear.support.utils.LocationUtil;
import com.an.howtowear.support.utils.NotificationUtil;
import com.an.howtowear.support.utils.UIUtil;
import com.an.howtowear.ui.dialog.TimePreference;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmEventReceiver extends BroadcastReceiver {

//    private static final int ALARM_PERIOD = 1000 * 60 * 60 * 24;
    private static final int ALARM_PERIOD = 1000 * 60 * 30;

    @Override
    public void onReceive(Context context, Intent intent) {
        HTWLog.d("Action : " + intent.getAction());

        if (intent != null) {
            // Alarm Intent
            if (intent.getAction() == AlarmUtil.ACTION_ALARM_RECEIVED) {
                NotificationUtil.getInstance().showNotificationMessage("Alarm", "Alarm Received");

//                TimePreference pref = new TimePreference(context);
//                String value = String.valueOf(System.currentTimeMillis());
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTimeInMillis(Long.parseLong(value));
//                HTWLog.d("value  : " + value + " date : " + calendar.getTime());
//                pref.persistStringValue(value);

                AlarmUtil.getInstance().setAlarm(System.currentTimeMillis(), ALARM_PERIOD);
                Intent intentService = new Intent(context, NetworkService.class);
                context.startService(intentService);
            }
        }
    }

}
