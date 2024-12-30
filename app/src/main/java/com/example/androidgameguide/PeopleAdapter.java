package com.example.androidgameguide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PeopleAdapter extends FirebaseRecyclerAdapter<PeopleModel, PeopleAdapter.PeopleViewHolder> {

    public PeopleAdapter(@NonNull FirebaseRecyclerOptions<PeopleModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PeopleViewHolder holder, int position, @NonNull PeopleModel model) {
        holder.name.setText(model.getName());
        holder.description.setText(model.getDescription());
        holder.birthday.setText(model.getBirthday());

        Glide.with(holder.itemView.getContext())
                .load(model.getImageUrl())
                .placeholder(R.drawable.relationship_icon)
                .error(R.drawable.relationship_icon)
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Fragment fragment;

            if ("Bachelors".equals(model.getType())) {
                fragment = new BachelorDetailsFragment();
            } else if ("Bachelorettes".equals(model.getType())) {
                fragment = new BacheloretteDetailsFragment();
            } else {
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString("characterId", getRef(position).getKey());
            bundle.putString("characterType", model.getType());
            fragment.setArguments(bundle);

            ((AppCompatActivity) holder.itemView.getContext())
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        });
    }


    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people, parent, false);
        return new PeopleViewHolder(view);
    }

    static class PeopleViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, birthday;
        ImageView image;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.characterName);
            description = itemView.findViewById(R.id.characterDescription);
            birthday = itemView.findViewById(R.id.characterBirthday);
            image = itemView.findViewById(R.id.characterImage);
        }
    }
}
