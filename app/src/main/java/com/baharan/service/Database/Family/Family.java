package com.baharan.service.Database.Family;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "family")
public class Family {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int fid;

    @NonNull
    @ColumnInfo(name = "firstName")
    private String firstName;

    @NonNull
    @ColumnInfo(name = "lastName")
    private String lastName;

    @NonNull
    @ColumnInfo(name = "codeMelli")
    private String codeMelli;

    @NonNull
    @ColumnInfo(name = "insuranceCode")
    private String insuranceCode;

    @NonNull
    @ColumnInfo(name = "relative")
    private String relative;

    @NonNull
    @ColumnInfo(name = "relative_with")
    private String relative_with;


    public Family(@NonNull String firstName, @NonNull String lastName, @NonNull String codeMelli, @NonNull String insuranceCode, @NonNull String relative, @NonNull String relative_with) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.codeMelli = codeMelli;
        this.insuranceCode = insuranceCode;
        this.relative = relative;
        this.relative_with = relative_with;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public String getCodeMelli() {
        return codeMelli;
    }

    public void setCodeMelli(String codeMelli) {
        this.codeMelli = codeMelli;
    }

    public String getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(String insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    public String getRelative_with() {
        return relative_with;
    }

    public void setRelative_with(String relative_with) {
        this.relative_with = relative_with;
    }
}
