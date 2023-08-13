package com.example.twit_tok.domain.requests;

public class BasicDataRequest {
    private String sid;
    private String uid;

    public BasicDataRequest(String sid, String uid) {
        this.sid = sid;
        this.uid = uid;
    }

    public BasicDataRequest(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
