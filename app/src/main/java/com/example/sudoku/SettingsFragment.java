package com.example.sudoku;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

import androidx.annotation.Nullable;

import static com.example.sudoku.MainActivity.Change;
import static com.example.sudoku.MainActivity.Language;
import static com.example.sudoku.MainActivity.Music;
import static com.example.sudoku.MainActivity.MyPREFERENCES;
import static com.example.sudoku.MainActivity.Theme;

public class SettingsFragment extends PreferenceFragment {
    SwitchPreference music,sound;
    ListPreference language, theme;
    SharedPreferences settings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        settings = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        language = (ListPreference)getPreferenceScreen().findPreference("key_language");
        language.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String language = newValue.toString();
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(Language, language);
                editor.putString(Change,"true");
                editor.apply();
                getActivity().finish();
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);

                return true;
            }
        });

        theme = (ListPreference)getPreferenceScreen().findPreference("key_theme");
        theme.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String theme_value = newValue.toString();
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(Theme, theme_value);
                editor.putString(Change,"true");
                editor.apply();
                getActivity().finish();
                Intent intent = getActivity().getIntent();
//                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                return true;
            }
        });



        music = (SwitchPreference) getPreferenceScreen().findPreference("check_music");
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
