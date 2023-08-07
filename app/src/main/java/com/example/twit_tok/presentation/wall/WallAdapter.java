package com.example.twit_tok.presentation.wall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.domain.model.RecivedTwok;
import com.example.twit_tok.domain.model.RecivedTwoks;

public class WallAdapter extends RecyclerView.Adapter<WallViewHolder> {
    private final LayoutInflater inflater;
    private final RecivedTwoks recivedTwoks;

    public WallAdapter(Context context, RecivedTwoks recivedTwoks) {
        inflater = LayoutInflater.from(context);
        this.recivedTwoks = recivedTwoks;
    }

    @NonNull
    @Override
    public WallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.twok_layout, parent, false);
        return new WallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallViewHolder holder, int position) {
        RecivedTwok recivedTwokToShow = recivedTwoks.getByPosition(position);
        holder.updateContent(recivedTwokToShow);
    }

    @Override
    public int getItemCount() {
        return recivedTwoks.getlength();
    }
}

