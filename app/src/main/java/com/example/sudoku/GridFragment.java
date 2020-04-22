package com.example.sudoku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class GridFragment extends Fragment {
    public int difficulty;
    public int[] sudoku[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_9x9game,null);
        initialization(rootView);
        makeSudoku(rootView);
        return rootView;
    }

    public void initialization(View view)
    {
        Bundle b = getActivity().getIntent().getExtras();
        difficulty = b.getInt("key");
        System.out.println(difficulty);
        GenerateSudoku mSudoku = new GenerateSudoku(9);
        mSudoku.fillValues();
        sudoku = mSudoku.getMat();
        View v = view.findViewById(R.id.gred_table);
        for (int i=1;i<=9;i++)
            for(int j=1;j<=9;j++)
            {
                String aux = "line"+i+"column"+j;
                TextView textView = v.findViewWithTag(aux);
                if(textView == null)
                    System.out.println(aux);
                textView.setText(String.valueOf(sudoku[i-1][j-1]));
            }
        int k;
        Random random = new Random();
        switch(difficulty)
        {
            case 1:{
                k = 20+random.nextInt(10);
                break;
            }
            case 2:{
                k = 30+random.nextInt(10);
                break;
            }
            case 3:{
                k = 40+random.nextInt(10);
                break;
            }
            default:{
                k = 50+random.nextInt(10);
            }
        }
        for(int i=0;i<k;i++)
        {
            int aux = random.nextInt(81);
            int x = aux /9+1;
            int y = aux %9+1;
            String del = "line"+x+"column"+y;
            TextView textView = v.findViewWithTag(del);
            if(textView.getText().toString() == " ")
                i--;
            else textView.setText(" ");
        }
    }

    void makeSudoku(View view)
    {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    System.out.println("Touch coordinates : " + event.getRawX() + "x" + event.getY());
                }
                return true;
            }
        });
    }

}
