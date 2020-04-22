package com.example.sudoku;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;

public class SettingsFragment extends PreferenceFragment {
    CheckBoxPreference music;
    SharedPreferences settings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        settings = PreferenceManager.getDefaultSharedPreferences(getActivity());

        music = (CheckBoxPreference) getPreferenceScreen().findPreference("check_music");

        music.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String music_value = newValue.toString();
                if (music_value == "true") {
                    ((SettingsActivity)getActivity()).playMusic(getView());
                }
                else{
                    ((SettingsActivity)getActivity()).stopMusic(getView());
                }
                return true;
            }
        });

    }
}
