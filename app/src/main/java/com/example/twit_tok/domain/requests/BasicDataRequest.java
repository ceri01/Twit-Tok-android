package com.example.twit_tok.domain.requests;

public class BasicDataRequest {
    private String sid;
    private String uid = null;
    private String tid = null;

    public BasicDataRequest(String sid) {
        this.sid = sid;
    }

    public BasicDataRequest(String sid, String uid) {
        this.sid = sid;
        this.uid = uid;
    }

    public BasicDataRequest(String sid, String uid, String tid) {
        this.sid = sid;
        this.uid = uid;
        this.tid = tid;
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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
