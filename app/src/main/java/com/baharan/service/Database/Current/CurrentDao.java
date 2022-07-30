package com.baharan.service.Database.Current;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.baharan.service.Database.Family.Family;
import com.baharan.service.Database.User.User;

import java.util.List;

@Dao
public interface CurrentDao {

    @Query("SELECT * FROM current")
    List<Code> getAllCodes();

    @Query("UPDATE current SET codeMelli =:code")
    void updateCode(String code);

    @Insert
    void insertCode(Code code);

    @Delete
    void deleteCode(Family family);

}