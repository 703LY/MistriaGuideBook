package com.example.androidgameguide;

public class MuseumItem {
    private String id;
    private String category;
    private String name;
    private boolean checked;

    public MuseumItem() {}

    public MuseumItem(String id, String category, String name, boolean checked) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.checked = checked;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
