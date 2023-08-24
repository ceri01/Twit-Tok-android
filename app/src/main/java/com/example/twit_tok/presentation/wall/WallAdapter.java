package com.example.twit_tok.presentation.wall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.domain.model.RawTwok;
import com.example.twit_tok.domain.model.TwokToShow;
import com.example.twit_tok.domain.model.TwokToShowBuffer;
import com.example.twit_tok.presentation.WallEventListener;

public class WallAdapter extends RecyclerView.Adapter<WallViewHolder> {
    private final LayoutInflater inflater;
    private final WallEventListener listener;
    private TwokToShowBuffer buffer;

    public WallAdapter(Context context, TwokToShowBuffer buffer, WallEventListener listener) {
        inflater = LayoutInflater.from(context);
        this.buffer = buffer;
        this.listener = listener;
    }

    public void resetTwokBuffer(TwokToShowBuffer buffer) {
        this.buffer = buffer;
    }

    @NonNull
    @Override
    public WallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.twok_layout, parent, false);
        return new WallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallViewHolder holder, int position) {
        TwokToShow recivedTwokToShow = buffer.getByPosition(position);
        holder.updateContent(recivedTwokToShow, listener);
    }

    @Override
    public int getItemCount() {
        return buffer.getlength();
    }
}

