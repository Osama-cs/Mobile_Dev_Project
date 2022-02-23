package com.example.c1943625.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDAO {

    @Insert
    Long insertNotes(Notes notes);

    @Update
    void updateNotes(Notes notes);

    @Query("select * from notes order by notes_title asc")
    List<Notes> getAllNotes();

}