package com.example.usergroup.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.usergroup.room.GroupEntity;

import java.util.List;

@Dao
public interface GroupDao {
    @Insert
    void insert(GroupEntity group);
    @Update
    void update(GroupEntity group);
    @Delete
    void delete(GroupEntity group);
    @Query("SELECT * FROM GROUP_TABLE")
    LiveData<List<GroupEntity>>getallGroups();


}
