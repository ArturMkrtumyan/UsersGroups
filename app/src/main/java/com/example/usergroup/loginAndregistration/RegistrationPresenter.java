package com.example.usergroup.loginAndregistration;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationPresenter {
    RegistrationView registrationView;

    public RegistrationPresenter(RegistrationView registrationView) {
        this.registrationView = registrationView;
    }

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public void createUserWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            registrationView.showToastRegistered();
                        } else {
                            registrationView.showToastException(task.getException());
                        }
                    }
                });
    }
}
