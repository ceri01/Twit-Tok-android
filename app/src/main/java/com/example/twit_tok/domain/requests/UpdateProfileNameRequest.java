package com.example.twit_tok.domain.requests;

public class UpdateProfileNameRequest {
    private String name;
    private String sid;

    public void setName(String name) {
        this.name = name;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public String getSid() {
        return sid;
    }
}
