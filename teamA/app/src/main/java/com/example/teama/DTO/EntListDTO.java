package com.example.teama.DTO;

import java.io.Serializable;

public class EntListDTO implements Serializable {
        private String ent_nick;
        private String ent_id;
        private String ent_name;
        private String ent_lat;
        private String ent_long;
        private String ent_open;
        private String ent_close;
        private String ent_location;
        private String ent_proof;
        private String ent_menu;
        private String ent_menu_picture;

        public String getEnt_menu_picture() {
            return ent_menu_picture;
        }
        public void setEnt_menu_picture(String ent_menu_picture) {
            this.ent_menu_picture = ent_menu_picture;
        }

        public String getEnt_menu() {
            return ent_menu;
        }

        public void setEnt_menu(String ent_menu) {
            this.ent_menu = ent_menu;
        }

        public String getEnt_nick() {
            return ent_nick;
        }

        public void setEnt_nick(String ent_nick) {
            this.ent_nick = ent_nick;
        }

        public String getEnt_id() {
            return ent_id;
        }

        public void setEnt_id(String ent_id) {
            this.ent_id = ent_id;
        }


        public String getEnt_proof() {
            return ent_proof;
        }

        public void setEnt_proof(String ent_proof) {
            this.ent_proof = ent_proof;
        }

        public EntListDTO(){}

        public EntListDTO(String ent_name, String ent_open, String ent_close, String ent_location, String ent_proof) {
            this.ent_name = ent_name;
            this.ent_open = ent_open;
            this.ent_close = ent_close;
            this.ent_location = ent_location;
            this.ent_proof = ent_proof;
        }

        public String getEnt_location() {
            return ent_location;
        }

        public void setEnt_location(String ent_location) {
            this.ent_location = ent_location;
        }

        public String getEnt_name() {
            return ent_name;
        }

        public void setEnt_name(String ent_name) {
            this.ent_name = ent_name;
        }

        public String getEnt_lat() {
            return ent_lat;
        }

        public void setEnt_lat(String ent_lat) {
            this.ent_lat = ent_lat;
        }

        public String getEnt_long() {
            return ent_long;
        }

        public void setEnt_long(String ent_long) {
            this.ent_long = ent_long;
        }

        public String getEnt_open() {
            return ent_open;
        }

        public void setEnt_open(String ent_open) {
            this.ent_open = ent_open;
        }

        public String getEnt_close() {
            return ent_close;
        }

        public void setEnt_close(String ent_close) {
            this.ent_close = ent_close;
        }
    }

