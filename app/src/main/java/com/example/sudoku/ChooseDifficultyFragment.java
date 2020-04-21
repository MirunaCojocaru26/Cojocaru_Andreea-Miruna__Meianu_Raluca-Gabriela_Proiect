package com.example.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

public class ChooseDifficultyFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_difficulty, container, false);
        setButtons(view);
        return view;
    }

    private void setButtons(View view)
    {
        view.findViewById(R.id.btn_easygame).setOnClickListener(this);
        view.findViewById(R.id.btn_mediumgame).setOnClickListener(this);
        view.findViewById(R.id.btn_hardgame).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_easygame: {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 1);
                intent.putExtras(b);
                startActivity(intent);
                break;
            }
            case R.id.btn_mediumgame:{
                Intent intent = new Intent(getActivity(), GameActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 2);
                intent.putExtras(b);
                startActivity(intent);
                break;
            }
            case R.id.btn_hardgame:{
                Intent intent = new Intent(getActivity(), GameActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", 3);
                intent.putExtras(b);
                startActivity(intent);
                break;
            }
        }
    }
}
