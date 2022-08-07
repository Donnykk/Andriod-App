import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8088);
            while (true) {
                System.out.println("服务器起始点"); //调试信息
                Socket s1 = server.accept();
                System.out.println("接到客户端socket请求"); // 调试信息
                InputStream is1 = s1.getInputStream();
                DataInputStream dis1 = new DataInputStream(is1);
                String[] str = dis1.readUTF().split(" "); // 用空格把账号和密码分开存储
                System.out.println(str[0] + " " + str[1] + " " + str[2]);

                switch (str[2]) {
                    case "Register":
                        System.out.println("等待下一步指令");
                        Socket s2 = server.accept();
                        InputStream is2 = s2.getInputStream();
                        DataInputStream dis2 = new DataInputStream(is2);
                        String[] getStrSecond = dis2.readUTF().split(" ");
                        //针对有人注册没注册完就退出了程序然后另外有人开始登陆或者重新注册
                        System.out.println(getStrSecond[0] + " " + getStrSecond[1] + " " + getStrSecond[2]);
                        switch (getStrSecond[2]) {
                            case "Login":
                                login(s2, getStrSecond);
                                break;
                            case "Register":
                                break;
                            case "Registered":
                                insertMasterDB(getStrSecond);
                                break;
                        }
                        is2.close();
                        s2.close();
                        dis2.close();
                        break;
                    // 如果发来登录账号+密码+login做如下操作
                    case "Login":
                        System.out.println("进入登陆判断");
                        login(s1, str);
                        break;
                    case "Registered":
                        insertMasterDB(str);
                        break;
                }
                dis1.close();
                s1.close();
            }
        } catch (EOFException eofException) {
            System.out.println("这是合法的，客户端已经关闭");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertMasterDB(String[] getStr) {
        ServerDatabase masterDB = new ServerDatabase();
        masterDB.connectSQL();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义要输出日期字符串的格式
        Date startTime = new Date();
        String startTimeSting = sdf.format(startTime);
        String s = "select * from userdata";// 调试信息
        String insert = "insert into userdata(username,password,time) " + "values('" + getStr[0] + "','" + MD5(getStr[1]) + "','" + startTimeSting + "')";
        if (masterDB.insertSQL(insert)) {
            System.out.println("insert successfully");
            ResultSet resultSet = masterDB.selectSQL(s);// 调试信息
            masterDB.layoutStyle2(resultSet);// 调试信息
        }
        masterDB.deconnectSQL();// 关闭连接
    }

    /**
     * 该函数为是否允许用户登录函数
     *
     * @param s1  建立连接的socket变量
     * @param str 要查找的username和password都在里面
     */
    public static void login(Socket s1, String[] str) {
        ServerDatabase masterDB = new ServerDatabase();
        masterDB.connectSQL();
        String select = "select * from userdata where username = '" + str[0] + "' and password = '" + MD5(str[1]) + "';";
        System.out.println(select);
        ResultSet resultSet = masterDB.selectSQL(select);
        // ///////////////查找用户名和密码是否一致//////////////
        try {
            // 用户名和密码不一致
            if (!resultSet.next()) {
                // 禁止登录命令
                OutputStream os = s1.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeUTF("NO");
                dos.close();
                System.out.println("用户密码错误");
            }
            // 用户名和密码一致
            else {
                // 允许登录命令
                OutputStream os = s1.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeUTF("YES");
                dos.close();
                System.out.println("用户密码正确");// 调试信息
            }
        } catch (Exception e) {
            System.out.println("显示出错");
            e.printStackTrace();
        }
        masterDB.deconnectSQL();// 关闭连接
    }

    // MD5加密
    private static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            ret.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[aByte & 0x0f]);
        }
        return ret.toString();
    }
}
