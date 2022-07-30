package com.baharan.service.Database.Family;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FamilyDao {

    @Query("SELECT * FROM family")
    List<Family> getAllFamilies();

    @Query("SELECT * FROM family where relative_with LIKE :relativeWith")
    List<Family> getselectFamilies(String relativeWith);

    @Query("SELECT * FROM family where lastName LIKE  :lastName ")
    Family findFamilyByName(String lastName);

    @Query("SELECT * FROM family where codeMelli LIKE :code_melli")
    Family findFamilyByCodeMelli(String code_melli);

    @Query("SELECT * FROM family where insuranceCode LIKE :insurance_code")
    Family findFamilyByInsuranceCode(String insurance_code);

    @Query("DELETE FROM family ")
    void deleteAllFamilies();

    @Insert
    void insertFamily(Family family);

    @Delete
    void deleteFamily(Family family);

}