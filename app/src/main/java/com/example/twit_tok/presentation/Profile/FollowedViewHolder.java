package com.example.twit_tok.presentation.Profile;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.common.Converters;

import org.jetbrains.annotations.NotNull;


public class FollowedViewHolder extends RecyclerView.ViewHolder {
    private final TextView name;
    private final ImageView picture;
    private final Button followUnfollow;

    public FollowedViewHolder(@NotNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.userName);
        this.picture = itemView.findViewById(R.id.userPicture);
        this.followUnfollow = itemView.findViewById(R.id.followUnfollow);
    }

    public void updateContent(User user) {
        this.name.setText(user.name());
        this.picture.setImageBitmap(Converters.fromBase64ToBitmap(user.picture()));
    }

}
