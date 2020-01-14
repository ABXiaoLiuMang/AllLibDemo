package com.dale.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BaseDao<T> {
    @Insert
    void insertItem(T item);//插入单条数据

    @Insert
    void insertItems(List<T> items);//插入list数据

    @Delete
    void deleteItem(T item);//删除item

    @Update
    void updateItem(T item);//更新item
}
