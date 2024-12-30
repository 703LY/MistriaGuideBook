package com.example.androidgameguide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForumDetailFragment extends Fragment {

    private DatabaseReference forumDatabase;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum_detail, container, false);
        long postId = getArguments().getLong("postId");
        DatabaseReference postReference = FirebaseDatabase.getInstance().getReference("ForumPosts").child(String.valueOf(postId));

        return view;
    }
}

