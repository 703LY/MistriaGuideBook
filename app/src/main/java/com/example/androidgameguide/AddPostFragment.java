package com.example.androidgameguide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
public class AddPostFragment extends Fragment {

    private DatabaseReference databaseReference;
    private DatabaseReference notificationReference;
    public AddPostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);

        EditText editTextTitle = view.findViewById(R.id.post_titleET);
        EditText editTextContent = view.findViewById(R.id.post_contentET);
        Button buttonSubmitPost = view.findViewById(R.id.submitBtn);

        databaseReference = FirebaseDatabase.getInstance().getReference("forums");
        notificationReference = FirebaseDatabase.getInstance().getReference("notifications");

        buttonSubmitPost.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            String content = editTextContent.getText().toString().trim();

            if (!title.isEmpty() && !content.isEmpty()) {
                databaseReference.orderByChild("id").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot snapshot) {
                        int newId = 1;
                        for (DataSnapshot data : snapshot.getChildren()) {
                            ForumPost lastPost = data.getValue(ForumPost.class);
                            if (lastPost != null) {
                                newId = lastPost.getId() + 1;
                            }
                        }

                        // Create the new post
                        String postId = databaseReference.push().getKey();
                        if (postId != null) {
                            ForumPost newPost = new ForumPost(newId, title, content, 0);

                            databaseReference.child(postId).setValue(newPost)
                                    .addOnSuccessListener(aVoid -> {
                                        sendNotification(title, content);

                                        getParentFragmentManager().popBackStack();
                                    })
                                    .addOnFailureListener(e -> {
                                        e.printStackTrace();
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        error.toException().printStackTrace();
                    }
                });
            }
        });

        return view;
    }

    private void sendNotification(String title, String content) {
        DatabaseReference notificationReference = FirebaseDatabase.getInstance().getReference("notifications");

        Map<String, String> notificationData = new HashMap<>();
        notificationData.put("title", "New Forum Post: " + title);
        notificationData.put("body", content);

        notificationReference.push().setValue(notificationData)
                .addOnSuccessListener(aVoid -> {

                })
                .addOnFailureListener(e -> {
                    e.printStackTrace();
                });
    }
}