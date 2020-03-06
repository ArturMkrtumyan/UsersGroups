package com.example.usergroup.loginAndregistration;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter {
    LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    loginView.showToastLoggedIn();
                    loginView.openHomePage();

                } else {
                    loginView.showToastException(task.getException());
                }
            }
        });
    }
}
