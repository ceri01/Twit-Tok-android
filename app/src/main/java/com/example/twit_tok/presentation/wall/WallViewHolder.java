package com.example.twit_tok.presentation.wall;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.App;
import com.example.twit_tok.R;
import com.example.twit_tok.domain.model.Twok;
import com.example.twit_tok.utils.Colors;

import java.util.jar.Attributes;

public class WallViewHolder extends RecyclerView.ViewHolder {
    private TextView text;
    private final TextView userName;
    private final ImageView userPicture;

    public WallViewHolder(View view) {
        super(view);
        this.text = view.findViewById(R.id.twok_content);
        this.userName = view.findViewById(R.id.twok_user_name);
        this.userPicture = view.findViewById(R.id.twok_user_profile);
    }

    public void updateContent(Twok twokToShow) {

/*
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(text.getWidth(), text.getHeight());

        text.setLayoutParams();


        text.setBackgroundColor(Colors.getColorFromHex(twokToShow.getBgcol()).toArgb());
        text.setTextColor(Colors.getColorFromHex(twokToShow.getFontcol()).toArgb());
*/


        text.setText(twokToShow.getText());
        userPicture.setImageBitmap(twokToShow.getUserPicture());
        userName.setText(twokToShow.getText());
    }
}
