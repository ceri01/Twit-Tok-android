package com.example.twit_tok.presentation.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.App;
import com.example.twit_tok.R;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.model.Users;

import javax.inject.Inject;

public class FollowedAdapter extends RecyclerView.Adapter<FollowedViewHolder> {
    private final LayoutInflater inflater;
    private final Users users;

    @Inject
    public FollowedAdapter(Users users) {
        this.inflater = LayoutInflater.from(App.getInstance().getApplicationContext());
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
        holder.updateContent(userToShow);
    }

    @Override
    public int getItemCount() {
        return users.getlength();
    }
}
