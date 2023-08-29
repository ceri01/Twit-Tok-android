package com.example.twit_tok.common;

import android.graphics.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Colors {
    public static boolean isValid(String color) {
        Pattern pattern = Pattern.compile("^[0-9A-Fa-f]{6}$");
        Matcher matcher = pattern.matcher(color);
        return matcher.matches();
    }

    public static String fromIntToHexString(int color) {
        String s = String.format("%06X", (0xFFFFFF & color));
        System.out.println(s);
        return s;
    }

    public static int fromHexStringToInt(String color) {
        return Color.parseColor("#" + color);
    }
}
