package com.example.twit_tok.presentation.wall;

import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.common.Constants;
import com.example.twit_tok.domain.model.Twok;
import com.example.twit_tok.utils.Colors;

import java.util.Objects;

public class WallViewHolder extends RecyclerView.ViewHolder {
    private final TextView text;
    private final TextView userName;
    private final ImageView userPicture;

    public WallViewHolder(View view) {
        super(view);
        this.text = view.findViewById(R.id.twok_content);
        this.userName = view.findViewById(R.id.twok_user_name);
        this.userPicture = view.findViewById(R.id.twok_user_profile);
    }

    public void updateContent(Twok twokToShow) {
        userPicture.setImageBitmap(twokToShow.getUserPicture());
        userName.setText(twokToShow.getUserName());

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((RelativeLayout.LayoutParams) text.getLayoutParams());
        Objects.requireNonNull(Constants.HALIGN.get(twokToShow.getHalign())).apply(lp);
        Objects.requireNonNull(Constants.VALIGN.get(twokToShow.getValign())).apply(lp);
        text.setLayoutParams(lp);

        itemView.setBackgroundColor(Colors.getColorFromHex(twokToShow.getBgcol()).toArgb());
        text.setTextColor(Colors.getColorFromHex(twokToShow.getFontcol()).toArgb());
        text.setText(twokToShow.getText());
        text.setTextSize(Constants.TEXTSIZES.getOrDefault(twokToShow.getFontsize(), 30));
        text.setTypeface(Constants.FONTTYPE.getOrDefault(twokToShow.getFonttype(), Typeface.DEFAULT));
    }
}
