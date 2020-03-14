package com.example.usergroup.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.usergroup.room.GroupEntity;

import java.util.List;
import java.util.Set;

@Dao
public interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<GroupEntity> groupEntityList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GroupEntity group);

    @Update
    void update(GroupEntity group);

    @Delete
    void delete(GroupEntity group);

    @Query("SELECT * FROM GROUP_TABLE")
    LiveData<List<GroupEntity>> getallGroups();

    @Query("DELETE FROM GROUP_TABLE")
    void deleteAll();

}
