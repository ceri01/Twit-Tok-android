package com.example.twit_tok.data.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.twit_tok.data.api.TwokApiInstance;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.model.entity.PictureEntity;
import com.example.twit_tok.domain.model.entity.ProfileEntity;
import com.example.twit_tok.domain.model.entity.SidEntity;
import com.example.twit_tok.domain.requests.SidRequest;
import com.example.twit_tok.utils.Converters;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;


@Database(version = 1, entities = {PictureEntity.class, ProfileEntity.class, SidEntity.class})
@TypeConverters(Converters.class)
public abstract class TwokDatabase extends RoomDatabase {


    private static final String DB_NAME = "twit-twok.db";
    private static volatile TwokDatabase instance;

    public static synchronized TwokDatabase getInstance(Context context) {
        Log.d("KEK", context.toString());
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
                        Call<SidRequest> init = TwokApiInstance.getTwokApi().getSid();
                        init.enqueue(new retrofit2.Callback<SidRequest>() {
                            @Override
                            public void onResponse(@NonNull Call<SidRequest> call, @NonNull Response<SidRequest> sidResponse) {
                                Call<User> profile = TwokApiInstance.getTwokApi().getProfile(sidResponse.body());
                                profile.enqueue(new retrofit2.Callback<User>() {
                                    @Override
                                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                                        if (!Objects.isNull(response.body())) {
                                            int uid = response.body().getUid();
                                            String sid = sidResponse.body().getSid();

                                            String profileTrigger = "CREATE TRIGGER IF NOT EXISTS unique_profile " +
                                                    "BEFORE INSERT ON ProfileEntity FOR EACH ROW " +
                                                    "BEGIN " +
                                                    "SELECT RAISE(ABORT, \"User already exists\") " +
                                                    "FROM ProfileEntity " +
                                                    "WHERE uid != " + uid +
                                                    "; END;";

                                            String sidTrigger = "CREATE TRIGGER IF NOT EXISTS unique_sid " +
                                                    "BEFORE INSERT ON SidEntity FOR EACH ROW " +
                                                    "BEGIN " +
                                                    "SELECT RAISE(ABORT, \"User already exists\") " +
                                                    "FROM SidEntity " +
                                                    "WHERE sid != '" + sid +
                                                    "'; END;";
                                            String insertSid = "INSERT INTO SidEntity(sid, uid) VALUES('" + sid + "', " + uid + " );";
                                            db.execSQL(profileTrigger);
                                            db.execSQL(sidTrigger);
                                            db.execSQL(insertSid);
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                                        Log.d("ERROR1", t.toString());
                                        t.printStackTrace();
                                    }
                                });
                            }

                            @Override
                            public void onFailure(@NonNull Call<SidRequest> call, @NonNull Throwable t) {
                                Log.d("ERROR2", t.toString());
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
