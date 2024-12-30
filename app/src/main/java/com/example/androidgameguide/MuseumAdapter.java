package com.example.androidgameguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MuseumAdapter extends RecyclerView.Adapter<MuseumAdapter.ViewHolder> {
    private List<MuseumItem> itemList;
    private DatabaseReference databaseReference;

    public MuseumAdapter(List<MuseumItem> itemList, DatabaseReference databaseReference) {
        this.itemList = itemList;
        this.databaseReference = databaseReference;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_museum, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MuseumItem item = itemList.get(position);
        holder.itemName.setText(item.getName());
        holder.checkBox.setChecked(item.isChecked());
        holder.itemIcon.setImageResource(R.drawable.museumarcheologywing_icon); // TODO

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            databaseReference.child(item.getId()).child("checked").setValue(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        CheckBox checkBox;
        ImageView itemIcon;

        public ViewHolder( View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            checkBox = itemView.findViewById(R.id.item_checkbox);
            itemIcon = itemView.findViewById(R.id.item_icon);
        }
    }
}

