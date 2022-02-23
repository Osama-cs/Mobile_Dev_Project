package com.example.c1943625;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.c1943625.database.Notes;
import com.example.c1943625.database.NotesRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;

public class ViewNotesActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Notes> notesArrayList;
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        //This is here to setup the bottom navigation view so that the user can jump to page to page.
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.botton_nav);

        bottomNavigationView.setSelectedItemId(R.id.view_journal_entries);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            //This is here so it can swap between activity to activity.
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.view_journal_entries:
                        startActivity(new Intent(getApplicationContext(),
                                ViewNotesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home_nav:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.make_a_journal_entry:
                        startActivity(new Intent(getApplicationContext(),
                                AddNoteActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        //This is where the RecyclerView finds the id from the xml so it can be put into motion.
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new LoadDataTask().execute();
    }

    //This class is here to load the data that is going to get put into the RecyclerView/CardView.
    class LoadDataTask extends AsyncTask<Void, Void, Void> {
        NotesRepository notesRepository;
        List<Notes> notesList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            notesRepository = new NotesRepository(getApplicationContext());

        }

        @Override
        protected Void doInBackground(Void... voids) {
            notesList = notesRepository.getNotes();
            notesArrayList = new ArrayList<>();

            for (int i = 0; i < notesList.size(); i++) {
                notesArrayList.add(notesList.get(i));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(notesArrayList, ViewNotesActivity.this);
            recyclerView.setAdapter(recyclerViewAdapter);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new LoadDataTask().execute();
    }
}