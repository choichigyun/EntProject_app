package com.example.teama.DTO;

import java.io.Serializable;

public class UserReviewDTO implements Serializable {
    private String user_review;
    private String user_reviewTitle;
    private String user_review_photo;
    private String user_nick;
    private String ent_id;

    public UserReviewDTO(String user_review, String user_nick, String ent_id) {
        this.user_review = user_review;
        this.user_nick = user_nick;
        this.ent_id = ent_id;
    }

    public String getEnt_id() {
        return ent_id;
    }

    public void setEnt_id(String ent_id) {
        this.ent_id = ent_id;
    }

    public UserReviewDTO(){};

    public String getUser_review() {
        return user_review;
    }

    public void setUser_review(String user_review) {
        this.user_review = user_review;
    }

    public String getUser_reviewTitle() {
        return user_reviewTitle;
    }

    public void setUser_reviewTitle(String user_reviewTitle) {
        this.user_reviewTitle = user_reviewTitle;
    }

    public String getUser_review_photo() {
        return user_review_photo;
    }

    public void setUser_review_photo(String user_review_photo) {
        this.user_review_photo = user_review_photo;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    public UserReviewDTO(String user_review, String user_reviewTitle, String user_review_photo, String user_nick) {
        this.user_review = user_review;
        this.user_reviewTitle = user_reviewTitle;
        this.user_review_photo = user_review_photo;
        this.user_nick = user_nick;
    }
}


