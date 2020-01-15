package com.dale.italk.db;

import androidx.room.Dao;
import androidx.room.Query;

import com.dale.room.BaseDao;

import java.util.List;

@Dao
public interface PhoneDao extends BaseDao<Phone> {

    @Query("SELECT * FROM Phone")
    List<Phone> getItems();


}