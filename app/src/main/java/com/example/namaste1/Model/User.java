package com.example.namaste1.Model;


public class User {


    private String id;
    private String username_;
    private String imageURL;


    public User(String id, String username_, String imageURL) {
        this.id = id;
        this.username_ = username_;
        this.imageURL = imageURL;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername_() {
        return username_;
    }

    public void setUsername_(String username_) {
        this.username_ = username_;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }



}
