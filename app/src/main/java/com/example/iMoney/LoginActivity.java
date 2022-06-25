package com.example.iMoney;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private EditText phone;     //输入手机号
    private EditText password;      //密码
    private Button btn1;     //登录按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        //获取变量值
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        btn1 = findViewById(R.id.btn_login);
        //btn1.setOnClickListener(v -> finish());
        btn1.setOnClickListener(v -> {
            //获得用户输入的信息
            String phoneNum = phone.getText().toString();
            String passwordStr = password.getText().toString();
            if (!TextUtils.isEmpty(phoneNum)) {
                if (!TextUtils.isEmpty(passwordStr)) {
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
