package com.example.teama.DTO;

public class EntMenuDTO {
    private String menu_name, menu_detail;
    private int menu_price;
    private String fdpicture_path;

    public EntMenuDTO(String menu_name, String menu_detail, int menu_price, String fdpicture_path) {
        this.menu_name = menu_name;
        this.menu_detail = menu_detail;
        this.menu_price = menu_price;
        this.fdpicture_path = fdpicture_path;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_detail() { return menu_detail; }

    public void setMenu_detail(String menu_detail) { this.menu_detail = menu_detail; }

    public int getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(int menu_price) {
        this.menu_price = menu_price;
    }

    public String getFdpicture_path() {
        return fdpicture_path;
    }

    public void setFdpicture_path(String fdpicture_path) {
        this.fdpicture_path = fdpicture_path;
    }
}
