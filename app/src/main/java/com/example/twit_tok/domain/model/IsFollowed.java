package com.example.twit_tok.domain.model;

/**
 * @noinspection ClassCanBeRecord
 */
public class IsFollowed {
    private final boolean followed;

    public IsFollowed(boolean followed) {
        this.followed = followed;
    }

    public boolean isFollowed() {
        return followed;
    }
}
