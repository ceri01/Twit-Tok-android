package com.example.twit_tok.domain.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class SidEntity {
    @PrimaryKey
    public String sid;

    @ColumnInfo(name = "uid")
    public String uid;
}
