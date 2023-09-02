package com.example.twit_tok.presentation.user_wall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.domain.model.TwokToShow;
import com.example.twit_tok.domain.model.TwokToShowBuffer;
import com.example.twit_tok.presentation.UserWallEventListener;
import com.example.twit_tok.presentation.wall.WallViewHolder;

public class UserWallAdapter extends RecyclerView.Adapter<UserWallViewHolder> {
    private final LayoutInflater inflater;
    private final UserWallEventListener listener;
    private TwokToShowBuffer buffer;

    public UserWallAdapter(Context context, TwokToShowBuffer buffer, UserWallEventListener listener) {
        inflater = LayoutInflater.from(context);
        this.buffer = buffer;
        this.listener = listener;
    }

    public void resetTwokBuffer(TwokToShowBuffer buffer) {
        this.buffer = buffer;
    }

    @NonNull
    @Override
    public UserWallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.user_twok_layout, parent, false);
        return new UserWallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserWallViewHolder holder, int position) {
        holder.setIsRecyclable(false); // used because some icons did not correspond to the correct users
        TwokToShow recivedTwokToShow = buffer.getByPosition(position);
        holder.updateContent(recivedTwokToShow, listener);
    }

    @Override
    public int getItemCount() {
        return buffer.getlength();
    }
}
