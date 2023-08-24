package com.example.twit_tok.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

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
        return Base64.getDecoder().decode(str.replace("\n", ""));
    }

    public static String fromBitmapToBase64(Bitmap image) {
        return Base64.getEncoder().encodeToString(fromBitmapToByte(image));
    }

    public static Bitmap fromBase64ToBitmap(String picture) {
        byte[] byteImage = Converters.fromBase64ToByte(picture);
        return BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
    }

    public static Bitmap fromDrawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable bitmapDrawable) {
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        return null;
    }
}
