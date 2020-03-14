package com.example.usergroup;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.usergroup.firebase.FirebaseClient;
import com.example.usergroup.room.GroupDao;
import com.example.usergroup.room.GroupEntity;
import com.example.usergroup.room.MyRoomDatabase;
import com.example.usergroup.room.UserDao;
import com.example.usergroup.room.UserEntity;

import java.util.List;

public class GroupAndUserRepository {
    FirebaseClient firebaseClient = new FirebaseClient();
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
    }

    public void sync() {
        firebaseClient.readGroupToDatabase(new FirebaseClient.ReadGroupFromFirebase() {
            @Override
            public void readGroup(final List<GroupEntity> groupEntities) {
                Log.d("MyLog","Server group size is "+groupEntities.size());

                if (groupEntities != null && groupEntities.size() != 0) {
                    new DeleteAllGroupAsyncTask(groupDao).execute();
                    new InsertListGroupAsyncTask(groupDao).execute(groupEntities);
                }
                allGroups.observeForever(new Observer<List<GroupEntity>>() {
                    @Override
                    public void onChanged(List<GroupEntity> groupEntityList) {
                        if (groupEntities != null) {
                            if (groupEntityList.size() != 0) {
                                for (GroupEntity localgroup : groupEntityList) {
                                    if (!groupEntities.contains(localgroup)) {
                                        firebaseClient.addGroupToDatabase(localgroup);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        });
        firebaseClient.readUserToDatabase(new FirebaseClient.ReadUserFromFirebase() {
            @Override
            public void readUser(final List<UserEntity> userEntities) {
                if (userEntities != null) {
                    new DeleteAllUserAsyncTask(userDao).execute();
                    new InsertListUserAsyncTask(userDao).execute(userEntities);
                }
                allUsers.observeForever(new Observer<List<UserEntity>>() {
                    @Override
                    public void onChanged(List<UserEntity> userEntitiesList) {
                        Log.d("MyLog","Local user size is "+userEntitiesList.size());
                        if (userEntities != null) {
                            for (UserEntity localUser : userEntitiesList) {
                                if (!userEntities.contains(localUser)) {
                                    firebaseClient.addUserToDatabase(localUser);
                                }
                            }
                        }
                    }
                });
            }

        });

    }

    public void deleteServerGroup(GroupEntity groupEntity) {
        firebaseClient.deleteGroupFromDatabase(groupEntity);
    }

    public void deleteServerUser(UserEntity userEntity) {
        firebaseClient.deleteUserFromDatabase(userEntity);
    }

    public void insertList(List<GroupEntity> list) {
        new InsertListGroupAsyncTask(groupDao).execute(list);
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


    public static class InsertGroupAsyncTask extends AsyncTask<GroupEntity, Void, Void> {
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

    public static class InsertListGroupAsyncTask extends AsyncTask<List<GroupEntity>, Void, Void> {
        GroupDao groupDao;

        public InsertListGroupAsyncTask(GroupDao groupDao) {
            this.groupDao = groupDao;
        }

        @Override
        protected Void doInBackground(List<GroupEntity>... lists) {
            groupDao.insertList(lists[0]);
            return null;
        }
    }

    public static class DeleteAllGroupAsyncTask extends AsyncTask<Void, Void, Void> {
        GroupDao groupDao;

        public DeleteAllGroupAsyncTask(GroupDao groupDao) {
            this.groupDao = groupDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            groupDao.deleteAll();
            return null;
        }
    }

    public static class InsertListUserAsyncTask extends AsyncTask<List<UserEntity>, Void, Void> {
        UserDao userDao;

        public InsertListUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(List<UserEntity>... lists) {
            userDao.insertList(lists[0]);
            return null;
        }
    }

    public static class DeleteAllUserAsyncTask extends AsyncTask<Void, Void, Void> {
        UserDao userDao;

        public DeleteAllUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAll();
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
