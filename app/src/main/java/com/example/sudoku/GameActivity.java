package com.example.sudoku;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    int difficulty;
    int[][] sudoku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initialization();
    }

    public void initialization()
    {
        Bundle b = getIntent().getExtras();
        difficulty = b.getInt("key");
        Random rand = new Random();
        int k;
        switch (difficulty)
        {
            case 1: {
                k = 20+rand.nextInt(9);
                break;
            }
            case 2:{
                k = 30+rand.nextInt(9);
                break;
            }
            case 3:{
                k = 40+rand.nextInt(9);
                break;
            }
            default:{
                k = 50;
            }
        }
        System.out.println(k);
        Sudoku mSudoku = new Sudoku(9);
        mSudoku.fillValues();
        sudoku = mSudoku.getMat();
        removek(k);
    }

    public class Sudoku
    {
        int[] mat[];
        int N; // number of columns/rows.
        int SRN; // square root of N

        Sudoku(int N)
        {
            this.N = N;
            SRN = (int)Math.sqrt(N);
            mat = new int[N][N];
        }

        // Sudoku Generator
        public void fillValues()
        {
            fillDiagonal();
            fillRemaining(0, SRN);
        }

        // Fill the diagonal SRN number of SRN x SRN matrices
        void fillDiagonal()
        {
            for (int i = 0; i<N; i=i+SRN)
                fillBox(i, i);
        }

        // Returns false if given 3 x 3 block contains num.
        boolean unUsedInBox(int rowStart, int colStart, int num)
        {
            for (int i = 0; i<SRN; i++)
                for (int j = 0; j<SRN; j++)
                    if (mat[rowStart+i][colStart+j]==num)
                        return false;
            return true;
        }

        // Fill a 3 x 3 matrix.
        void fillBox(int row,int col)
        {
            int num;
            for (int i=0; i<SRN; i++)
            {
                for (int j=0; j<SRN; j++)
                {
                    do
                    {
                        num = randomGenerator(N);
                    }
                    while (!unUsedInBox(row, col, num));

                    mat[row+i][col+j] = num;
                }
            }
        }

        // Random generator
        int randomGenerator(int num)
        {
            return (int) Math.floor((Math.random()*num+1));
        }

        // Check if safe to put in cell
        boolean CheckIfSafe(int i,int j,int num)
        {
            return (unUsedInRow(i, num) && unUsedInCol(j, num) && unUsedInBox(i-i%SRN, j-j%SRN, num));
        }

        // check in the row for existence
        boolean unUsedInRow(int i,int num)
        {
            for (int j = 0; j<N; j++)
                if (mat[i][j] == num)
                    return false;
            return true;
        }

        // check in the row for existence
        boolean unUsedInCol(int j,int num)
        {
            for (int i = 0; i<N; i++)
                if (mat[i][j] == num)
                    return false;
            return true;
        }

        // A recursive function to fill remaining matrix
        boolean fillRemaining(int i, int j) {
            if (j >= N && i < N - 1) {
                i = i + 1;
                j = 0;
            }
            if (i >= N && j >= N)
                return true;
            if (i < SRN) {
                if (j < SRN)
                    j = SRN;
            } else if (i < N - SRN) {
                if (j == (int) (i / SRN) * SRN)
                    j = j + SRN;
            } else {
                if (j == N - SRN) {
                    i = i + 1;
                    j = 0;
                    if (i >= N)
                        return true;
                }
            }

            for (int num = 1; num <= N; num++) {
                if (CheckIfSafe(i, j, num)) {
                    mat[i][j] = num;
                    if (fillRemaining(i, j + 1))
                        return true;

                    mat[i][j] = 0;
                }
            }
            return false;
        }

        public int[][] getMat() {
            return mat;
        }
    }

    void removek(int k)
    {

    }
}
