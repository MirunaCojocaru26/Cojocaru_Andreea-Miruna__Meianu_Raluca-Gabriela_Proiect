package com.example.sudoku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class SettingsActivity extends AppCompatActivity {
    MediaPlayer song;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null)
                return;
            getFragmentManager().beginTransaction().add(R.id.fragment_container, new SettingsFragment()).commit();
        }
    }
    public void playMusic(View view){
        if(song == null) {
            song = MediaPlayer.create(this, R.raw.sound);
            song.start();
        }
    }
    public void stopMusic(View view){
        song.stop();
        song = null;

    }

}
