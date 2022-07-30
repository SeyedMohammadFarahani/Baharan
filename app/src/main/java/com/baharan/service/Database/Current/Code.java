package com.baharan.service.Database.Current;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "current")
public class Code {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "codeMelli")
    private String codeMelli;

    public Code(String codeMelli) {
        this.codeMelli = codeMelli;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCodeMelli() {
        return codeMelli;
    }

    public void setCodeMelli(String codeMelli) {
        this.codeMelli = codeMelli;
    }
}


