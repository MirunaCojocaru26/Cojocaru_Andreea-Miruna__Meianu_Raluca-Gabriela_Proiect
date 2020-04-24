package com.example.sudoku;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Time.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TimeDao timeDao();
}
