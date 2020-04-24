package com.example.sudoku;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TimeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        recyclerView = findViewById(R.id.recyclerview_times);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getTimes();
    }

    private void getTimes(){
        class GetTimes extends AsyncTask<Void,Void, List<Time>> {
            @Override
            protected List<Time> doInBackground(Void...voids){
                List<Time> timeList = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase().timeDao().getAll();
                return timeList;
            }
            @Override
            protected void onPostExecute(List<Time> times){
                super.onPostExecute(times);
                TimeAdapter adapter = new TimeAdapter(TimeActivity.this,times);
                recyclerView.setAdapter(adapter);
            }
        }
        GetTimes times = new GetTimes();
        times.execute();
    }
}
