package com.example.usergroup.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "group_table")
public class GroupEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int groupId;
    @ColumnInfo(name = "group_name")
    private String name;

    @Ignore
    public GroupEntity() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public GroupEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
