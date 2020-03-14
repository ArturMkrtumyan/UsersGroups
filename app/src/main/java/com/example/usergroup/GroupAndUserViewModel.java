package com.example.usergroup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.usergroup.room.GroupEntity;
import com.example.usergroup.room.UserEntity;

import java.util.List;

public class GroupAndUserViewModel extends AndroidViewModel {
    private GroupAndUserRepository repository;
    private LiveData<List<GroupEntity>> allGroup;
    private LiveData<List<UserEntity>> allUsers;

    public GroupAndUserViewModel(@NonNull Application application) {
        super(application);
        repository = new GroupAndUserRepository(application);
        allGroup = repository.getAllGroups();
        allUsers = repository.getAllUsers();
    }

    public void deleteServerGroup(GroupEntity groupEntity) {
        repository.deleteServerGroup(groupEntity);
    }

    public void deleteServerUser(UserEntity userEntity) {
        repository.deleteServerUser(userEntity);
    }

    public void insert(GroupEntity group) {
        repository.insert(group);
    }

    public void update(GroupEntity group) {
        repository.update(group);
    }

    public void delete(GroupEntity group) {
        repository.delete(group);
    }

    public LiveData<List<GroupEntity>> getAllGroup() {
        return allGroup;
    }

    public void sync() {
        repository.sync();
    }

    public void insert(UserEntity user) {
        repository.insert(user);
    }

    public void update(UserEntity user) {
        repository.update(user);
    }

    public void delete(UserEntity user) {
        repository.delete(user);
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return allUsers;
    }
}
