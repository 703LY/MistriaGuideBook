package com.example.androidgameguide;

public class CropsModel {
    private String name;
    private String image;
    private String buyPrice;
    private String sellPrice;

    public CropsModel() {}

    public CropsModel(String name, String image, String buyPrice, String sellPrice) {
        this.name = name;
        this.image = image;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }
}

