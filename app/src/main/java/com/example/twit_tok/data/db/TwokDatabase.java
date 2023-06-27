package com.example.twit_tok.data.db;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.twit_tok.domain.model.entity.PictureEntity;
import com.example.twit_tok.domain.model.entity.ProfileEntity;

import java.security.PrivateKey;

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
                // DA FIXARE, METTERE UID NELLA WHERE
                db.execSQL("""
                        CREATE TRIGGER IF NOT EXISTS unique_profile BEFORE INSERT ON ProfileEntity FOR EACH ROW 
                        BEGIN 
                            SELECT RAISE(ABORT, "User already exists") 
                            FROM ProfileEntity 
                            WHERE uid <> :uid;
                        END;
                        """);
            }
        }).build();
    }

    abstract PicturesDao getPicturesDao();

    abstract ProfileDao getProfileDao();
}
