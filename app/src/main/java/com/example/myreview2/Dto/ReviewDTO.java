package com.example.myreview2.Dto;


public class ReviewDTO {
    String users_nick;
    String ent_id;
    String review;
    String review_date;
    String rvpicture_path;

    public ReviewDTO(String users_nick, String ent_id, String review, String review_date, String rvpicture_path) {
        this.users_nick = users_nick;
        this.ent_id = ent_id;
        this.review = review;
        this.review_date = review_date;
        this.rvpicture_path = rvpicture_path;
    }

    public String getUsers_nick() {
        return users_nick;
    }

    public void setUsers_nick(String users_nick) {
        this.users_nick = users_nick;
    }

    public String getEnt_id() {
        return ent_id;
    }

    public void setEnt_id(String ent_id) {
        this.ent_id = ent_id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview_date() {
        return review_date;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }

    public String getRvpicture_path() {
        return rvpicture_path;
    }

    public void setRvpicture_path(String rvpicture_path) {
        this.rvpicture_path = rvpicture_path;
    }
}