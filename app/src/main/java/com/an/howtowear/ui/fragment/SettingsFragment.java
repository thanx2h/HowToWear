package com.an.howtowear.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.an.howtowear.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_preference);
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        DialogFragment dialogFragment = TimePreferenceDialogFragmentCompat.newInstance(preference.getKey());
        dialogFragment.setTargetFragment(this, 0);
        dialogFragment.show(getFragmentManager(), null);
    }
}

