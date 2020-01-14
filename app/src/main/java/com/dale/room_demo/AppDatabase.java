package com.dale.room_demo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dale.room.RoomSdk;
import com.dale.room_demo.entity.Phone;
import com.dale.room_demo.entity.PhoneDao;
import com.dale.room_demo.entity.User;
import com.dale.room_demo.entity.UserDao;

@Database(entities = {User.class, Phone.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract PhoneDao phoneDao();

    public static AppDatabase get() {
        return (AppDatabase) RoomSdk.ins().with().database();
    }
}