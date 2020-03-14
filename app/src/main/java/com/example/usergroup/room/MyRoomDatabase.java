package com.example.usergroup.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {GroupEntity.class, UserEntity.class}, version = 2, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {
    private static MyRoomDatabase instance;

    public abstract GroupDao getGroupDao();

    public abstract UserDao getUserDao();

    public static synchronized MyRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyRoomDatabase.class, "MyDb").fallbackToDestructiveMigration().build();

        }
        return instance;
    }


}
