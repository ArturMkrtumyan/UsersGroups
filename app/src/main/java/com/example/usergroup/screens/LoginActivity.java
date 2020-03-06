package com.example.usergroup.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usergroup.loginAndregistration.LoginPresenter;
import com.example.usergroup.loginAndregistration.LoginView;
import com.example.usergroup.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements LoginView {
    EditText loginEmail, loginPassword;
    TextView textViewNoAccount;
    Button buttonLogin;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        textViewNoAccount = findViewById(R.id.textViewNoAccount);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter Your Details", Toast.LENGTH_SHORT).show();
                } else {
                    loginPresenter.signInWithEmailAndPassword(email, password);
                }
            }
        });
    }

    @Override
    public void openHomePage() {
        Intent intent = new Intent(LoginActivity.this, GroupActivity.class);
        startActivity(intent);
    }

    @Override
    public void showToastLoggedIn() {
        Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToastException(Exception e) {
        Toast.makeText(this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show();
    }
}
