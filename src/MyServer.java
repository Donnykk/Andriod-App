//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyServer {
    private ServerSocket server;

    public MyServer() {
    }

    public void run(String[] args) {
        int port = 8088;
        String endpoint = "localhost:" + port;
        try {
            this.server = new ServerSocket(port);
        } catch (IOException var5) {
            System.err.println("服务开启失败 => " + endpoint);
            var5.printStackTrace();
            return;
        }
        System.out.println("服务开启成功 => " + endpoint);
        while (true) {
            this.doLoop();
        }
    }

    private void doLoop() {
        System.out.println("服务器起始点");

        try {
            System.out.println("接到客户端socket请求");
            Socket client = this.server.accept();
            DataInputStream dis = new DataInputStream(client.getInputStream());
            String cmd1 = dis.readUTF();
            System.out.println("运行命令 => " + cmd1);
            String[] cmd1s = cmd1.split("\\s+");
            switch (cmd1s[2]) {
                case "Register":
                    System.out.println("等待下一步指令");
                    Socket s2 = this.server.accept();
                    DataInputStream dis2 = new DataInputStream(s2.getInputStream());
                    String cmd2 = dis2.readUTF();
                    System.out.println(cmd2);
                    String[] cmd2s = cmd2.split("\\s+");
                    switch (cmd2s[2]) {
                        case "Login":
                            login(s2, cmd2s);
                        case "Register":
                        default:
                            break;
                        case "Registered":
                            insertMasterDB(cmd2s);
                    }

                    s2.close();
                    dis2.close();
                    break;
                case "Login":
                    System.out.println("进入登陆判断");
                    login(client, cmd1s);
                    break;
                case "Registered":
                    insertMasterDB(cmd1s);
            }
            dis.close();
            client.close();
        } catch (IOException var13) {
            var13.printStackTrace();
        }

    }

    public static void main(String[] args) {
        MyServer server = new MyServer();
        server.run(args);
    }

    private static void insertMasterDB(String[] commands) {
        ServerDatabase masterDB = new ServerDatabase();
        masterDB.connectSQL();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = new Date();
        String startTimeSting = sdf.format(startTime);
        String s = "select * from userdata";
        String insert = "insert into userdata(username,password,time) values('" + commands[0] + "','" + MD5(commands[1]) + "','" + startTimeSting + "')";
        if (masterDB.insertSQL(insert)) {
            System.out.println("insert successfully");
            ResultSet resultSet = masterDB.selectSQL(s);
            masterDB.layoutStyle2(resultSet);
        }
        masterDB.deconnectSQL();
    }

    public static void login(Socket s1, String[] str) {
        ServerDatabase masterDB = new ServerDatabase();
        masterDB.connectSQL();
        String select = "select * from userdata where username = '" + str[0] + "' and password = '" + MD5(str[1]) + "';";
        System.out.println(select);
        ResultSet resultSet = masterDB.selectSQL(select);
        try {
            OutputStream os;
            DataOutputStream dos;
            if (!resultSet.next()) {
                os = s1.getOutputStream();
                dos = new DataOutputStream(os);
                dos.writeUTF("NO");
                dos.close();
                System.out.println("用户密码错误");
            } else {
                os = s1.getOutputStream();
                dos = new DataOutputStream(os);
                dos.writeUTF("YES");
                dos.close();
                System.out.println("用户密码正确");
            }
        } catch (Exception var7) {
            System.out.println("显示出错");
            var7.printStackTrace();
        }
        masterDB.deconnectSQL();
    }

    private static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
            return toHex(bytes);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    private static String toHex(byte[] bytes) {
        char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            ret.append(HEX_DIGITS[aByte >> 4 & 15]);
            ret.append(HEX_DIGITS[aByte & 15]);
        }
        return ret.toString();
    }
}
