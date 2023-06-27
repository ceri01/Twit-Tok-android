package com.example.twit_tok.domain.requests;

public class UpdateProfilePictureRequest {
    private String picture;
    private String sid;

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPicture() {
        return picture;
    }

    public String getSid() {
        return sid;
    }
}
