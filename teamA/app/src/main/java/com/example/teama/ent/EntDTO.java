package com.example.teama.ent;

public class EntDTO {

    public String ent_id;
    public String ent_password;
    public String ent_tel;
    public String ent_name;
    public String ent_nick;
    public String ent_shop;
    public String ent_Proof;
    public String ent_latitude;
    public String ent_longitude;
    public String ent_category;

    //long은 시스템에서 사용중인 변수라서 사용불가


    public EntDTO(String id, String password, String tel, String name, String nick, String shop, String proof, String latitude, String longitude, String category) {
        this.ent_id = id;
        this.ent_password = password;
        this.ent_tel = tel;
        this.ent_name = name;
        this.ent_nick = nick;
        this.ent_shop = shop;
        this.ent_Proof = proof;
        this.ent_latitude = latitude;
        this.ent_longitude = longitude;
        this.ent_category = category;
    }

    //위치
    public EntDTO(String latitude, String longitude) {
        this.ent_latitude = latitude;
        this.ent_longitude = longitude;
    }

    //getter*setter method

    public String getEnt_id() {
        return ent_id;
    }

    public void setEnt_id(String ent_id) {
        this.ent_id = ent_id;
    }

    public String getEnt_password() {
        return ent_password;
    }

    public void setEnt_password(String ent_password) {
        this.ent_password = ent_password;
    }

    public String getEnt_tel() {
        return ent_tel;
    }

    public void setEnt_tel(String ent_tel) {
        this.ent_tel = ent_tel;
    }

    public String getEnt_name() {
        return ent_name;
    }

    public void setEnt_name(String ent_name) {
        this.ent_name = ent_name;
    }

    public String getEnt_nick() {
        return ent_nick;
    }

    public void setEnt_nick(String ent_nick) {
        this.ent_nick = ent_nick;
    }

    public String getEnt_shop() {
        return ent_shop;
    }

    public void setEnt_shop(String ent_shop) {
        this.ent_shop = ent_shop;
    }

    public String getEnt_Proof() {
        return ent_Proof;
    }

    public void setEnt_Proof(String ent_Proof) {
        this.ent_Proof = ent_Proof;
    }

    public String getEnt_latitude() {
        return ent_latitude;
    }

    public void setEnt_latitude(String ent_latitude) {
        this.ent_latitude = ent_latitude;
    }

    public String getEnt_longitude() {
        return ent_longitude;
    }

    public void setEnt_longitude(String ent_longitude) {
        this.ent_longitude = ent_longitude;
    }

    public String getEnt_category() {
        return ent_category;
    }

    public void setEnt_category(String ent_category) {
        this.ent_category = ent_category;
    }
}
