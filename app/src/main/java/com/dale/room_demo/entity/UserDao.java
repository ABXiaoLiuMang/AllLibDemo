//package com.dale.room_demo.entity;
//
//import androidx.room.Dao;
//import androidx.room.Query;
//
//import com.dale.room.BaseDao;
//
//import java.util.List;
//
//@Dao
//public interface UserDao extends BaseDao<User> {
//
//    @Query("SELECT * FROM user")
//    List<User> getItems();
//
////    @Query("SELECT * FROM user WHERE id IN (:userIds)")
////    List<User> getItems(int[] userIds);
//
////    @Query("SELECT name FROM user")
////    LiveData<List<User>> loadUsersFromRegionsSync();
////
////    @Query("SELECT * FROM user WHERE id BETWEEN :minAge AND :maxAge")
////    User[] loadAllUsersBetweenAges(int minAge, int maxAge);
////
////    @Query("SELECT * FROM user WHERE name LIKE :search")
////    List<User> findUserWithName(String search);
////
////    @Query("SELECT * FROM user WHERE age > :minAge LIMIT 5")
////    Cursor loadRawUsersOlderThan(int minAge);
//}