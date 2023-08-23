package com.example.twit_tok.common;

import com.example.twit_tok.domain.model.RecivedTwok;
import com.google.android.gms.common.util.Strings;

import java.util.Objects;

public class TwoksUtils {
    public static boolean isValidParameter(Integer value) {
        return (!Objects.isNull(value) && value >= 0 && value < 3);
    }

    public static boolean isValidCoord(Double lat, Double lon) {
        return (!Objects.isNull(lat) && !Objects.isNull(lon) && (lat >= -90 && lat <= 90) && (lon >= -180 && lon <= 180));
    }

    public static boolean isValidTwok(RecivedTwok twok) {
        return  !Objects.isNull(twok) &&
                !twok.getText().isBlank() &&
                isValidParameter(twok.getFontsize()) &&
                isValidParameter(twok.getHalign()) &&
                isValidParameter(twok.getValign()) &&
                isValidParameter(twok.getFonttype()) &&
                Colors.isValid(twok.getFontcol()) &&
                Colors.isValid(twok.getBgcol()) &&
               ((Objects.isNull(twok.getLat()) && Objects.isNull(twok.getLon())) || isValidCoord(twok.getLat(), twok.getLon()));
    }
}
