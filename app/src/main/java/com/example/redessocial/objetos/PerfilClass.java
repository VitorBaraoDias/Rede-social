package com.example.redessocial.objetos;

import java.io.Serializable;

public class PerfilClass implements Serializable {

    String user_name = "";
    int id_profile;

    public PerfilClass(String user_name, int id_profile) {
        this.user_name = user_name;
        this.id_profile = id_profile;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getId_profile() {
        return id_profile;
    }

    public void setId_profile(int id_profile) {
        this.id_profile = id_profile;
    }
}
