package com.example.usergroup.firebase;


import androidx.annotation.NonNull;

import com.example.usergroup.room.GroupEntity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class FirebaseClient {
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    public void addDataToDatabase(GroupEntity groupEntity) {
        database.collection("group").add(groupEntity).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


    public void readDataToDatabase() {
        database.collection("group").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<GroupEntity> list = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    GroupEntity groupEntity = documentSnapshot.toObject(GroupEntity.class);
                    list.add(groupEntity);
                }
            }
        });

    }

}
