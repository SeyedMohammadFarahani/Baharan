package com.baharan.service.Database.Reserve;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "reserve")
public class Reserve {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "owner")
    private String owner;

    @ColumnInfo(name = "ownerCode")
    private String ownerCode;

    @ColumnInfo(name = "ownerInsurance")
    private String ownerInsurance;

    @ColumnInfo(name = "ownerName")
    private String ownerName;

    @ColumnInfo(name = "code")
    private String code;

    @ColumnInfo(name = "hospital")
    private String hospital;

    @ColumnInfo(name = "field")
    private String field;

    @ColumnInfo(name = "doctorName")
    private String doctorName;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;

    public Reserve(String code, String hospital, String field, String doctorName, String date, String time) {
        this.code = code;
        this.hospital = hospital;
        this.field = field;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getOwnerInsurance() {
        return ownerInsurance;
    }

    public void setOwnerInsurance(String ownerInsurance) {
        this.ownerInsurance = ownerInsurance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
