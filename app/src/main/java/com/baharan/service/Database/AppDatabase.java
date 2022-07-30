package com.baharan.service.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.baharan.service.Database.Current.Code;
import com.baharan.service.Database.Current.CurrentDao;
import com.baharan.service.Database.Family.Family;
import com.baharan.service.Database.Family.FamilyDao;
import com.baharan.service.Database.Reserve.Reserve;
import com.baharan.service.Database.Reserve.ReserveDao;
import com.baharan.service.Database.User.User;
import com.baharan.service.Database.User.UserDao;


@Database(entities = {Family.class, User.class, Code.class, Reserve.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract FamilyDao familyDao();

    public abstract UserDao userDao();

    public abstract CurrentDao currentDao();

    public abstract ReserveDao reserveDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
