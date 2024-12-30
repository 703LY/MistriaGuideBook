package com.example.androidgameguide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CropsFragment extends Fragment {

    private RecyclerView recyclerViewFall, recyclerViewSpring, recyclerViewSummer, recyclerViewWinter;
    private CropsAdapter fallAdapter, springAdapter, summerAdapter, winterAdapter;
    private List<CropsModel> fallCropList, springCropList, summerCropList, winterCropList;
    private DatabaseReference databaseReference;

    public CropsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crops, container, false);

        // Initialize RecyclerViews
        recyclerViewFall = view.findViewById(R.id.recyclerViewFall);
        recyclerViewSpring = view.findViewById(R.id.recyclerViewSpring);
        recyclerViewSummer = view.findViewById(R.id.recyclerViewSummer);
        recyclerViewWinter = view.findViewById(R.id.recyclerViewWinter);

        recyclerViewFall.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerViewSpring.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerViewSummer.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerViewWinter.setLayoutManager(new GridLayoutManager(getContext(), 3));

        fallCropList = new ArrayList<>();
        springCropList = new ArrayList<>();
        summerCropList = new ArrayList<>();
        winterCropList = new ArrayList<>();

        fallAdapter = new CropsAdapter(getContext(), fallCropList);
        springAdapter = new CropsAdapter(getContext(), springCropList);
        summerAdapter = new CropsAdapter(getContext(), summerCropList);
        winterAdapter = new CropsAdapter(getContext(), winterCropList);

        recyclerViewFall.setAdapter(fallAdapter);
        recyclerViewSpring.setAdapter(springAdapter);
        recyclerViewSummer.setAdapter(summerAdapter);
        recyclerViewWinter.setAdapter(winterAdapter);

        // Load data for each season
        loadSeasonData("fall", fallCropList, fallAdapter);
        loadSeasonData("spring", springCropList, springAdapter);
        loadSeasonData("summer", summerCropList, summerAdapter);
        loadSeasonData("winter", winterCropList, winterAdapter);

        return view;
    }

    private void loadSeasonData(String season, List<CropsModel> cropList, CropsAdapter adapter) {
        databaseReference = FirebaseDatabase.getInstance().getReference("crops/" + season);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                cropList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CropsModel crop = dataSnapshot.getValue(CropsModel.class);
                    cropList.add(crop);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle error
            }
        });
    }
}

