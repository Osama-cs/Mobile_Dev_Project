package com.example.c1943625.database;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;

public class NotesRepository {
    private String DB_NAME = "notesdb";

    private NotesDatabase notesDatabase;

    Context context;

    public NotesRepository(Context context) {
        this.context = context;
        notesDatabase = Room.databaseBuilder(context, NotesDatabase.class, DB_NAME).build();
    }

    public void InsertTask(final Notes notes) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                notesDatabase.notesDao().insertNotes(notes);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context, notes.notes_title + " Has been noted, Good job!", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }


    public List<Notes> getNotes() {
        List<Notes> notesList = notesDatabase.notesDao().getAllNotes();
        return notesList;
    }

    public void UpdateTask(final Notes notes) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                notesDatabase.notesDao().updateNotes(notes);
                return null;
            }
        }.execute();

    }


}
