package com.an.howtowear.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SwitchPreferenceCompat;

import com.an.howtowear.R;
import com.an.howtowear.config.PreferenceKey;
import com.an.howtowear.support.utils.AlarmUtil;
import com.an.howtowear.ui.dialog.TimePreference;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
    private SwitchPreferenceCompat pushPreference;
    private TimePreference timePreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_preference);
        pushPreference = (SwitchPreferenceCompat)findPreference(PreferenceKey.WEATHER_PUSH.getValue());
        timePreference = (TimePreference)findPreference(PreferenceKey.TIME_SETTING.getValue());

        pushPreference.setOnPreferenceChangeListener(this);
//        timePreference.setOnPreferenceChangeListener(this);

        if(pushPreference.isChecked()){
            timePreference.setVisible(true);
        } else {
            timePreference.setVisible(false);
        }
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        DialogFragment dialogFragment = TimePreferenceDialogFragmentCompat.newInstance(preference.getKey());
        dialogFragment.setTargetFragment(this, 0);
        dialogFragment.show(getFragmentManager(), null);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        switch (preference.getKey()){
            case "weather_push" :
                if((boolean)newValue)
                    timePreference.setVisible(true);
                else {
                    timePreference.setVisible(false);
//                    AlarmUtil.getInstance().cancelAlarm();
                }
                break;

            default:
                break;
        }

        return true;
    }
}

