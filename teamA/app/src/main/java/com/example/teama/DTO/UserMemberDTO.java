package com.example.teama.DTO;

import java.io.Serializable;

public class UserMemberDTO implements Serializable {
    private String users_id;
    private String users_nick;
    private String users_name;
    private String users_pw;
    private String users_tel;
    private String users_type;
    private String users_date;

    public UserMemberDTO(){};

    public String getUsers_date() {
        return users_date;
    }

    public void setUsers_date(String users_date) {
        this.users_date = users_date;
    }

    public String getUsers_id() {
        return users_id;
    }

    public void setUsers_id(String users_id) {
        this.users_id = users_id;
    }

    public String getUsers_nick() {
        return users_nick;
    }

    public void setUsers_nick(String users_nick) {
        this.users_nick = users_nick;
    }

    public String getUsers_name() {
        return users_name;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }

    public String getUsers_pw() {
        return users_pw;
    }

    public void setUsers_pw(String users_pw) {
        this.users_pw = users_pw;
    }

    public String getUsers_tel() {
        return users_tel;
    }

    public void setUsers_tel(String users_tel) {
        this.users_tel = users_tel;
    }

    public String getUsers_type() {
        return users_type;
    }

    public void setUsers_type(String users_type) {
        this.users_type = users_type;
    }
}
