package com.example.twit_tok.domain.requests;

public class UpdateProfileNameRequest {
    private String sid;
    private String name;

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSid() {
        return sid;
    }
}
