package com.example.twit_tok.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converters {
    @TypeConverter
    public static byte[] fromBitmapToByte(Bitmap image) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        return bytes.toByteArray();
    }

    @TypeConverter
    public static Bitmap fromByteToBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public static String fromByteToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] fromBase64ToByte(String str) {
        Pattern pattern = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return Base64.getDecoder().decode(str);
        }
        return new byte[0]; // TODO: vedi cosa mettere
    }

    public static String fromBitmapToBase64(Bitmap image) {
        return Base64.getEncoder().encodeToString(fromBitmapToByte(image));
    }
}