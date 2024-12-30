package com.example.androidgameguide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ForumFragment extends Fragment {

    private RecyclerView recyclerView;
    private ForumAdapter adapter;
    private List<ForumPost> forumPosts;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewForum);
        FloatingActionButton fabAddPost = view.findViewById(R.id.fabAddPost);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        forumPosts = new ArrayList<>();
        adapter = new ForumAdapter(forumPosts);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("forums");

        loadDataFromFirebase();

        // Add new post
        fabAddPost.setOnClickListener(v -> {
            Fragment addPostFragment = new AddPostFragment();
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, addPostFragment)
                    .addToBackStack(null)
                    .commit();
        });


        return view;
    }

    private void loadDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                forumPosts.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    ForumPost post = postSnapshot.getValue(ForumPost.class);
                    forumPosts.add(post);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });
    }
}




