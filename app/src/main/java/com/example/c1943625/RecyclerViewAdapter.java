package com.example.c1943625;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c1943625.database.Notes;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Notes> notesSet;
    Context context;


    //This is where the data that is needed for the recycler view is defined.
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtID;
        public TextView txtNotesTitle;
        public TextView txtNotesContent;
        public TextView txtNotesMood;
        public LinearLayout ll_notes_card;


        public ViewHolder(View view){
            super(view);

            txtID = view.findViewById(R.id.txtID);
            txtNotesTitle = view.findViewById(R.id.txtNotesTitle);
            txtNotesContent = view.findViewById(R.id.txtNotesContent);
            txtNotesMood = view.findViewById(R.id.txtNotesMood);
            ll_notes_card = view.findViewById(R.id.ll_notes_card);
        }


    }

    //A constructor that grabs notes in an ArrayList, and the context of the class.
    public RecyclerViewAdapter(ArrayList<Notes> notesSet, Context context){
        this.notesSet=notesSet;
        this.context=context;
    }

    //This is where the ViewHolder gets the xml file.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    //This is where it all gets put to together, and matches the values that the user inputted to the
    //xml file.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       LinearLayout ll_notes_card = holder.ll_notes_card;
       TextView txtID = holder.txtID;
       TextView txtNotesTitle = holder.txtNotesTitle;
       TextView txtNotesContent = holder.txtNotesContent;
       TextView txtNotesMood = holder.txtNotesMood;

        txtID.setText(notesSet.get(position).notes_id+"");
        txtNotesTitle.setText(notesSet.get(position).notes_title+"");
        txtNotesContent.setText(notesSet.get(position).notes_content+"");
        txtNotesMood.setText(notesSet.get(position).notes_mood);

       ll_notes_card.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int notes_id = notesSet.get(position).notes_id;
               String notes_title = notesSet.get(position).notes_title;
               String notes_content = notesSet.get(position).notes_content;
               String notes_mood = notesSet.get(position).notes_mood;

               Intent intent = new Intent(context,NotesDetailActivity.class);

               intent.putExtra("notes_id", notes_id);
               intent.putExtra("notes_title", notes_title);
               intent.putExtra("notes_content", notes_content);
               intent.putExtra("notes_mood", notes_mood);

               context.startActivity(intent);

           }
       });
    }

    @Override
    public int getItemCount() {
        return notesSet.size();
    }


}
