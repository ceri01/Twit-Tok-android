package com.example.twit_tok.utils;

public class TwoksUtils {
    public static boolean isValidParameter(int value) {
        return (value > 0 && value < 4);
    }

    public static boolean isValidCoord(double lat, double lon) {
        return ((lat >= -90 && lat <= 90) && (lon >= -180 && lon <= 180));
    }
}
