package com.example.twit_tok.common;

import java.util.Objects;

public class TwoksUtils {
    public static boolean isValidParameter(Integer value) {
        return (!Objects.isNull(value) && value > 0 && value < 4);
    }

    public static boolean isValidCoord(double lat, double lon) {
        return ((lat >= -90 && lat <= 90) && (lon >= -180 && lon <= 180));
    }
}
