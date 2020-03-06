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

public class AddEditGroupActivity extends AppCompatActivity {
    private EditText groupName;
    public static final String GROUP_NAME = "GROUP_NAME";
    public static final String GROUP_ID = "GROUP_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        groupName = findViewById(R.id.group_name);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(GROUP_ID)) {
            setTitle("Edit GroupEntity");
            groupName.setText(intent.getStringExtra(GROUP_NAME));
        } else {
            setTitle("Add GroupEntity");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_group_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_group:
                saveGroup();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveGroup() {
        String namegroup = groupName.getText().toString();
        if (namegroup.isEmpty()) {
            Toast.makeText(this, "Please insert group name", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(GROUP_NAME, namegroup);
        int id = getIntent().getIntExtra(GROUP_ID, -1);
        if (id != 1) {
            data.putExtra(GROUP_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }
}
