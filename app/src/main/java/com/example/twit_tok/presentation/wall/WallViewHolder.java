package com.example.twit_tok.presentation.wall;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.common.Constants;
import com.example.twit_tok.domain.model.Twok;
import com.example.twit_tok.utils.Colors;

import java.util.Objects;

public class WallViewHolder extends RecyclerView.ViewHolder {
    private final TextView text;
    private final TextView userName;

    private final ConstraintLayout content;
    private final ImageView userPicture;

    public WallViewHolder(View view) {
        super(view);
        this.text = view.findViewById(R.id.twok_text);
        this.userName = view.findViewById(R.id.twok_user_name);
        this.userPicture = view.findViewById(R.id.twok_user_profile);
        this.content = view.findViewById(R.id.twok);
        System.out.println(this.content);
    }

    public void updateContent(Twok twokToShow) {
        if (!Objects.isNull(twokToShow.getUserPicture())) {
            userPicture.setImageBitmap(twokToShow.getUserPicture());
        }
        content.setBackgroundColor(Color.parseColor(twokToShow.getBgcol()));
        userName.setText(twokToShow.getUserName());

        text.setTextColor(Color.parseColor(twokToShow.getFontcol()));
        text.setText(twokToShow.getText());
        text.setTextSize(Constants.TEXTSIZES.getOrDefault(twokToShow.getFontsize(), 30));
        text.setTypeface(Constants.FONTTYPE.getOrDefault(twokToShow.getFonttype(), Typeface.DEFAULT));

        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(text.getLayoutParams());

        lp.reset();
        Objects.requireNonNull(Constants.HALIGN.get(twokToShow.getHalign())).apply(lp);
        Objects.requireNonNull(Constants.VALIGN.get(twokToShow.getValign())).apply(lp);
        text.setLayoutParams(lp);
    }
}
