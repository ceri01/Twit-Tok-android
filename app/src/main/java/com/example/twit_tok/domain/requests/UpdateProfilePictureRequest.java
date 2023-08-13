package com.example.twit_tok.domain.requests;

public class UpdateProfilePictureRequest {
    private String picture;
    private String sid;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
