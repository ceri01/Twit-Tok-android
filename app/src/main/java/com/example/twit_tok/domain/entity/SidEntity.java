package com.example.twit_tok.domain.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = @Index(value = {"uid"}, unique = true))
public class SidEntity {
    @PrimaryKey
    public String sid;

    @ColumnInfo(name = "uid")
    public int uid;
}
