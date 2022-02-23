package com.example.c1943625;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UsefulLinkFragment extends Fragment {

    TextView hyperLinkView;


    public UsefulLinkFragment() {
        // Required empty public constructor
    }

    //This is here to setup the bottom navigation view so that the user can jump to page to page.
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                //This is here so it can swap between activity to activity.
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.view_journal_entries:
                            startActivity(new Intent(getContext(),
                                    ViewNotesActivity.class));
                            getActivity().overridePendingTransition(0, 0);
                            return true;
                        case R.id.home_nav:
                            startActivity(new Intent(getContext(),
                                    MainActivity.class));
                            getActivity().overridePendingTransition(0, 0);
                            return true;
                        case R.id.make_a_journal_entry:
                            startActivity(new Intent(getContext(),
                                    AddNoteActivity.class));
                            getActivity().overridePendingTransition(0, 0);
                            return true;
                    }
                    return false;
                }
            };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Because this is a fragment the BottomNavigationMenu code had to be don in a different way
    //so that it can work with the code.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_useful_links, container, false);

        BottomNavigationView BNV = (BottomNavigationView) v.findViewById(R.id.botton_nav);

        BNV.setSelectedItemId(R.id.home_nav);

        BNV.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        hyperLinkView = (TextView) v.findViewById(R.id.HL);

        hyperLinkView.setMovementMethod(LinkMovementMethod.getInstance());

        return v;
    }
}