package com.example.androidgameguide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class BachelorettesFragment extends Fragment {

    private RecyclerView recyclerView;
    private PeopleAdapter bachelorettesAdapter;

    public BachelorettesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bachelorettes, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewBachelorettes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Query query = FirebaseDatabase.getInstance().getReference()
                .child("people")
                .orderByChild("type")
                .equalTo("Bachelorettes");

        FirebaseRecyclerOptions<PeopleModel> options = new FirebaseRecyclerOptions.Builder<PeopleModel>()
                .setQuery(query, PeopleModel.class)
                .build();

        bachelorettesAdapter = new PeopleAdapter(options);
        recyclerView.setAdapter(bachelorettesAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (bachelorettesAdapter != null) {
            bachelorettesAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (bachelorettesAdapter != null) {
            bachelorettesAdapter.stopListening();
        }
    }
}
