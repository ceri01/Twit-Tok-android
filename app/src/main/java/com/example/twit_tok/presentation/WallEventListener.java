package com.example.twit_tok.presentation;

import com.example.twit_tok.domain.model.User;

public interface WallEventListener {
    void onMapButtonPressed(Double latitude, Double longitude);

    void onFollowButtonPressed(User user);

    void onUnfollowButtonPressed(int uid);

    void onUsernamePressed(int uid);
}
