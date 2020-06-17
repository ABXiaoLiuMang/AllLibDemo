//package com.dale.room_demo.entity;
//
//import android.graphics.Bitmap;
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import androidx.room.ColumnInfo;
//import androidx.room.Entity;
//import androidx.room.Ignore;
//import androidx.room.PrimaryKey;
//
//import com.google.gson.annotations.SerializedName;
//
///**
// * 用户信息
// */
//@Entity(tableName = "user")
//public class User implements Parcelable {
//
//    // 每个实体必须定义至少1个字段作为主键，即使只有1个字段，仍然需要用 @PrimaryKey 注解字段
//    // 如果您想 Room 自动分配 IDs 给实体，则可以设置 @PrimaryKey 的 autoGenerate 属性。
//    // 如果实体具有复合主键，则可以使用 @Entity 注解的 primaryKeys 属性
//    @PrimaryKey(autoGenerate = true)
//    private int id;
//
//    @ColumnInfo(name = "age")
//    private int age;
//
//    // ID
//    @SerializedName("userId")
//    private String userId;
//
//    // 名字
//    @ColumnInfo(name = "name")
//    private String name;
//
//    // 排除这个字段，不存入数据库
//    @Ignore
//    Bitmap bitmap;
//
//    public User() {
//
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public Bitmap getBitmap() {
//        return bitmap;
//    }
//
//    public void setBitmap(Bitmap bitmap) {
//        this.bitmap = bitmap;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(id);
//        dest.writeInt(age);
//        dest.writeString(userId);
//        dest.writeString(name);
//    }
//
//    protected User(Parcel in) {
//        this.id = in.readInt();
//        this.age = in.readInt();
//        this.userId = in.readString();
//        this.name = in.readString();
//    }
//
//    public static final Creator<User> CREATOR = new Creator<User>() {
//
//        @Override
//        public User createFromParcel(Parcel source) {
//            return new User(source);
//        }
//
//        @Override
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", userId='" + userId + '\'' +
//                ", name='" + name + '\'' +
//                ", bitmap=" + bitmap +
//                '}';
//    }
//}