package com.example.twit_tok.data.db;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.twit_tok.data.api.TwokApiInstance;
import com.example.twit_tok.domain.model.Profile;
import com.example.twit_tok.domain.model.entity.PictureEntity;
import com.example.twit_tok.domain.model.entity.ProfileEntity;
import com.example.twit_tok.domain.requests.SidRequest;

import java.io.ObjectInput;
import java.security.PrivateKey;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Database(version = 1, entities = {PictureEntity.class, ProfileEntity.class})
public abstract class TwokDatabase extends RoomDatabase {

    private static final String DB_NAME = "twit-twok.db";
    private static volatile TwokDatabase instance;

    static synchronized TwokDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static TwokDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                TwokDatabase.class,
                DB_NAME).addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Call<SidRequest> init = TwokApiInstance.getTwokApi().getSid();
                init.enqueue(new retrofit2.Callback<SidRequest>() {
                    @Override
                    public void onResponse(@NonNull Call<SidRequest> call, @NonNull Response<SidRequest> sidResponse) {
                        Call<Profile> profile = TwokApiInstance.getTwokApi().getProfile(sidResponse.body());
                        profile.enqueue(new retrofit2.Callback<Profile>() {
                            @Override
                            public void onResponse(@NonNull Call<Profile> call, @NonNull Response<Profile> response) {
                                if (!Objects.isNull(response.body())) {
                                    int uid = response.body().getUid();
                                    String sid = sidResponse.body().getSid();

                                    String profileTrigger = "CREATE TRIGGER IF NOT EXISTS unique_profile " +
                                            "BEFORE INSERT ON ProfileEntity FOR EACH ROW " +
                                            "BEGIN " +
                                            "SELECT RAISE(ABORT, \"User already exists\") " +
                                            "FROM ProfileEntity " +
                                            "WHERE uid <>" + uid +
                                            "END;";

                                    String sidTrigger = "CREATE TRIGGER IF NOT EXISTS unique_sid " +
                                            "BEFORE INSERT ON SidEntity FOR EACH ROW " +
                                            "BEGIN " +
                                            "SELECT RAISE(ABORT, \"User already exists\") " +
                                            "FROM SidEntity " +
                                            "WHERE sid <>" + sid +
                                            "END;";
                                    instance.getSidDao().insertSid(sidResponse.body().getSid(), response.body().getUid());
                                    db.execSQL(profileTrigger);
                                    db.execSQL(sidTrigger);
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {
                                // TODO: Handle error
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NonNull Call<SidRequest> call, @NonNull Throwable t) {
                        // TODO: handle error
                    }
                });

            }
        }).build();
    }

    abstract PicturesDao getPicturesDao();

    abstract ProfileDao getProfileDao();

    abstract SidDao getSidDao();
}
