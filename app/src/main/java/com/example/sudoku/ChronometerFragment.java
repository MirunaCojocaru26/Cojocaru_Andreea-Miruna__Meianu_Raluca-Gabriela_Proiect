package com.example.sudoku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import androidx.fragment.app.Fragment;

public class ChronometerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chronometer, container, false);
        Chronometer simpleChronometer =rootView.findViewById(R.id.simpleChronometer);
        simpleChronometer.start();
        return rootView;
    }
}
