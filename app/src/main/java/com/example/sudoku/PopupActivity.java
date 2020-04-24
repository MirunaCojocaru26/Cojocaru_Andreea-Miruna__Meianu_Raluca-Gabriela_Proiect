package com.example.sudoku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import static com.example.sudoku.MainActivity.MyPREFERENCES;
import static com.example.sudoku.MainActivity.Theme;

public class PopupActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        setContentView(R.layout.popup_window);

        TextView textView = findViewById(R.id.chron_time_show);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("time"));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.4));

        findViewById(R.id.pop_up_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
