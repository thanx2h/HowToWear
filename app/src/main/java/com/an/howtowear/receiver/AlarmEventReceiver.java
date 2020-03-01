package com.an.howtowear.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.an.howtowear.support.utils.AlarmUtil;
import com.an.howtowear.support.utils.HTWLog;
import com.an.howtowear.support.utils.NotificationUtil;

public class AlarmEventReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        HTWLog.d(intent.toString());

        if (intent != null) {
            // Alarm Intent
            if (intent.getAction() == AlarmUtil.ACTION_ALARM) {
                NotificationUtil.getInstance().showNotificationMessage();
//                AlarmUtil.getInstance().setAlarm();
            }
        }
    }
}
