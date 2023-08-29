package com.example.twit_tok.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.twit_tok.common.Converters;
import com.example.twit_tok.data.api.TwokApiInstance;
import com.example.twit_tok.domain.entity.PictureEntity;
import com.example.twit_tok.domain.entity.ProfileEntity;
import com.example.twit_tok.domain.entity.SidEntity;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.requests.ProfileRequest;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;


@Database(version = 1, entities = {PictureEntity.class, ProfileEntity.class, SidEntity.class})
@TypeConverters(Converters.class)
public abstract class TwokDatabase extends RoomDatabase {

    private static volatile TwokDatabase instance;

    public static synchronized TwokDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static TwokDatabase create(final Context context) {
        return Room.databaseBuilder(context, TwokDatabase.class, "Twit-tok.db")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Call<Sid> init = TwokApiInstance.getTwokApi().getSid();
                        init.enqueue(new retrofit2.Callback<>() {
                            @Override
                            public void onResponse(@NonNull Call<Sid> call, @NonNull Response<Sid> sidResponse) {
                                Call<ProfileRequest> profile = TwokApiInstance.getTwokApi().getProfile(sidResponse.body());
                                profile.enqueue(new retrofit2.Callback<>() {
                                    @Override
                                    public void onResponse(@NonNull Call<ProfileRequest> call, @NonNull Response<ProfileRequest> response) {
                                        if (!Objects.isNull(response.body())) {
                                            ProfileRequest profileResponse = response.body();
                                            String sid = Objects.requireNonNull(sidResponse.body()).sid();

                                            String profileTrigger = "CREATE TRIGGER IF NOT EXISTS unique_profile " +
                                                    "BEFORE INSERT ON ProfileEntity FOR EACH ROW " +
                                                    "BEGIN " +
                                                    "SELECT RAISE(ABORT, \"User already exists\") " +
                                                    "FROM ProfileEntity " +
                                                    "WHERE uid != " + Objects.requireNonNull(profileResponse).uid() +
                                                    "; END;";

                                            String sidTrigger = "CREATE TRIGGER IF NOT EXISTS unique_sid " +
                                                    "BEFORE INSERT ON SidEntity FOR EACH ROW " +
                                                    "BEGIN " +
                                                    "SELECT RAISE(ABORT, \"User already exists\") " +
                                                    "FROM SidEntity " +
                                                    "WHERE sid != '" + sid +
                                                    "'; END;";
                                            String insertSid = "INSERT INTO SidEntity(sid, uid) VALUES('" + sid + "', " + profileResponse.uid() + " );"; // TODO sql injection
                                            String insertProfile = "INSERT INTO ProfileEntity(uid, name, picture, pversion) VALUES(" + profileResponse.uid() + ", '" + profileResponse.name() + "', '" + profileResponse.picture() + "', " + profileResponse.pversion() + ");";
                                            db.execSQL(profileTrigger);
                                            db.execSQL(sidTrigger);
                                            db.execSQL(insertSid);
                                            db.execSQL(insertProfile);
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<ProfileRequest> call, @NonNull Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                            }

                            @Override
                            public void onFailure(@NonNull Call<Sid> call, @NonNull Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }
                })
                .build();
    }

    public abstract PicturesDao getPicturesDao();

    public abstract ProfileDao getProfileDao();

    public abstract SidDao getSidDao();
}
