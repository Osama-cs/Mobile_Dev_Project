package com.example.c1943625.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int notes_id;

    @ColumnInfo(name = "notes_title")
    public String notes_title;

    @ColumnInfo(name ="notes_content")
    public String notes_content;

    @ColumnInfo(name ="notes_mood")
    public String notes_mood;

    public Notes(int notes_id, String notes_title, String notes_content, String notes_mood) {
        this.notes_id = notes_id;
        this.notes_title = notes_title;
        this.notes_content = notes_content;
        this.notes_mood = notes_mood;
    }

}