package com.example.androidgameguide;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BacheloretteDetailsFragment extends Fragment {

    private ImageView characterImage;
    private TextView characterName, characterDescription, characterBirthday;
    private TextView lovedGifts, likedGifts, neutralGifts, hatedGifts;

    public BacheloretteDetailsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bachelorette_details, container, false);

        characterImage = view.findViewById(R.id.characterDetailImage);
        characterName = view.findViewById(R.id.characterDetailName);
        characterDescription = view.findViewById(R.id.characterDetailDescription);
        characterBirthday = view.findViewById(R.id.characterDetailBirthday);
        lovedGifts = view.findViewById(R.id.characterDetailLovedGifts);
        likedGifts = view.findViewById(R.id.characterDetailLikedGifts);
        neutralGifts = view.findViewById(R.id.characterDetailNeutralGifts);
        hatedGifts = view.findViewById(R.id.characterDetailHatedGifts);

        if (getArguments() != null) {
            String characterId = getArguments().getString("characterId");
            loadCharacterDetails(characterId);
        }

        return view;
    }

    private void loadCharacterDetails(String characterId) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child("people")
                .child(characterId);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    characterName.setText(snapshot.child("name").getValue(String.class));
                    characterDescription.setText(snapshot.child("description").getValue(String.class));
                    characterBirthday.setText(snapshot.child("birthday").getValue(String.class));
                    lovedGifts.setText("Loved Gifts: " + snapshot.child("gifts").child("loved").getValue(String.class));
                    likedGifts.setText("Liked Gifts: " + snapshot.child("gifts").child("liked").getValue(String.class));
                    neutralGifts.setText("Neutral Gifts: " + snapshot.child("gifts").child("neutral").getValue(String.class));
                    hatedGifts.setText("Hated Gifts: " + snapshot.child("gifts").child("hated").getValue(String.class));

                    Glide.with(requireContext())
                            .load(snapshot.child("imageUrl").getValue(String.class))
                            .placeholder(R.drawable.relationship_icon)
                            .error(R.drawable.relationship_icon)
                            .into(characterImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", error.getMessage());
            }
        });
    }
}
