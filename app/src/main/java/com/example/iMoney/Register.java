package com.example.iMoney;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

public class Register extends AppCompatActivity {
    private EditText phone;
    private EditText password;
    private EditText password_again;
    private static final String ipAddress = "192.168.1.7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        //获取变量值
        phone = findViewById(R.id.new_phone);
        password = findViewById(R.id.new_password);
        password_again = findViewById(R.id.new_password_again);
        //登录按钮
        Button btn1 = findViewById(R.id.btn_tryRegister);
        btn1.setOnClickListener(new myClick());
    }

    class myClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_tryRegister) {
                if (phone.getText().toString().length() < 11) {
                    Toast.makeText(Register.this, "手机号不存在", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().length() < 6) {
                    Toast.makeText(Register.this, "密码须大于6位", Toast.LENGTH_SHORT).show();
                } else if (!password.getText().toString().equals(password_again.getText().toString())) {
                    Toast.makeText(Register.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Socket s1 = new Socket(ipAddress, 8088);
                        OutputStream os = s1.getOutputStream();
                        DataOutputStream dos = new DataOutputStream(os);
                        //传给服务器账号和密码
                        dos.writeUTF(phone.getText().toString() + " "
                                + password.getText().toString() + " " + "Registered");
                        dos.close();
                        s1.close();
                        Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "注册失败",
                                Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(Register.this, LoginActivity.class));
                    finish();
                }
            }
        }
    }
}
