package com.example.androidgameguide;

import java.io.Serializable;

public class ForumPost implements Serializable {
    private int id;
    private String title;
    private String content;
    private int likes;

    public ForumPost() {}

    public ForumPost(int id, String title, String content, int likes) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }
}


