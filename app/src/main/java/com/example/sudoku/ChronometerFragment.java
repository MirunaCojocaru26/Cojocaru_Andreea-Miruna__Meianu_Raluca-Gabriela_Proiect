package com.example.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import androidx.fragment.app.Fragment;

public class ChronometerFragment extends Fragment {
    Chronometer simpleChronometer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chronometer, container, false);
        simpleChronometer =rootView.findViewById(R.id.simpleChronometer);
        simpleChronometer.start();
        return rootView;
    }

    public void stop_chronometer()
    {
        simpleChronometer.stop();
        Intent intent = new Intent(this.getActivity(), PopupActivity.class);
        intent.putExtra("time",simpleChronometer.getText());
        startActivity(intent);
    }
}
