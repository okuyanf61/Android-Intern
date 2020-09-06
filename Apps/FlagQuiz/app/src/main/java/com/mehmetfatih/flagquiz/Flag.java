package com.mehmetfatih.flagquiz;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "flag_table")
public class Flag {
    @PrimaryKey
    public int flagId;

    @ColumnInfo(name = "flag_name")
    public String flagName;

    @ColumnInfo(name = "flag_picture")
    public String flagPicture;

    public Flag() {
    }

    public Flag(int flagId, String flagName, String flagPicture) {
        this.flagId = flagId;
        this.flagName = flagName;
        this.flagPicture = flagPicture;
    }

}
