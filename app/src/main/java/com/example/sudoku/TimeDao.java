package com.example.sudoku;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TimeDao {
    @Query("SELECT * FROM time")
    List<Time> getAll();

    @Insert
    void insert(Time time);

    @Delete
    void delete(Time time);
}
