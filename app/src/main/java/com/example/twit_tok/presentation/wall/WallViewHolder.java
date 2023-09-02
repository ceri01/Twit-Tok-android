package com.example.twit_tok.presentation.wall;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.common.Colors;
import com.example.twit_tok.common.Constants;
import com.example.twit_tok.common.Converters;
import com.example.twit_tok.domain.model.TwokToShow;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.presentation.WallEventListener;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class WallViewHolder extends RecyclerView.ViewHolder {
    private final TextView text;
    private final TextView userName;
    private final ConstraintLayout content;
    private final ImageView userPicture;
    private final MaterialButton mapButton;
    private final MaterialButton followUnfollow;

    public WallViewHolder(View view) {
        super(view);
        this.text = view.findViewById(R.id.twok_text);
        this.userName = view.findViewById(R.id.wall_twok_user_name);
        this.userPicture = view.findViewById(R.id.wall_twok_user_profile);
        this.content = view.findViewById(R.id.twok);
        this.mapButton = view.findViewById(R.id.wall_twok_map);
        this.followUnfollow = view.findViewById(R.id.wall_twok_follow);
    }

    public void updateContent(@NonNull TwokToShow recivedTwokToShow, WallEventListener listener) {
        if (!Objects.isNull(recivedTwokToShow.getUserPicture())) {
            userPicture.setImageBitmap(recivedTwokToShow.getUserPicture());
        }
        if (recivedTwokToShow.getIsFollowed()) {
            followUnfollow.setText(R.string.unfollow);
        } else {
            followUnfollow.setText(R.string.follow);
        }
        content.setBackgroundColor(Colors.fromHexStringToInt(recivedTwokToShow.getBgcol()));
        userName.setText(recivedTwokToShow.getUserName());
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUsernamePressed(recivedTwokToShow.getUid());
            }
        });

        text.setTextColor(Colors.fromHexStringToInt(recivedTwokToShow.getFontcol()));
        text.setText(recivedTwokToShow.getText());
        text.setTextSize(Constants.FONTSIZE.getOrDefault(recivedTwokToShow.getFontsize(), 30));
        text.setTypeface(Constants.FONTTYPE.getOrDefault(recivedTwokToShow.getFonttype(), Typeface.DEFAULT));

        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(text.getLayoutParams());

        Objects.requireNonNull(Constants.HALIGN.get(recivedTwokToShow.getHalign())).apply(lp);
        Objects.requireNonNull(Constants.VALIGN.get(recivedTwokToShow.getValign())).apply(lp);
        text.setLayoutParams(lp);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMapButtonPressed(recivedTwokToShow.getLat(), recivedTwokToShow.getLon());
            }
        });

        followUnfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recivedTwokToShow.getIsFollowed()) {
                    followUnfollow.setText(R.string.follow);
                    listener.onUnfollowButtonPressed(recivedTwokToShow.getUid());
                } else {
                    User user;
                    if (!Objects.isNull(recivedTwokToShow.getUserPicture())) {
                        user = new User(
                                recivedTwokToShow.getUid(),
                                recivedTwokToShow.getUserName(),
                                Converters.fromBitmapToBase64(recivedTwokToShow.getUserPicture()),
                                recivedTwokToShow.getPversion(),
                                recivedTwokToShow.getIsFollowed());
                    } else {
                        user = new User(
                                recivedTwokToShow.getUid(),
                                recivedTwokToShow.getUserName(),
                                null,
                                recivedTwokToShow.getPversion(),
                                recivedTwokToShow.getIsFollowed());
                    }
                    listener.onFollowButtonPressed(user);
                    followUnfollow.setText(R.string.unfollow);
                }
                recivedTwokToShow.setFollowed(!recivedTwokToShow.getIsFollowed());
                // probabilemnte qui ci sarà da aspettare prima di fare le righe sopra
            }
        });
    }
}
