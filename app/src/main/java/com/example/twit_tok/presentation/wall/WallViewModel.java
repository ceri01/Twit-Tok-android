package com.example.twit_tok.presentation.wall;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.twit_tok.App;
import com.example.twit_tok.common.Constants;
import com.example.twit_tok.common.TwoksUtils;
import com.example.twit_tok.data.api.TwokApiInstance;
import com.example.twit_tok.data.db.TwokDatabase;
import com.example.twit_tok.domain.model.RecivedTwok;
import com.example.twit_tok.domain.model.RecivedTwoksBuffer;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.model.Users;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.SidRequest;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Objects;
import java.util.function.Function;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WallViewModel extends ViewModel {
    private final Users users;
    private final RecivedTwoksBuffer buffer;
    private MutableLiveData<Boolean> ready;

    @Inject
    public WallViewModel(Users users) {
        this.users = users;
        this.buffer = new RecivedTwoksBuffer();
        this.ready = new MutableLiveData<>(false);
    }

    // TODO: gestisci null in entrambi i casi
    public boolean followUser(User user) {
        if (Objects.isNull(user)) return false;
        executeFollowUnfollow(user.uid(), (BasicDataRequest bdr) -> TwokApiInstance.getTwokApi().follow(bdr));
        this.users.insert(user);
        return true;
    }

    public void unfollowUser(int uid) {
        executeFollowUnfollow(uid, (BasicDataRequest bdr) -> TwokApiInstance.getTwokApi().unfollow(bdr));
        this.users.remove(uid);
    }

    private void executeFollowUnfollow(int uid, Function<BasicDataRequest, Call<Void>> operation) {
        ListenableFuture<Sid> listenableFutureSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        listenableFutureSid.addListener(() -> {
            try {
                SidRequest sid = new SidRequest(listenableFutureSid.get().sid());
                BasicDataRequest bdr = new BasicDataRequest(sid.getSid(), String.valueOf(uid));
                operation.apply(bdr).execute();
            } catch (Exception e) {
                // TODO: Gestisci errore in caso faili la richiesta di rete
            }
        }, App.getInstance().getMainExecutor());
    }

    private void retrieveTwoks() {
        Log.d("retrieveNewTwoks", "dentro getNewTwok");
        ListenableFuture<Sid> listenableFutureSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        listenableFutureSid.addListener(() -> {
            try {
                SidRequest sid = new SidRequest(listenableFutureSid.get().sid());
                Call<RecivedTwok> twok = TwokApiInstance.getTwokApi().getRandomTwok(sid);
                twok.enqueue(new Callback<RecivedTwok>() {
                    @Override
                    public void onResponse(@NonNull Call<RecivedTwok> call, @NonNull Response<RecivedTwok> response) {
                        if (!Objects.isNull(response)) {
                            RecivedTwok data = response.body();
                            if (TwoksUtils.isValidTwok(data)) {
                                System.out.println(data);
                                buffer.insert(response.body());
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<RecivedTwok> call, @NonNull Throwable t) {
                        Log.d("retrieveNewTwoks", "Risposta negativa");
                        t.printStackTrace();
                        // TODO: Gestisci errore in caso faili la richiesta di rete
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: Gestisci errore in caso faili la richiesta di rete
            }
        }, App.getInstance().getMainExecutor());
    }

    public void getTwoks() {
        for (int i = 0; i < Constants.DEFAULT_AMOUNT_OF_NEW_TWOKS; i++) {
            Log.d("retrieveNewTwoks", i + "");
            retrieveTwoks();
            if (i == 7) {
                Log.d("retrieveNewTwoks", i + " buffer -> " + getBufferLength());
                this.ready.setValue(true);
            }
        }
    }

    public MutableLiveData<Boolean> isReady() {
        return ready;
    }

    public RecivedTwoksBuffer getBuffer() {
        return buffer;
    }

    public int getBufferLength() {
        return this.buffer.getlength();
    }

    public void resetBuffer() {
        buffer.reset();
        retrieveTwoks();
    }

    // TODO metti anche retrieveNewTwoks con tid
}