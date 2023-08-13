package com.example.twit_tok.presentation;

import com.example.twit_tok.domain.model.User;

public interface WallEventListener {
    void onMapButtonPressed(Double latitude, Double longitude);

    boolean onFollowButtonPressed(User user);

    void onUnfollowButtonPressed(int uid);
}
