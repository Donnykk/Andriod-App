package com.example.iMoney;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    public static String phoneNum;     //输入手机号
    public EditText phone;
    public EditText password;      //密码
    public static String username = "NULL";
    public static String pic_num = "NULL";
    public static String style = "NULL";
    private static final String ipAddress = "39.106.139.86";
    //private static final String ipAddress = "10.130.22.101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        //获取变量值
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        //登录按钮
        Button btn1 = findViewById(R.id.btn_login);
        Button btn2 = findViewById(R.id.btn_register);
        btn1.setOnClickListener(new myClick());
        btn2.setOnClickListener(new myClick());
    }

    class myClick implements View.OnClickListener {
        @SuppressLint("NonConstantResourceId")
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (v.getId() == R.id.btn_login) {
                try {
                    //管理员账号直接登录
                    if (phone.getText().toString().equals("123456")
                            && password.getText().toString().equals("123456")) {
                        finish();
                    } else {
                        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                                .detectDiskReads().detectDiskWrites()
                                .detectNetwork().penaltyLog().build());
                        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                                .detectLeakedSqlLiteObjects().penaltyLog()
                                .penaltyDeath().build());
                        //超时检测
                        long timeMillis = System.currentTimeMillis();
                        Socket s1 = new Socket(ipAddress, 8088);
                        OutputStream os = s1.getOutputStream();
                        DataOutputStream dos = new DataOutputStream(os);
                        phoneNum = phone.getText().toString();
                        try {
                            if (System.currentTimeMillis() - timeMillis > 5000) {
                                Toast.makeText(getApplicationContext(), "服务器炸啦", Toast.LENGTH_SHORT).show();
                            }
                            dos.writeUTF(phoneNum + " " + password.getText().toString() + " " + "Login"); // 向服务器传送登录账号和密码
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //等一秒钟
                        new Handler().postDelayed(() -> {
                        }, 1000);
                        InputStream is = s1.getInputStream();
                        DataInputStream dis = new DataInputStream(is);
                        String getStr = dis.readUTF(); //YES或者NO
                        if (getStr.startsWith("YES")) {
                            String[] cmds = getStr.split("\\s+");
                            username = cmds[1];
                            pic_num = cmds[2];
                            style = cmds[3];
                            if (Objects.equals(username, "null")) {
                                username = "用户" + phoneNum;
                            }
                            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else if (getStr.equals("NO")) {
                            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                        }
                        dis.close();
                        dos.close();
                        s1.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (v.getId() == R.id.btn_register) {
                try {
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                            .detectDiskReads().detectDiskWrites()
                            .detectNetwork().penaltyLog().build());
                    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                            .detectLeakedSqlLiteObjects().penaltyLog()
                            .penaltyDeath().build());
                    Socket s1 = new Socket(ipAddress, 8088);
                    OutputStream os = s1.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(os);
                    dos.writeUTF("# # Register");//向服务器传送# # Register字符
                    dos.close();
                    s1.close();
                    //如果没有异常则跳转到注册界面
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "注册失败 IOException" + e, Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(LoginActivity.this, Register.class));
            }
        }
    }
}
