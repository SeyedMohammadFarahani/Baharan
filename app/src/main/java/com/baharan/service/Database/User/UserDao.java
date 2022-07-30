package com.baharan.service.Database.User;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;


import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user where codeMelli LIKE :code")
    User findUserByCodeMelli(String code);

    @Query("SELECT * FROM user where phone LIKE :Phone")
    User findUserByPhone(String Phone);

    @Query("SELECT * FROM user where uid =:Id")
    User findUserById(int Id);

    @Query("DELETE FROM user ")
    void deleteAllUsers();

    @Query("UPDATE user SET firstName =:FirstName WHERE codeMelli =:code ")
    void updateFirstName(String code, String FirstName);

    @Query("UPDATE user SET lastName =:lastName WHERE codeMelli =:code ")
    void updateLastName(String code, String lastName);

    @Query("UPDATE user SET insuranceCode =:insurance WHERE codeMelli =:code ")
    void updateInsuranceCode(String code, String insurance);

    @Query("UPDATE user SET email =:email WHERE codeMelli =:code ")
    void updateEmail(String code, String email);

    @Query("UPDATE user SET address =:address WHERE codeMelli =:code ")
    void updateAddress(String code, String address);

    @Query("UPDATE user SET phone =:Phone WHERE codeMelli =:code")
    void updatePhone(String code, String Phone);

    @Query("UPDATE user SET password =:password WHERE phone =:phone")
    void updatePass(String phone, String password);

    @Query("UPDATE user SET state =:state WHERE codeMelli =:code")
    void updateState(String code, String state);

    @Query("UPDATE user SET city =:city WHERE codeMelli =:code")
    void updateCity(String code, String city);

    @Query("UPDATE user SET image =:profile WHERE codeMelli =:code")
    void updateProfile(String code, byte[] profile);

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);
}