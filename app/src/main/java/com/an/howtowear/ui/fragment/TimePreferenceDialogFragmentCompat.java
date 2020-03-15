package com.an.howtowear.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.view.View;
import android.widget.TimePicker;

import com.an.howtowear.support.utils.AlarmUtil;
import com.an.howtowear.support.utils.HTWLog;
import com.an.howtowear.ui.dialog.TimePreference;

import java.util.Calendar;

public class TimePreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat implements DialogPreference.TargetFragment {

    private TimePicker timePicker = null;

    public static TimePreferenceDialogFragmentCompat newInstance(String key) {
        final TimePreferenceDialogFragmentCompat fragment = new TimePreferenceDialogFragmentCompat();
        final Bundle bundle = new Bundle(1);
        bundle.putString(ARG_KEY, key);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View onCreateDialogView(Context context) {
        timePicker = new TimePicker(context);
        return (timePicker);
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);
        timePicker.setIs24HourView(true);
        TimePreference pref = (TimePreference) getPreference();
        timePicker.setCurrentHour(pref.hour);
        timePicker.setCurrentMinute(pref.minute);
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            TimePreference pref = (TimePreference) getPreference();
            pref.hour = timePicker.getCurrentHour();
            pref.minute = timePicker.getCurrentMinute();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, pref.hour);
            calendar.set(Calendar.MINUTE, pref.minute);

//            String value = String.valueOf(calendar.getTimeInMillis());
//            HTWLog.d("value  : " + value + " date : " + calendar.getTime());
//            if (pref.callChangeListener(value)) pref.persistStringValue(value);

            // Alarm Setting
            AlarmUtil.getInstance().setAlarm(calendar.getTimeInMillis(), 0);
        }
    }

    @Override
    public Preference findPreference(CharSequence charSequence) {
        return getPreference();
    }


}
