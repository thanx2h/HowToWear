package com.an.howtowear.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.an.howtowear.utils.AlarmUtil;
import com.an.howtowear.utils.NotificationUtil;

public class EventReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("TEST_HTW", intent.toString());

        if(intent != null){
            if(intent.getAction() == AlarmUtil.ACTION_ALARM){
                NotificationUtil.getNotificationUtil().showNotificationMessage();
                AlarmUtil.getInstance().setAlarm();
            }

        } else {

        }
    }
}
