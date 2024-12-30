package com.example.androidgameguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CropsAdapter extends RecyclerView.Adapter<CropsAdapter.CropViewHolder> {

    private Context context;
    private List<CropsModel> cropList;

    public CropsAdapter(Context context, List<CropsModel> cropList) {
        this.context = context;
        this.cropList = cropList;
    }

    @Override
    public CropViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.crop_item, parent, false);
        return new CropViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CropViewHolder holder, int position) {
        CropsModel crop = cropList.get(position);

        holder.cropName.setText(crop.getName());
        holder.cropPrice.setText("Buy: " + crop.getBuyPrice() + " | Sell: " + crop.getSellPrice());
        Glide.with(context).load(crop.getImage()).into(holder.cropImage);
    }

    @Override
    public int getItemCount() {
        return cropList.size();
    }

    public static class CropViewHolder extends RecyclerView.ViewHolder {
        ImageView cropImage;
        TextView cropName, cropPrice;

        public CropViewHolder(View itemView) {
            super(itemView);
            cropImage = itemView.findViewById(R.id.cropImage);
            cropName = itemView.findViewById(R.id.cropName);
            cropPrice = itemView.findViewById(R.id.cropPrice);
        }
    }
}

