package com.example.usergroup.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {GroupEntity.class, UserEntity.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {
    private static MyRoomDatabase instance;

    public abstract GroupDao getGroupDao();

    public abstract UserDao getUserDao();

    public static synchronized MyRoomDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyRoomDatabase.class, "MyDb").fallbackToDestructiveMigration().addCallback(callback).build();

        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(instance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {
        private GroupDao groupDao;
        private UserDao userDao;

        public PopulateAsyncTask(MyRoomDatabase db) {
            groupDao = db.getGroupDao();
            userDao = db.getUserDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            groupDao.insert(new GroupEntity("GroupEntity 1"));
            groupDao.insert(new GroupEntity("GroupEntity 2"));
            groupDao.insert(new GroupEntity("GroupEntity 3"));
            userDao.insert(new UserEntity("Artur", "Mkrtumyan",1));
            userDao.insert(new UserEntity("Petros", "Karapetyan",1));
            userDao.insert(new UserEntity("Taron", "Aramyan",1));
            return null;
        }
    }
}
