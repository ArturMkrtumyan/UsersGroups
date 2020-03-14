package com.example.usergroup.firebase;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.usergroup.room.GroupEntity;
import com.example.usergroup.room.UserEntity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.Nullable;


public class FirebaseClient {
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private ReadGroupFromFirebase readGroupFromFirebase;
    private ReadUserFromFirebase readUserFromFirebase;


    public void addGroupToDatabase(final GroupEntity groupEntity) {
        database.collection("groups").document(String.valueOf(groupEntity.getGroupId())).set(groupEntity).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void deleteGroupFromDatabase(final GroupEntity groupEntity) {
        database.collection("groups").document(String.valueOf(groupEntity.getGroupId())).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


    public void readGroupToDatabase(final ReadGroupFromFirebase readGroupFromFirebase) {
        database.collection("groups").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {
                    ArrayList<GroupEntity> groupEntities = (ArrayList<GroupEntity>) queryDocumentSnapshots.toObjects(GroupEntity.class);
                    readGroupFromFirebase.readGroup(groupEntities);
                    Log.d("MyLog", "Server group size in read metod is " + groupEntities.size());
                }
            }
        });
    }

    public interface ReadGroupFromFirebase {
        void readGroup(List<GroupEntity> groupEntities);
    }

    public interface ReadUserFromFirebase {
        void readUser(List<UserEntity> userEntities);
    }

    public void addUserToDatabase(final UserEntity userEntity) {
        database.collection("user").document(String.valueOf(userEntity.getIdUser())).set(userEntity).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void deleteUserFromDatabase(final UserEntity userEntity) {
        database.collection("user").document(String.valueOf(userEntity.getIdUser())).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void readUserToDatabase(final ReadUserFromFirebase readUserFromFirebase) {
        database.collection("user").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {
                    ArrayList<UserEntity> userEntities = (ArrayList<UserEntity>) queryDocumentSnapshots.toObjects(UserEntity.class);
                    readUserFromFirebase.readUser(userEntities);
                }
            }
        });
    }


}
