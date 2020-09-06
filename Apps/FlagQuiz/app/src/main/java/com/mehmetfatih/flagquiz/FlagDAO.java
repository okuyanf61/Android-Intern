package com.mehmetfatih.flagquiz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FlagDAO {
    @Query("SELECT * FROM flag_table ORDER BY RANDOM() LIMIT 5")
    LiveData<List<Flag>> getAll();

    @Query("SELECT * FROM flag_table WHERE flagId != :id ORDER BY RANDOM() LIMIT 3")
    LiveData<List<Flag>> getRandomWrong(int id);

    @Query("SELECT * FROM flag_table WHERE flagId != :id ORDER BY RANDOM() LIMIT 3")
    List<Flag> randomThreeWrong(int id);

    @Query("SELECT * FROM flag_table ORDER BY RANDOM() LIMIT 5")
    List<Flag> fiveQuestions();

    @Insert
    void insert(Flag flag);

    @Delete
    void delete(Flag flag);

    @Update
    void update(Flag flag);

    @Query("DELETE FROM flag_table")
    void deleteAll();
}
