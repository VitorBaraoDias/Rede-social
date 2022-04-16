package com.example.redessocial.objetos;

import java.io.Serializable;

public class User implements Serializable {

    String email = "";
    String pass = "";
    String nome = "";

    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }
    public User(String email, String pass,String nome) {
        this.email = email;
        this.pass = pass;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
