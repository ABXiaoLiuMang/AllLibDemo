package com.dale.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.Collection;

@Dao
public interface BaseDao<T> {
    //    onConflict = OnConflictStrategy.REPLACE指定主键冲突时候的解决方式。直接替换。
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    long insert(T item);//插入单条数据

    //    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    long insert(Collection<T> items);//插入list数据

    @Delete
    void delete(T item);//删除item

    @Delete
    void delete(Collection<T> items);//删除item

    @Update
    void update(T item);//更新item

    @Update
    void update(Collection<T> items);
}
