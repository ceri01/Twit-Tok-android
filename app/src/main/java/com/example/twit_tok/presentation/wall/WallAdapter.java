package com.example.twit_tok.presentation.wall;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.domain.model.RecivedTwok;
import com.example.twit_tok.domain.model.RecivedTwoksBuffer;
import com.example.twit_tok.presentation.WallEventListener;

public class WallAdapter extends RecyclerView.Adapter<WallViewHolder> {
    private final LayoutInflater inflater;
    private final RecivedTwoksBuffer recivedTwoks;
    private final WallEventListener listener;

    public WallAdapter(Context context, RecivedTwoksBuffer recivedTwoks, WallEventListener listener) {
        inflater = LayoutInflater.from(context);
        Log.d("SIUM", recivedTwoks.getlength() + "");
        for (RecivedTwok recivedTwok : recivedTwoks) {
            Log.d("SIUM", recivedTwok.getText());
        }
        this.recivedTwoks = recivedTwoks;
        this.listener = listener;
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
        holder.updateContent(recivedTwokToShow, listener);
    }

    @Override
    public int getItemCount() {
        return recivedTwoks.getlength();
    }
}

