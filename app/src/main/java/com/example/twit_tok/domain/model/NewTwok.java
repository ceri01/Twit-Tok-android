package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;

import com.example.twit_tok.common.Colors;
import com.example.twit_tok.common.Constants;

import javax.inject.Inject;

/**
 * @noinspection unused
 */
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

    @NonNull
    @Override
    public String toString() {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder("Twok{text='")
                .append(text)
                .append("', bgcol='")
                .append(bgcol)
                .append("', fontcol='")
                .append(fontcol)
                .append("', fontsize='")
                .append(fontsize)
                .append("', fonttype='")
                .append(fonttype)
                .append("', halign='")
                .append(halign)
                .append("', valign='")
                .append(valign)
                .append("', lat='")
                .append(lat)
                .append("', lon='")
                .append(lon);
        return sb.toString();
    }
}
