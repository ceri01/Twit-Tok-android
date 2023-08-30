package com.example.twit_tok.presentation.Profile;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.common.Converters;
import com.example.twit_tok.common.PictureUtils;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.presentation.ProfileEventListener;

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

    public void updateContent(User user, ProfileEventListener listener) {
        this.name.setText(user.name());
        // TODO fai richiesta foto a database
        if (PictureUtils.isValidPicture(user.picture())) {
            this.picture.setImageBitmap(Converters.fromBase64ToBitmap(user.picture()));
        }
        followUnfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUnfollowButtonPressed(user.uid());
            }
        });
    }
}
