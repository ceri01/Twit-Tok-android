package com.example.twit_tok.domain.entity;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = SidEntity.class,
        parentColumns = "uid",
        childColumns = "uid",
        onDelete = ForeignKey.CASCADE))
public class ProfileEntity {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public Bitmap picture;

    @ColumnInfo(name = "pversion")
    public int pversion;
}

