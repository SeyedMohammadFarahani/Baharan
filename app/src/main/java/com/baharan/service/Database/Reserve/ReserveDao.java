package com.baharan.service.Database.Reserve;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.baharan.service.Database.Family.Family;
import com.baharan.service.Database.User.User;

import java.util.List;

@Dao
public interface ReserveDao {

    @Query("SELECT * FROM reserve")
    List<Reserve> getAllReserves();

    @Query("SELECT * FROM reserve where owner LIKE :owner")
    List<Reserve> getOwnerReserves(String owner);

    @Query("SELECT * FROM reserve where (hospital LIKE :hospital) and (field LIKE :field) and (owner is null)")
    List<Reserve> getFieldReserves(String hospital, String field);

    @Query("SELECT * FROM reserve where (hospital LIKE :hospital) and (owner is null)")
    List<Reserve> getHospitalReserves(String hospital);


    @Query("SELECT * FROM reserve where (hospital LIKE :hospital) and (field LIKE :field) and (doctorName LIKE :doctor) and (owner is null)")
    List<Reserve> getFieldAndDoctorReserves(String hospital, String field, String doctor);

    @Query("SELECT * FROM reserve where (hospital LIKE :hospital) and (doctorName LIKE :doctorName) and (owner is null)")
    List<Reserve> getDoctorReserves(String hospital, String doctorName);

    @Query("SELECT * FROM reserve where code LIKE :code")
    Reserve findReserveByCode(String code);

    @Query("DELETE FROM reserve ")
    void deleteAllReserves();

    @Query("UPDATE reserve SET owner =:owner WHERE code =:code ")
    void updateOwner(String code, String owner);

    @Query("UPDATE reserve SET ownerCode =:ownerCode WHERE code =:code ")
    void updateOwnerCode(String code, String ownerCode);

    @Query("UPDATE reserve SET ownerName =:ownerName WHERE code =:code ")
    void updateOwnerName(String code, String ownerName);

    @Query("UPDATE reserve SET ownerInsurance =:ownerInsurance WHERE code =:code ")
    void updateName(String code, String ownerInsurance);

    @Insert
    void insertReserve(Reserve reserve);

    @Delete
    void deleteReserve(Reserve reserve);
}