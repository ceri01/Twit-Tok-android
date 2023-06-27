package com.example.twit_tok.domain.requests;

public class BasicDataRequest {
    private String sid;
    private String uid;

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public String getUid() {
        return uid;
    }
}
