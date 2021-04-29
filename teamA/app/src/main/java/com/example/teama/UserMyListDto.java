package com.example.teama;

public class UserMyListDto {

    String ent_name;
    String ent_position;
    String ent_time;
    int ent_photo;
    int ent_trash;

    public UserMyListDto(String ent_name, String ent_position, String ent_time, int ent_photo, int ent_trash) {
        this.ent_name = ent_name;
        this.ent_position = ent_position;
        this.ent_time = ent_time;
        this.ent_photo = ent_photo;
        this.ent_trash = ent_trash;
    }

    public String getEnt_name() {
        return ent_name;
    }

    public void setEnt_name(String ent_name) {
        this.ent_name = ent_name;
    }

    public String getEnt_position() {
        return ent_position;
    }

    public void setEnt_position(String ent_position) {
        this.ent_position = ent_position;
    }

    public String getEnt_time() {
        return ent_time;
    }

    public void setEnt_time(String ent_time) {
        this.ent_time = ent_time;
    }

    public int getEnt_photo() {
        return ent_photo;
    }

    public void setEnt_photo(int ent_photo) {
        this.ent_photo = ent_photo;
    }

    public int getEnt_trash() {
        return ent_trash;
    }

    public void setEnt_trash(int ent_trash) {
        this.ent_trash = ent_trash;
    }
}
