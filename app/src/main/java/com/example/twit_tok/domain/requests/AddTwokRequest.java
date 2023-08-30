package com.example.twit_tok.domain.requests;

import com.example.twit_tok.domain.model.NewTwok;

public class AddTwokRequest {
    private String sid;
    private String text;
    private String bgcol;
    private String fontcol;
    private int fontsize;
    private int fonttype;
    private int halign;
    private int valign;
    private Double lat;
    private Double lon;

    public AddTwokRequest(String sid, NewTwok twok) {
        this.sid = sid;
        this.text = twok.getText();
        this.bgcol = twok.getBgcol();
        this.fontcol = twok.getFontcol();
        this.fontsize = twok.getFontsize();
        this.fonttype = twok.getFonttype();
        this.halign = twok.getHalign();
        this.valign = twok.getValign();
        this.lat = twok.getLat();
        this.lon = twok.getLon();
    }

    public String getSid() {
        return sid;
    }

    public String getText() {
        return text;
    }

    public String getBgcol() {
        return bgcol;
    }

    public String getFontcol() {
        return fontcol;
    }

    public int getFontsize() {
        return fontsize;
    }

    public int getFonttype() {
        return fonttype;
    }

    public int getHalign() {
        return halign;
    }

    public int getValign() {
        return valign;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }
}
