package com.example.c1943625;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.c1943625.database.Notes;
import com.example.c1943625.database.NotesRepository;

public class NotesDetailActivity extends AppCompatActivity {

    EditText edt_notes_id, edt_notes_title, edt_notes_content;

    Button btn_update;

    RadioButton rbtn_happy, rbtn_sad;

    int intNotesId;

    String strNotesTitle = "", strNotesContent = "", strNotesMood = "";

    String strNotesId_updated = "", strNotesTitle_updated = "", strNotesContent_updated = "", strNotesMood_updated = "";

    //This create method here is where the user will click on the an item on the recycler view and be
    //taken to the item they wish to update the data.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detail);

        edt_notes_id = (EditText) findViewById(R.id.notes_id);
        edt_notes_title = (EditText) findViewById(R.id.notes_title);
        edt_notes_content = (EditText) findViewById(R.id.notes_content);
        rbtn_happy = (RadioButton) findViewById(R.id.rbtn_happy);
        rbtn_sad = (RadioButton) findViewById(R.id.rbtn_sad);
        btn_update = (Button) findViewById(R.id.btn_update);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            intNotesId = data.getInt("notes_id");
            strNotesTitle = data.getString("notes_title");
            strNotesContent = data.getString("notes_content");
            strNotesMood = data.getString("notes_mood");

        }

        edt_notes_id.setText(intNotesId + "");
        edt_notes_title.setText(strNotesTitle + "");
        edt_notes_content.setText(strNotesContent + "");

        if (strNotesMood.trim().toLowerCase().equalsIgnoreCase("happy")) {
            rbtn_happy.setChecked(true);
        }
        if (strNotesMood.trim().toLowerCase().equalsIgnoreCase("sad")) {
            rbtn_sad.setChecked(true);
        }

        //This is similar to the submit button, but where we are using these updated values here,
        //so when you click the button it will then overwrite the data.
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_notes_title.getText().toString().isEmpty()
                        || edt_notes_content.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill in the fields!", Toast.LENGTH_LONG).show();
                } else {

                    strNotesId_updated = edt_notes_id.getText().toString().trim();
                    strNotesTitle_updated = edt_notes_title.getText().toString().trim();
                    strNotesContent_updated = edt_notes_content.getText().toString().trim();

                    if (rbtn_happy.isChecked()) {
                        strNotesMood_updated = "Happy";
                    }
                    else if (rbtn_sad.isChecked()) {
                        strNotesMood_updated = "Sad";
                    }

                    NotesRepository notesRepository = new NotesRepository(getApplicationContext());
                    Notes notes = new Notes(Integer.parseInt(String.valueOf(intNotesId)), strNotesTitle_updated, strNotesContent_updated,strNotesMood_updated);
                    openDialog();
                    notesRepository.UpdateTask(notes);
                }
            }
        });


    }

    //This is where the Dialog can ve accessed in this class so we can call it later.
    private void openDialog() {
        DialogMaker dialogMaker = new DialogMaker();
        dialogMaker.show(getSupportFragmentManager(), "example dialog");
    }

}