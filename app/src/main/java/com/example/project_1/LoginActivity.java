package com.example.project_1;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        this.getSupportActionBar().hide();
        Button btn1=findViewById(R.id.btn_login);
        btn1.setOnClickListener(v -> finish());
    }
}
