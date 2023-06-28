package com.example.twit_tok.data.db;

import androidx.room.Query;

import com.example.twit_tok.domain.model.Profile;

public interface SidDao {
    @Query("SELECT * FROM SidEntity")
    Profile getSid();

    @Query("INSERT INTO SidEntity VALUES(:sid, :uid)")
    void insertSid(String sid, int uid);
}
