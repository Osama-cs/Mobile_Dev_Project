package com.example.c1943625;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.c1943625.database.Notes;
import com.example.c1943625.database.NotesRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.c1943625.BaseApplication.CHANNEL_1_ID;

public class AddNoteActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;

    EditText notes_id,notes_title, notes_content;

    Button btn_submit;

    RadioButton rbtn_happy, rbtn_sad;

    String id, title, content, mood;

    BottomNavigationView bottomNavigationView;


    //This here is the create method that will allow for the user to input data into the database through
    //text on the page.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        //This notification manger is here so that the app can send notifications on this page.
        notificationManager = NotificationManagerCompat.from(this);

        //This is here to setup the bottom navigation view so that the user can jump to page to page.
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.botton_nav);

        bottomNavigationView.setSelectedItemId(R.id.make_a_journal_entry);

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


        notes_id = (EditText) findViewById(R.id.notes_id);
        notes_title = (EditText) findViewById(R.id.notes_title);
        notes_content = (EditText) findViewById(R.id.notes_content);
        rbtn_happy = (RadioButton) findViewById(R.id.rbtn_happy);
        rbtn_sad = (RadioButton) findViewById(R.id.rbtn_sad);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        //This button takes the data from the what is written in the xml and then makes it into a new
        //Notes object, then goes to the insert task method to insert the data in the database.
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notes_title.getText().toString().isEmpty() || notes_content.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in the text fields to continue!", Toast.LENGTH_SHORT).show();
                } else {
                    title = notes_title.getText().toString().trim();
                    content = notes_content.getText().toString().trim();
                    id = notes_id.getText().toString().trim();

                    if (rbtn_happy.isChecked()){
                        mood = "Happy";
                    }else if (rbtn_sad.isChecked()){
                        mood = "Sad";
                    }

                    NotesRepository notesRepository = new NotesRepository(getApplicationContext());
                    Notes notes = new Notes(Integer.parseInt(id),title, content, mood);
                    notesRepository.InsertTask(notes);
                    sendOnChannel(v);
                    finish();
                }
            }
        });

    }

    //This is where the notification is setup and will be set to the phone when the code has been submitted.
    public void sendOnChannel(View v){
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1,notification);
    }
}