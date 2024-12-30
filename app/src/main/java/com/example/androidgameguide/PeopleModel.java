package com.example.androidgameguide;

public class PeopleModel {
    private String name;
    private String description;
    private String birthday;
    private String imageUrl;
    private String type;

    public PeopleModel() {}

    public PeopleModel(String name, String description, String birthday, String imageUrl, String type) {
        this.name = name;
        this.description = description;
        this.birthday = birthday;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


