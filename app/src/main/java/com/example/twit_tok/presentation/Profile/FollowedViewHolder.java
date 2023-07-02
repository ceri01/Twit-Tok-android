package com.example.twit_tok.presentation.Profile;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.utils.Converters;

import org.jetbrains.annotations.NotNull;


public class FollowedViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private ImageView picture;
    private Button followUnfollow;
    private boolean firstUse = true; //  da togliere

    public FollowedViewHolder(@NotNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.userName);
        this.picture = itemView.findViewById(R.id.userPicture);
        this.followUnfollow = itemView.findViewById(R.id.followUnfollow);
    }

    public void updateContent(User user) {
        if (firstUse) {
            Log.v("VIEWHOLDER", user.name());
            firstUse = false;
        } else {
            Log.v("VIEWHOLDER", "Vecchio " + this.name.getText() + " nuovo " + user.name());
        }
        this.name.setText(user.name());
        this.picture.setImageBitmap(Converters.fromBase64ToBitmap(user.picture()));
    }

}
