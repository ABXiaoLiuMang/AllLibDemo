package com.dale.italk.db;

import androidx.room.Dao;
import androidx.room.Query;

import com.dale.room.BaseDao;

import java.util.List;

@Dao
public interface UserDao extends BaseDao<User> {

    @Query("SELECT * FROM user")
    List<User> getItems();

    @Query("SELECT * FROM user WHERE userId IN (:userIds)")
    List<User> getItems(int[] userIds);

//    @Query("SELECT name FROM user")
//    LiveData<List<User>> loadUsersFromRegionsSync();
//
//    @Query("SELECT * FROM user WHERE id BETWEEN :minAge AND :maxAge")
//    User[] loadAllUsersBetweenAges(int minAge, int maxAge);
//
//    @Query("SELECT * FROM user WHERE name LIKE :search")
//    List<User> findUserWithName(String search);
//
//    @Query("SELECT * FROM user WHERE age > :minAge LIMIT 5")
//    Cursor loadRawUsersOlderThan(int minAge);

//    @Query("SELECT * FROM user WHERE userId = :userId")
//    DataSource.Factory<Integer, User> sourceFactory(int userId);






//    @Query("SELECT * FROM message")
//    List<Message> getMessage();
//
//    @Query("SELECT * FROM message WHERE sessionId = :sessionId")
//    List<Message> getMessageBySessionId(String sessionId);
//
//    @Query("SELECT * FROM message WHERE sessionId = :sessionId")
//    DataSource.Factory<Integer, Message> sourceFactory(int sessionId);
//
//    @Query("DELETE FROM message WHERE sessionId = :sessionId")
//    void deleteBySessionId(String sessionId);
//
//    @Query("UPDATE message SET msgData = :msgData WHERE sessionId = :sessionId")
//    void updateBySessionId(String msgData,String sessionId);
}