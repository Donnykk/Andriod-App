package com.example.iMoney;

import java.util.HashMap;

public class RegisterInfo {
    private static RegisterInfo instance = null;

    public static RegisterInfo getInstance() {
        if (instance == null) {
            instance = new RegisterInfo();
        }
        return instance;
    }

    public void getRegisterData(String phone, String password) {
        //将注册的信息保存在map中（须和服务器端一致）
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("phone", phone);
        dataMap.put("password", password);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(phone);
        stringBuilder.append(password);

    }
}
