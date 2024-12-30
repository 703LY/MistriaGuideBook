package com.example.androidgameguide;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CombatFragment extends Fragment {
    private RecyclerView recyclerView;
    private CombatAdapter combatAdapter;

    public CombatFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_combat, container, false);

        recyclerView = view.findViewById(R.id.combatRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<CombatModel> options = new FirebaseRecyclerOptions.Builder<CombatModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("combats"), CombatModel.class)
                .build();

        combatAdapter = new CombatAdapter(options);
        recyclerView.setAdapter(combatAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        combatAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        combatAdapter.stopListening();
    }
}
