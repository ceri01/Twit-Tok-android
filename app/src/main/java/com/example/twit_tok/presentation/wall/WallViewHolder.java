package com.example.twit_tok.presentation.wall;

import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.common.Constants;
import com.example.twit_tok.common.TwoksUtils;
import com.example.twit_tok.domain.model.RecivedTwok;
import com.example.twit_tok.common.Colors;
import com.example.twit_tok.presentation.EventListener;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class WallViewHolder extends RecyclerView.ViewHolder {
    private final TextView text;
    private final TextView userName;
    private final ConstraintLayout content;
    private final ImageView userPicture;
    private final MaterialButton mapButton;
    private final MaterialButton follow;

    public WallViewHolder(View view) {
        super(view);
        this.text = view.findViewById(R.id.twok_text);
        this.userName = view.findViewById(R.id.twok_user_name);
        this.userPicture = view.findViewById(R.id.twok_user_profile);
        this.content = view.findViewById(R.id.twok);
        this.mapButton = view.findViewById(R.id.twok_map);
        this.follow = view.findViewById(R.id.twok_follow);
    }

    public void updateContent(@NonNull RecivedTwok recivedTwokToShow, EventListener listener) {
        if (!Objects.isNull(recivedTwokToShow.getUserPicture())) {
            userPicture.setImageBitmap(recivedTwokToShow.getUserPicture());
        }
        content.setBackgroundColor(Colors.fromHexStringToInt(recivedTwokToShow.getBgcol()));
        userName.setText(recivedTwokToShow.getUserName());

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
                Log.d("TEST", "" + recivedTwokToShow);
                listener.onMapButtonPressed(recivedTwokToShow.getLat(), recivedTwokToShow.getLon());
            }
        });
    }
}
