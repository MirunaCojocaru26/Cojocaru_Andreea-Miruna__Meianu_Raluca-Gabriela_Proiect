package com.example.sudoku;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.sudoku.MainActivity.MyPREFERENCES;
import static com.example.sudoku.MainActivity.Theme;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_game);
    }
}
