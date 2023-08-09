package com.example.twit_tok.common;

import android.Manifest;
import android.content.Context;

import androidx.core.content.ContextCompat;

public class LocationUtils {
    public static boolean hasLocationPermissions(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == 1;
    }

}
