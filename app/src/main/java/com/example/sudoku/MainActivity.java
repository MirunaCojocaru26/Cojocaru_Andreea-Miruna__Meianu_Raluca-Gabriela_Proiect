package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_playgame).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ChooseDifficultyFragment fragment = new ChooseDifficultyFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment_difficulty, fragment,"main_activity_fragment").commit();
    }
}
