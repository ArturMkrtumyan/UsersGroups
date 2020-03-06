package com.example.usergroup;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.usergroup.firebase.FirebaseClient;
import com.example.usergroup.room.GroupDao;
import com.example.usergroup.room.GroupEntity;
import com.example.usergroup.room.MyRoomDatabase;
import com.example.usergroup.room.UserDao;
import com.example.usergroup.room.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class GroupAndUserRepository {
    FirebaseClient firebaseClient;
    List<GroupEntity> servergroupEntities;

    private GroupDao groupDao;
    private UserDao userDao;
    private LiveData<List<GroupEntity>> allGroups;
    private LiveData<List<UserEntity>> allUsers;

    public GroupAndUserRepository(Application application) {
        MyRoomDatabase database = MyRoomDatabase.getInstance(application);
        groupDao = database.getGroupDao();
        allGroups = groupDao.getallGroups();
        userDao = database.getUserDao();
        allUsers = userDao.getallUsers();
        servergroupEntities = new ArrayList<>();
        firebaseClient = new FirebaseClient();
    }

    public void synk(CollBack collback) {
        // firebaseClient.addDataToDatabase(new GroupEntity("VAch"));
        Log.d("MyLog", "Read as " + servergroupEntities.isEmpty());

    }

    public void insert(GroupEntity group) {
        new InsertGroupAsyncTask(groupDao).execute(group);
    }

    public void update(GroupEntity group) {
        new UpdateGroupAsyncTask(groupDao).execute(group);

    }

    public void delete(GroupEntity group) {
        new DeleteGroupAsyncTask(groupDao).execute(group);
    }

    public LiveData<List<GroupEntity>> getAllGroups() {
        return allGroups;
    }

    public void insert(UserEntity user) {
        new InsertUserAsyncTask(userDao).execute(user);

    }

    public void update(UserEntity user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void delete(UserEntity user) {
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return allUsers;
    }


    private static class InsertGroupAsyncTask extends AsyncTask<GroupEntity, Void, Void> {
        private GroupDao groupDao;

        public InsertGroupAsyncTask(GroupDao groupDao) {
            this.groupDao = groupDao;
        }

        @Override
        protected Void doInBackground(GroupEntity... groups) {
            groupDao.insert(groups[0]);
            return null;
        }
    }

    private static class UpdateGroupAsyncTask extends AsyncTask<GroupEntity, Void, Void> {
        private GroupDao groupDao;

        public UpdateGroupAsyncTask(GroupDao groupDao) {
            this.groupDao = groupDao;
        }

        @Override
        protected Void doInBackground(GroupEntity... groups) {
            groupDao.update(groups[0]);
            return null;
        }
    }

    private static class DeleteGroupAsyncTask extends AsyncTask<GroupEntity, Void, Void> {
        private GroupDao groupDao;

        public DeleteGroupAsyncTask(GroupDao groupDao) {
            this.groupDao = groupDao;
        }

        @Override
        protected Void doInBackground(GroupEntity... groups) {
            groupDao.delete(groups[0]);
            return null;
        }
    }

    private static class InsertUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao userDao;

        public InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao userDao;

        public UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao userDao;

        public DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... users) {
            userDao.delete(users[0]);
            return null;
        }
    }
}
