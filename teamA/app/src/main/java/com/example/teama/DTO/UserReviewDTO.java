package com.example.teama.DTO;

import java.io.Serializable;

public class UserReviewDTO implements Serializable {
    private String review;
    private String rvpicture_path;
    private String users_nick;
    private String ent_nick;
    private int review_no;

    public int getReview_no() {
        return review_no;
    }

    public void setReview_no(int review_no) {
        this.review_no = review_no;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRvpicture_path() {
        return rvpicture_path;
    }

    public void setRvpicture_path(String rvpicture_path) {
        this.rvpicture_path = rvpicture_path;
    }

    public String getUsers_nick() {
        return users_nick;
    }

    public void setUsers_nick(String users_nick) {
        this.users_nick = users_nick;
    }

    public String getEnt_nick() {
        return ent_nick;
    }

    public void setEnt_nick(String ent_nick) {
        this.ent_nick = ent_nick;
    }
}


