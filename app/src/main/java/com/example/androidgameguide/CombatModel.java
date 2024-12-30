package com.example.androidgameguide;

public class CombatModel {
    private int damage;
    private int hp;
    private String drops;
    private String name;
    private String imageUrl;

    public CombatModel() {
    }

    public CombatModel(int damage, int hp, String drops, String name, String imageUrl) {
        this.damage = damage;
        this.hp = hp;
        this.drops = drops;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getDrops() {
        return drops;
    }

    public void setDrops(String drops) {
        this.drops = drops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
