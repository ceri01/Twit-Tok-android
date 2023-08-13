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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBgcol() {
        return bgcol;
    }

    public void setBgcol(String bgcol) {
        this.bgcol = bgcol;
    }

    public String getFontcol() {
        return fontcol;
    }

    public void setFontcol(String fontcol) {
        this.fontcol = fontcol;
    }

    public int getFontsize() {
        return fontsize;
    }

    public void setFontsize(int fontsize) {
        this.fontsize = fontsize;
    }

    public int getFonttype() {
        return fonttype;
    }

    public void setFonttype(int fonttype) {
        this.fonttype = fonttype;
    }

    public int getHalign() {
        return halign;
    }

    public void setHalign(int halign) {
        this.halign = halign;
    }

    public int getValign() {
        return valign;
    }

    public void setValign(int valign) {
        this.valign = valign;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
