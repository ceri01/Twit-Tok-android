package com.example.twit_tok.data.api;

import com.example.twit_tok.common.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwokApiInstance {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final TwokApi twokApi = retrofit.create(TwokApi.class);

    public static TwokApi getTwokApi() {
        return twokApi;
    }
}
