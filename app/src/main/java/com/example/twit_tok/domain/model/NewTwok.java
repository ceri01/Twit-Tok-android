package com.example.twit_tok.domain.model;

import com.example.twit_tok.common.Constants;
import com.example.twit_tok.common.Colors;

import javax.inject.Inject;

public class NewTwok {
    private String text;
    private String bgcol;
    private String fontcol;
    private int fontsize;
    private int fonttype;
    private int halign;
    private int valign;
    private Double lat;
    private Double lon;

    @Inject
    public NewTwok() {
        this.text = "Insert new Twok";
        this.bgcol = Colors.fromIntToHexString(Constants.DEFAULT_NEW_TWOK_BACKGROUND_COLOR);
        this.fontcol = Colors.fromIntToHexString(Constants.DEFAULT_NEW_TWOK_TEXT_COLOR);
        this.fontsize = 1;
        this.fonttype = 1;
        this.valign = 1;
        this.halign = 1;
        this.lat = null;
        this.lon = null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBgColor() {
        return bgcol;
    }

    public void setBgColor(String bgcol) {
        this.bgcol = bgcol;
    }

    public String getFontColor() {
        return fontcol;
    }

    public void setFontColor(String fontcol) {
        this.fontcol = fontcol;
    }

    public int getFontSize() {
        return fontsize;
    }

    public void setFontSize(int fontsize) {
        this.fontsize = fontsize;
    }

    public int getFontType() {
        return fonttype;
    }

    public void setFontType(int fonttype) {
        this.fonttype = fonttype;
    }

    public int getHAlign() {
        return halign;
    }

    public void setHAlign(int halign) {
        this.halign = halign;
    }

    public int getVAlign() {
        return valign;
    }

    public void setVAlign(int valign) {
        this.valign = valign;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public void reset() {
        this.text = "Insert new Twok";
        this.bgcol = Colors.fromIntToHexString(Constants.DEFAULT_NEW_TWOK_BACKGROUND_COLOR);
        this.fontcol = Colors.fromIntToHexString(Constants.DEFAULT_NEW_TWOK_TEXT_COLOR);
        this.fontsize = 1;
        this.fonttype = 1;
        this.valign = 1;
        this.halign = 1;
        this.lat = null;
        this.lon = null;
    }
}
