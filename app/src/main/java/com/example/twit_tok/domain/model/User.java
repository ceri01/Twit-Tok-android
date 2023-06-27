package com.example.twit_tok.domain.model;

public class User {
    private String uid;
    private String name;
    private String picture;
    private String pvresion;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPvresion(String pvresion) {
        this.pvresion = pvresion;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getPvresion() {
        return pvresion;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", pvresion='" + pvresion + '\'' +
                '}';
    }
}
