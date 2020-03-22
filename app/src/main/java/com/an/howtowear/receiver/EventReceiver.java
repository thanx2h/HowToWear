package com.an.howtowear.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.an.howtowear.config.HTWIntent;
import com.an.howtowear.support.utils.AlarmUtil;
import com.an.howtowear.support.utils.HTWLog;
import com.an.howtowear.support.utils.NotificationUtil;

public class EventReceiver extends BroadcastReceiver {

    private static final int ALARM_PERIOD = 1000 * 60 * 60 * 24;
//    private static final int ALARM_PERIOD = 1000 * 60 * 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        HTWLog.d("Action : " + intent.getAction());

        if (intent != null) {
            // Alarm Intent
            if (intent.getAction() == HTWIntent.INTENT_WEATHER_NOTI) {
//                NotificationUtil.getInstance().showNotificationMessage("Alarm", "Alarm Received");
            }
        }
    }
}
