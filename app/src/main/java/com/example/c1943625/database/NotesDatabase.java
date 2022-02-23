package com.example.c1943625.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Notes.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NotesDAO notesDao();
}
