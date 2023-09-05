package com.example.twit_tok.domain.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PictureEntity {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "picture")
    public String picture;

    @ColumnInfo(name = "pversion")
    public int pversion;
}
