package com.example.twit_tok.domain.requests;

public class AddTwokRequest {
    private String sid;
    private String text;
    private String bgcol;
    private String fontcol;
    private int fontsize;
    private int fonttype;
    private int halign;
    private int valign;
    private double lat;
    private double lon;

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBgcol(String bgcol) {
        this.bgcol = bgcol;
    }

    public void setFontcol(String fontcol) {
        this.fontcol = fontcol;
    }

    public void setFontsize(int fontsize) {
        this.fontsize = fontsize;
    }

    public void setFonttype(int fonttype) {
        this.fonttype = fonttype;
    }

    public void setHalign(int halign) {
        this.halign = halign;
    }

    public void setValign(int valign) {
        this.valign = valign;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
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

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
