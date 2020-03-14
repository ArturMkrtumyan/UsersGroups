package com.example.usergroup.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usergroup.R;

public class AddUserActivity extends AppCompatActivity {
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_SURNAME = "USER_SURNAME";

    private EditText userName;
    private EditText userSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        userName = findViewById(R.id.user_add_name);
        userSurname = findViewById(R.id.user_add_surname);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add UserEntity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_user:

                saveUser();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveUser() {
        String nameuser = userName.getText().toString();
        String surnameuser = userSurname.getText().toString();
        if (nameuser.isEmpty() || surnameuser.isEmpty()) {
            Toast.makeText(AddUserActivity.this, "Please insert  username and surname", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(USER_NAME, nameuser);
        data.putExtra(USER_SURNAME, surnameuser);
        setResult(RESULT_OK, data);
        finish();
    }

}
