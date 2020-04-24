package com.example.sudoku;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Music = "music";
    public static final String Language = "lang";
    public static final String Theme = "theme";
    public static final String Change = "change";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //change theme
        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String value_theme = sharedPreferences.getString(Theme,"");
        switch (value_theme){
            case "dark":
                setTheme(R.style.AppTheme);
                break;
            case "red":
                setTheme(R.style.AppTheme1);
                break;
        }
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        setAppLocale(sharedPreferences.getString(Language,""));
        actionBar.setTitle("Main");
        //play music
        System.out.println(sharedPreferences.getString(Music,""));
        if(sharedPreferences.getString(Music,"").equals("true")){
            if (!isMyServiceRunning(service.class)) {
                startService(new Intent(MainActivity.this, service.class));
            }
        }
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_scors).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimeActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RulesActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_playgame).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        System.out.println("intru in onStart");
        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String change = sharedPreferences.getString(Change,"");
        if(change.equals("true")){
            System.out.println("intra in if");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Change, "false");
            editor.apply();
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        ChooseDifficultyFragment fragment = new ChooseDifficultyFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment_difficulty, fragment,"main_activity_fragment").commit();
    }


    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void setAppLocale(String localeCode){
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(config, dm);
    }

}
