package com.example.twit_tok.presentation.Profile;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.model.Users;
import com.example.twit_tok.presentation.ProfileEventListener;

import java.util.Objects;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

public class FollowedAdapter extends RecyclerView.Adapter<FollowedViewHolder> {
    private final LayoutInflater inflater;
    private final Users users;
    private final ProfileEventListener listener;

    @AssistedInject
    public FollowedAdapter(Users users, @Assisted Context context, @Assisted ProfileEventListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.users = users;
    }

    @NonNull
    @Override
    public FollowedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_user, parent, false);
        return new FollowedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowedViewHolder holder, int position) {
        User userToShow = users.getByPosition(position);
        holder.updateContent(userToShow, listener);
    }

    @Override
    public int getItemCount() {
        return users.getlength();
    }
}
