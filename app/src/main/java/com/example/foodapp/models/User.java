package com.example.foodapp.models;

import java.io.Serializable;

public class User implements Serializable {


    private String username, correo, password, id;

    public User(){}

    public User(String username, String correo, String password) {
        this.correo = correo;
        this.password = password;
        this.username = username;
    }

    public User(String id){
        this.id = id;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


}
