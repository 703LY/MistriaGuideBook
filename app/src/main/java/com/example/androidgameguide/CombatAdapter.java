package com.example.androidgameguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CombatAdapter extends FirebaseRecyclerAdapter<CombatModel, CombatAdapter.CombatViewHolder> {

    public CombatAdapter(@NonNull FirebaseRecyclerOptions<CombatModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CombatViewHolder holder, int position, @NonNull CombatModel model) {
        holder.combatName.setText(model.getName());
//        holder.combatHP.setText(holder.itemView.getContext().getString(R.string.hp_placeholder, model.getHp()));
//        holder.combatDmg.setText(holder.itemView.getContext().getString(R.string.damage_placeholder, model.getDamage()));
//        holder.combatDrops.setText(holder.itemView.getContext().getString(R.string.drops_placeholder, model.getDrops()));
        holder.combatHP.setText(String.valueOf(model.getHp()));
        holder.combatDmg.setText(String.valueOf(model.getDamage()));
        holder.combatDrops.setText(model.getDrops());

        Glide.with(holder.itemView.getContext())
                .load(model.getImageUrl())
                .placeholder(R.drawable.combats_icon)
                .error(R.drawable.combats_icon)
                .into(holder.combatImage);
    }

    @NonNull
    @Override
    public CombatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.combat_item, parent, false);
        return new CombatViewHolder(view);
    }

    static class CombatViewHolder extends RecyclerView.ViewHolder {
        TextView combatName, combatHP, combatDmg, combatDrops;
        ImageView combatImage; // Use CircleImageView

        public CombatViewHolder(@NonNull View itemView) {
            super(itemView);
            combatName = itemView.findViewById(R.id.combat_nameTV);
            combatHP = itemView.findViewById(R.id.combat_hpTV);
            combatDmg = itemView.findViewById(R.id.combat_dmgTV);
            combatDrops = itemView.findViewById(R.id.combat_dropsTV);
            combatImage = itemView.findViewById(R.id.imageview1);
        }
    }
}
