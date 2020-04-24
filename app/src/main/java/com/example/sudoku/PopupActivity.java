package com.example.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PopupActivity extends Activity {
    public String time_finish;
    public String level_finish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);

        TextView textView = findViewById(R.id.chron_time_show);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("time"));
        time_finish = intent.getStringExtra("time");
        level_finish = intent.getStringExtra("level");
        saveScor();

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

    private void saveScor()
    {
        class SaveTask extends AsyncTask<Void,Void, Void>
        {
            @Override
            protected Void doInBackground(Void... voids) {
                Time time = new Time();
                time.setTime(time_finish);
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);
                time.setDate(formattedDate);
                time.setLevel(level_finish);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().timeDao().insert(time);
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
        }
        SaveTask saveTask = new SaveTask();
        saveTask.execute();
    }
}
