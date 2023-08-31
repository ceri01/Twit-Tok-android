package com.example.twit_tok.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import com.example.twit_tok.App;

import java.util.Objects;

public class NetUtils {
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        Network nw = cm.getActiveNetwork();
        if (Objects.isNull(nw)) return false;
        NetworkCapabilities actNw = cm.getNetworkCapabilities(nw);
        return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
    }
}
