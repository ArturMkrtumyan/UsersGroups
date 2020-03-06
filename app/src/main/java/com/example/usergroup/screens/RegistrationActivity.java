package com.example.usergroup.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usergroup.R;
import com.example.usergroup.loginAndregistration.RegistrationPresenter;
import com.example.usergroup.loginAndregistration.RegistrationView;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity implements RegistrationView {

    EditText registrationEmail, registrationPassword;
    Button buttonRegistration;
    TextView haveAnAccount;
    RegistrationPresenter registrationPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registrationPresenter = new RegistrationPresenter(this);
        registrationEmail = findViewById(R.id.registrationEmail);
        registrationPassword = findViewById(R.id.registrationPassword);
        buttonRegistration = findViewById(R.id.buttonRegistration);
        haveAnAccount = findViewById(R.id.textViewHaveАnАccount);

        haveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = registrationEmail.getText().toString().trim();
                String password = registrationPassword.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Empty", Toast.LENGTH_SHORT).show();

                } else {
                    registrationPresenter.createUserWithEmailAndPassword(email, password);
                }
            }
        });
    }

    @Override
    public void showToastRegistered() {
        Toast.makeText(RegistrationActivity.this, "Registered", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToastException(Exception e) {
        Toast.makeText(RegistrationActivity.this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show();
    }
}
