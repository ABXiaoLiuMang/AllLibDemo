- tableName：设置表名字。默认是类的名字。
  indices：设置索引。
  inheritSuperIndices：父类的索引是否会自动被当前类继承。
  primaryKeys：设置主键。
  foreignKeys：设置外键。
  
      @Entity(indices = {@Index(value = {"first_name", "last_name"},
              unique = true)})
      public class User {
          @PrimaryKey
          public int id;
      
          @ColumnInfo(name = "first_name")
          public String firstName;
      
          @ColumnInfo(name = "last_name")
          public String lastName;
      
          @Ignore
          Bitmap picture;
      }
      
      @Entity(foreignKeys = @ForeignKey(entity = User.class,
                                        parentColumns = "id",
                                        childColumns = "user_id"))
      public class Book {
          @PrimaryKey
          public int bookId;
      
          public String title;
      
          @ColumnInfo(name = "user_id")
          public int userId;
      }
  
   上述代码通过foreignKeys之后Book表中的userId来源于User表中的id。
   @ForeignKey属性介绍：
   
   entity：parent实体类(引用外键的表的实体)。
   
   parentColumns：parent外键列(要引用的外键列)。
   
   childColumns：child外键列(要关联的列)。
   

- @ColumnInfo注解来自定义表中列的名字。  

- 带参数查询
   - 一个参数
  @Query("SELECT * FROM user WHERE firstName == :name")
         User[] loadAllUsersByFirstName(String name);
         
   - 多个参数      
  @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
  User[] loadAllUsersBetweenAges(int minAge, int maxAge);

  @Query("SELECT * FROM user WHERE firstName LIKE :search " + "OR lastName LIKE :search")
  List<User> findUserWithName(String search);       
 
 - 一组参数
  @Query("SELECT firstName, lastName FROM user WHERE region IN (:regions)")
     public List<NameTuple> loadUsersFromRegions(List<String> regions);
     
     
https://www.jianshu.com/p/3e358eb9ac43     