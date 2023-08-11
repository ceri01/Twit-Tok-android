package com.example.twit_tok.presentation;

public interface EventListener {
    void onMapButtonPressed(Double latitude, Double longitude);
    void onFollowUnfollowButtonPressed(boolean followUnfollow, int uid);
}
