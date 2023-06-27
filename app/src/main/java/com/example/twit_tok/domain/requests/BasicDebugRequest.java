package com.example.twit_tok.domain.requests;

public class BasicDebugRequest {
    private String sid;
    private String tid;

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getSid() {
        return sid;
    }

    public String getTid() {
        return tid;
    }
}
