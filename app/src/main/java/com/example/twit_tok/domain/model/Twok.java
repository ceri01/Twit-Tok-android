package com.example.twit_tok.domain.model;

import java.util.Objects;

public class Twok {
    private String text;
    private String uid;
    private String bgcol;
    private String fontcol;
    private int fontsize;
    private int fonttype;
    private int halign;
    private int valign;
    private double lat;
    private double lon;

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getUid() {
        return uid;
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

    @Override
    public String toString() {
        return "Twok{" +
                "text='" + text + '\'' +
                ", uid='" + uid + '\'' +
                ", bgcol='" + bgcol + '\'' +
                ", fontcol='" + fontcol + '\'' +
                ", fontsize=" + fontsize +
                ", fonttype=" + fonttype +
                ", halign=" + halign +
                ", valign=" + valign +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
