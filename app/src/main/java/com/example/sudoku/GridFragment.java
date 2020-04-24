package com.example.sudoku;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.Random;

public class GridFragment extends Fragment {
    public int difficulty;
    public int[] sudoku[];
    public View principal_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_9x9game, null);
        initialization(rootView);
        select_cell();
        return rootView;
    }

    //some initializations we need to start
    public void initialization(View view) {
        Bundle b = getActivity().getIntent().getExtras();
        difficulty = b.getInt("key");
        GenerateSudoku mSudoku = new GenerateSudoku(9);
        mSudoku.fillValues();
        sudoku = mSudoku.getMat();
        this.principal_view = view;
        int k;
        Random random = new Random();
        switch (difficulty) {
            case 1: {
                //k = 50 + random.nextInt(10);
                k=80;
                break;
            }
            case 2: {
                k = 40 + random.nextInt(10);
                break;
            }
            case 3: {
                k = 30 + random.nextInt(10);
                break;
            }
            default: {
                k = 20 + random.nextInt(10);
            }
        }
        start_grid(k);
    }

    //what the start grid looked like
    void start_grid(int k) {
        Random random = new Random();
        View v = principal_view.findViewById(R.id.gred_table);
        for (int i = 0; i < k; i++) {
            int aux = random.nextInt(81);
            int x = aux / 9 + 1;
            int y = aux % 9 + 1;
            String put = "line" + x + "column" + y;
            TextView textView = v.findViewWithTag(put);
            if (TextUtils.isEmpty(textView.getText())) {
                textView.setText(String.valueOf(sudoku[x - 1][y - 1]));
                textView.setTextColor(Color.parseColor("#000000"));
                sudoku[x - 1][y - 1] = 0;
            } else i--;
        }
    }

    //put a gray coloron the selected cell
    void select_cell() {
        View v = principal_view.findViewById(R.id.gred_table);
        for (int i = 1; i <= 9; i++)
            for (int j = 1; j <= 9; j++) {
                String aux = "line" + i + "column" + j;
                final TextView textView = v.findViewWithTag(aux);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        unselect_previous_cell();
                        textView.setBackgroundResource(R.drawable.cell_shape_selected);
                        see_cell(textView);
                        complet_cell(textView);
                    }
                });
            }
    }

    //deselect the previous cells each time a new one is selected
    void unselect_previous_cell() {
        View v = principal_view.findViewById(R.id.gred_table);
        for (int i = 1; i <= 9; i++)
            for (int j = 1; j <= 9; j++) {
                String aux = "line" + i + "column" + j;
                TextView textView = v.findViewWithTag(aux);
                if (textView != null) {
                    textView.setBackgroundResource(R.drawable.cell_shape);
                }
            }
    }

    //see all the cell with the same number as the selected one
    void see_cell(TextView textView) {
        View v = principal_view.findViewById(R.id.gred_table);
        if (!TextUtils.isEmpty(textView.getText())) {
            String number = textView.getText().toString();
            for (int i = 1; i <= 9; i++)
                for (int j = 1; j <= 9; j++) {
                    String aux = "line" + i + "column" + j;
                    TextView text = v.findViewWithTag(aux);
                    if (!TextUtils.isEmpty(text.getText()))
                        if (text.getText().toString().equals(number))
                            text.setBackgroundResource(R.drawable.cell_shape_selected);
                }
        }
    }

    //set the number of the selected cell
    void complet_cell(final TextView textView) {
        if (textView.getCurrentTextColor() != Color.BLACK) {
            principal_view.findViewById(R.id.btn_number_one).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("1");
                    textView.setTextColor(Color.parseColor("#0c1070"));
                    game_end();
                }
            });
            principal_view.findViewById(R.id.btn_number_two).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("2");
                    textView.setTextColor(Color.parseColor("#0c1070"));
                    game_end();
                }
            });
            principal_view.findViewById(R.id.btn_number_three).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("3");
                    textView.setTextColor(Color.parseColor("#0c1070"));
                    game_end();
                }
            });
            principal_view.findViewById(R.id.btn_number_four).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("4");
                    textView.setTextColor(Color.parseColor("#0c1070"));
                    game_end();
                }
            });
            principal_view.findViewById(R.id.btn_number_five).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("5");
                    textView.setTextColor(Color.parseColor("#0c1070"));
                    game_end();
                }
            });
            principal_view.findViewById(R.id.btn_number_six).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("6");
                    textView.setTextColor(Color.parseColor("#0c1070"));
                    game_end();
                }
            });
            principal_view.findViewById(R.id.btn_number_seven).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("7");
                    textView.setTextColor(Color.parseColor("#0c1070"));
                    game_end();
                }
            });
            principal_view.findViewById(R.id.btn_number_eight).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("8");
                    textView.setTextColor(Color.parseColor("#0c1070"));
                    game_end();
                }
            });
            principal_view.findViewById(R.id.btn_number_nine).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("9");
                    textView.setTextColor(Color.parseColor("#0c1070"));
                    game_end();
                }
            });
            principal_view.findViewById(R.id.btn_number_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText("");
                }
            });
        }
    }

    //verify if the game is completed
    void game_end() {
        if(verify_line() & verify_column() & verify_3x3grid())
        {
            ((GameActivity)getActivity()).acces_the_chronometer();
        }
    }

    //take every single line and verify to not have same numbers
    boolean verify_line() {
        View v = principal_view.findViewById(R.id.gred_table);
        for (int k = 1; k <= 9; k++)
            for (int i = 1; i <= 8; i++)
                for (int j = i + 1; j <= 9; j++) {
                    String aux1 = "line" + k + "column" + i;
                    TextView text1 = v.findViewWithTag(aux1);
                    String aux2 = "line" + k + "column" + j;
                    TextView text2 = v.findViewWithTag(aux2);
                    if (TextUtils.isEmpty(text1.getText()) | TextUtils.isEmpty(text2.getText()) | text1.getText().toString().equals(text2.getText().toString()))
                        return false;
                }
        return true;
    }

    //take every single column and verify to not have same numbers
    boolean verify_column() {
        View v = principal_view.findViewById(R.id.gred_table);
        for (int k = 1; k <= 9; k++)
            for (int i = 1; i <= 8; i++)
                for (int j = i + 1; j <= 9; j++) {
                    String aux1 = "line" + i + "column" + k;
                    TextView text1 = v.findViewWithTag(aux1);
                    String aux2 = "line" + j + "column" + k;
                    TextView text2 = v.findViewWithTag(aux2);
                    if (TextUtils.isEmpty(text1.getText()) | TextUtils.isEmpty(text2.getText()) | text1.getText().toString().equals(text2.getText().toString()))
                        return false;
                }
        return true;
    }

    //take every 3x3 grid and verify to not have same numbers
    boolean verify_3x3grid(){
        for(int i = 1;i<=9;i=i+3)
            for(int j = 1; j<=9;j=j+3)
                if(!verify_auxiliar(i,j))
                    return false;
        return true;
    }

    //auxiliar function for verify_3x3grid
    boolean verify_auxiliar(int x, int y )
    {
        View v = principal_view.findViewById(R.id.gred_table);
        for (int i=x; i< x+3;i++)
            for(int j =y;j<y+3;j++)
                for(int k =x; k<x+3;k++)
                    for(int h=y;h<y+3;h++)
                        if(!(i==k && h==j))
                        {
                            String aux1 = "line" + i + "column" + j;
                            TextView text1 = v.findViewWithTag(aux1);
                            String aux2 = "line" + k + "column" + h;
                            TextView text2 = v.findViewWithTag(aux2);
                            if (TextUtils.isEmpty(text1.getText()) | TextUtils.isEmpty(text2.getText()) | text1.getText().toString().equals(text2.getText().toString()))
                                return false;
                        }
        return true;
    }
}
