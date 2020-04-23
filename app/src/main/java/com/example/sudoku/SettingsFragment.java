package com.example.sudoku;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

import androidx.annotation.Nullable;

import static com.example.sudoku.MainActivity.Music;
import static com.example.sudoku.MainActivity.MyPREFERENCES;

public class SettingsFragment extends PreferenceFragment {
    SwitchPreference music,sound;
    SharedPreferences settings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        music = (SwitchPreference) getPreferenceScreen().findPreference("check_music");
        settings = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        music.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String music_value = newValue.toString();
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(Music, music_value);
                editor.apply();
                if (music_value == "true") {
                    if (!((SettingsActivity)getActivity()).isMyServiceRunning(service.class)) {
                        getActivity().startService(new Intent(getActivity().getApplicationContext(), service.class));
                    }
                }
                else{
                    if (((SettingsActivity)getActivity()).isMyServiceRunning(service.class)) {
                        getActivity().stopService(new Intent(getActivity().getApplicationContext(), service.class));
                    }
                }
                return true;
            }
        });


    }


}
