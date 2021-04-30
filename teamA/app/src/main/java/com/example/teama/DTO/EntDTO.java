package com.example.teama.DTO;

public class EntDTO {

    private String ent_id;
    private String ent_pw;
    private String ent_name;
    private String ent_nick;
    private String ent_tel;
    private String ent_proof;

    public EntDTO(String ent_id, String ent_pw, String ent_name, String ent_nick, String ent_tel, String ent_proof) {
        this.ent_id = ent_id;
        this.ent_pw = ent_pw;
        this.ent_name = ent_name;
        this.ent_nick = ent_nick;
        this.ent_tel = ent_tel;
        this.ent_proof = ent_proof;
    }

    public String getEnt_id() {
        return ent_id;
    }

    public void setEnt_id(String ent_id) {
        this.ent_id = ent_id;
    }

    public String getEnt_pw() {
        return ent_pw;
    }

    public void setEnt_pw(String ent_pw) {
        this.ent_pw = ent_pw;
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

    public String getEnt_tel() {
        return ent_tel;
    }

    public void setEnt_tel(String ent_tel) {
        this.ent_tel = ent_tel;
    }

    public String getEnt_proof() {
        return ent_proof;
    }

    public void setEnt_proof(String ent_proof) {
        this.ent_proof = ent_proof;
    }
}
