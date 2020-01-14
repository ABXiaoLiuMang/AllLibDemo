package com.dale.room_demo.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.dale.room.BaseDao;

import java.util.List;

@Dao
public interface UserDao extends BaseDao<User> {

    @Query("SELECT * FROM User")
    List<User> getItems();

    @Query("SELECT * FROM user WHERE userId IN (:userIds)")
    List<User> getItems(int[] userIds);

}