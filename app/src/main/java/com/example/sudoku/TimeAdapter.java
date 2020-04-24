package com.example.sudoku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimesViewHolder> {
    private Context myContext;
    private List<Time> timeList;

    public TimeAdapter(Context myContext,List<Time> timeList){
        this.myContext = myContext;
        this.timeList = timeList;
    }

    @Override
    public TimesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(myContext).inflate(R.layout.recyclerview_times,parent,false);
        return new TimesViewHolder(view);
    }
    @Override
    public void onBindViewHolder(TimesViewHolder holder,int position){
        Time time = timeList.get(position);
        holder.textViewDate.setText(time.getDate());
        holder.textViewTime.setText(time.getTime());
    }
    @Override
    public int getItemCount(){
        return timeList.size();
    }

    class TimesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewDate, textViewTime;
        public TimesViewHolder(View itemView){
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
        }

    }
}
