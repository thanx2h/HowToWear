package com.an.howtowear.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.widget.Toast;

import com.an.howtowear.R;
import com.an.howtowear.config.PreferenceKey;
import com.an.howtowear.support.utils.AlarmUtil;
import com.an.howtowear.support.utils.HTWLog;
import com.an.howtowear.ui.dialog.TimePreference;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
    private SwitchPreferenceCompat pushPreference;
    private TimePreference timePreference;
    private static final int REQCODE_BATTERY_OPTIMIZATION = 0x1;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_preference);
        pushPreference = (SwitchPreferenceCompat) findPreference(PreferenceKey.WEATHER_PUSH.getValue());
        timePreference = (TimePreference) findPreference(PreferenceKey.TIME_SETTING.getValue());

        pushPreference.setOnPreferenceChangeListener(this);
//        timePreference.setOnPreferenceChangeListener(this);

        if (pushPreference.isChecked()) {
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

        switch (preference.getKey()) {
            case "weather_push":
                if ((boolean) newValue) {
                    if (!isIgnoringBatteryOptimizations(getActivity())) {
                        requestIgnoringBatteryOptimazition();
                    }
                    timePreference.setVisible(true);
                } else {
                    timePreference.setVisible(false);
                    AlarmUtil.getInstance().cancelAlarm(getActivity());
                }
                break;

            default:
                break;
        }

        return true;
    }

    private void requestIgnoringBatteryOptimazition() {
        // 수정 필요
        AlertDialog.Builder setdialog = new AlertDialog.Builder(getActivity());
        setdialog.setTitle("추가 설정이 필요합니다.")
                .setMessage("어플을 문제없이 사용하기 위해서는 해당 어플을 \"배터리 사용량 최적화\" 목록에서 \"제외\"해야 합니다. 설정화면으로 이동하시겠습니까?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
                        startActivityForResult(intent, REQCODE_BATTERY_OPTIMIZATION);
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "설정을 취소했습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }

    private boolean isIgnoringBatteryOptimizations(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        HTWLog.d("requestCode : " + requestCode + " resultCode : " + resultCode);
//        super.onActivityResult(requestCode, resultCode, data);
        if(REQCODE_BATTERY_OPTIMIZATION == 0){
            if(resultCode == 0){
                Toast.makeText(getActivity(), "배터리 사용량 최적화 제외 적용 완료되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "배터리 사용량 최적화 제외 적용 취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}

