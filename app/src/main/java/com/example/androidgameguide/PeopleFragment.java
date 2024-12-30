package com.example.androidgameguide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PeopleFragment extends Fragment {

    private RecyclerView recyclerView;
    private PeopleAdapter peopleAdapter;

    public PeopleFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        Button bachelorsButton = view.findViewById(R.id.bachelorButton);
        Button bachelorettesButton = view.findViewById(R.id.bacheloretteButton);

        replaceFragment(new BachelorsFragment());

        bachelorsButton.setOnClickListener(v -> replaceFragment(new BachelorsFragment()));
        bachelorettesButton.setOnClickListener(v -> replaceFragment(new BachelorettesFragment()));

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}