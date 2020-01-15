package com.dale.italk.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dale.room.RoomSdk;

@Database(entities = {User.class, Phone.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract PhoneDao phoneDao();

    public static AppDatabase get() {
        return (AppDatabase) RoomSdk.ins().with().database();
    }
}