package com.example.twit_tok.data.db;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RewriteQueriesToDropUnusedColumns;

import com.example.twit_tok.domain.model.Sid;
import com.google.common.util.concurrent.ListenableFuture;

@Dao
public interface SidDao {
    @Query("SELECT * FROM SidEntity")
    @RewriteQueriesToDropUnusedColumns
    ListenableFuture<Sid> getSid();
}
