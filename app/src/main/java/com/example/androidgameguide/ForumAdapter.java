package com.example.androidgameguide;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
//import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumViewHolder> {

    private final List<ForumPost> forumPosts;

    public ForumAdapter(List<ForumPost> forumPosts) {
        this.forumPosts = forumPosts;
    }

    @NonNull
    @Override
    public ForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_item, parent, false);
        return new ForumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumViewHolder holder, int position) {
        ForumPost post = forumPosts.get(position);
        holder.tvTitle.setText(post.getTitle());
        holder.tvContent.setText(post.getContent());
        holder.tvLikes.setText(String.valueOf(post.getLikes()));
    }

    @Override
    public int getItemCount() {
        return forumPosts.size();
    }

    static class ForumViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent, tvLikes;
        ImageView ivLike, ivComments;

        public ForumViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.titleTextView);
            tvContent = itemView.findViewById(R.id.contentTextView);
            tvLikes = itemView.findViewById(R.id.likeCountTextView);
            ivLike = itemView.findViewById(R.id.starButton);
            ivComments = itemView.findViewById(R.id.replyButton);
        }
    }
}




