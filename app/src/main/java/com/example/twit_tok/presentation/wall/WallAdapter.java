package com.example.twit_tok.presentation.wall;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.common.Constants;
import com.example.twit_tok.domain.model.Twok;
import com.example.twit_tok.domain.model.Twoks;
import com.example.twit_tok.utils.Colors;

import java.util.Arrays;
import java.util.Objects;

public class WallAdapter extends RecyclerView.Adapter<WallViewHolder> {
    private final LayoutInflater inflater;
    private final Twoks twoks;

    public WallAdapter(Context context, Twoks twoks) {
        inflater = LayoutInflater.from(context);
        this.twoks = twoks;
    }

    @NonNull
    @Override
    public WallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.twok_layout, parent, false);
        return new WallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallViewHolder holder, int position) {
        Twok twokToShow = twoks.getByPosition(position);
        holder.updateContent(twokToShow);
    }

    @Override
    public int getItemCount() {
        return twoks.getlength();
    }
}

