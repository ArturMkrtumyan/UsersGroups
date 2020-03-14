package com.example.usergroup.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.usergroup.room.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(UserEntity user);

    @Update
    void update(UserEntity user);

    @Delete
    void delete(UserEntity user);

    @Query("SELECT * FROM user_table")
    LiveData<List<UserEntity>> getallUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<UserEntity> userEntityList);

    @Query("DELETE FROM USER_TABLE")
    void deleteAll();
}
