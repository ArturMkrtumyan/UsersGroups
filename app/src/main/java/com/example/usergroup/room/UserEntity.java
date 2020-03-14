package com.example.usergroup.room;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private int idUser;
    private String nameUser;
    private String surnameUser;
    private int groupid;

    @Ignore
    public UserEntity() {
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getGroupid() {
        return groupid;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getSurnameUser() {
        return surnameUser;
    }

    public UserEntity(String nameUser, String surnameUser, int groupid) {
        this.nameUser = nameUser;
        this.surnameUser = surnameUser;
        this.groupid = groupid;
    }
}
