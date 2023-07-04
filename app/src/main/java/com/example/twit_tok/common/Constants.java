package com.example.twit_tok.common;

import android.graphics.Typeface;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Constants {
    public static final String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";

    public static final Map<Integer, Integer> TEXTSIZES = ImmutableMap.of(
            1, 20,
            2, 30,
            3, 40
    );

    public static final Map<Integer, Typeface> FONTTYPE = ImmutableMap.of(
            1, Typeface.DEFAULT,
            2, Typeface.MONOSPACE,
            3, Typeface.SERIF
    );

    public static final Map<Integer, Function<RelativeLayout.LayoutParams, RelativeLayout.LayoutParams>> HALIGN = ImmutableMap.of(
            1, (Function<RelativeLayout.LayoutParams, RelativeLayout.LayoutParams>) layoutParams -> {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                return layoutParams;
            },
            2, (Function<RelativeLayout.LayoutParams, RelativeLayout.LayoutParams>) layoutParams -> {
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                return layoutParams;
            },
            3, (Function<RelativeLayout.LayoutParams, RelativeLayout.LayoutParams>) layoutParams -> {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                return layoutParams;
            }
    );

    public static final Map<Integer, Function<RelativeLayout.LayoutParams, RelativeLayout.LayoutParams>> VALIGN = ImmutableMap.of(
            1, (Function<RelativeLayout.LayoutParams, RelativeLayout.LayoutParams>) layoutParams -> {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                return layoutParams;
            },
            2, (Function<RelativeLayout.LayoutParams, RelativeLayout.LayoutParams>) layoutParams -> {
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                return layoutParams;
            },
            3, (Function<RelativeLayout.LayoutParams, RelativeLayout.LayoutParams>) layoutParams -> {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                return layoutParams;
            }
    );
}
