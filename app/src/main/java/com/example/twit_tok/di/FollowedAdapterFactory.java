package com.example.twit_tok.di;

import android.content.Context;

import com.example.twit_tok.presentation.Profile.FollowedAdapter;
import com.example.twit_tok.presentation.ProfileEventListener;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface FollowedAdapterFactory {
    FollowedAdapter create(Context context, ProfileEventListener listener);
}
