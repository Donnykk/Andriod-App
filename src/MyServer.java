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

    public void run(String[] args) {
        int port = 8088;
        String endpoint = "localhost:" + port;
        try {
            this.server = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("服务开启失败 => " + endpoint);
            e.printStackTrace();
            return;
        }
        System.out.println("服务开启成功 => " + endpoint);
        while (true) {
            doLoop();
        }
    }

    private void register() {
        Socket client = null;
        DataInputStream dis = null;
        try {
            client = server.accept();
            dis = new DataInputStream(client.getInputStream());
            String cmd = dis.readUTF();
            // 针对有人注册没注册完就退出了程序然后另外有人开始登陆或者重新注册
            System.out.println("运行命令 => " + cmd);
            String[] cmds = cmd.split("\\s+");
            if (cmds.length < 3) {
                System.err.println("命令长度异常 => " + cmd);
                return;
            }
            switch (cmds[2]) {
                case "Login":
                    login(client, cmds);
                    break;
                case "Register":
                    break;
                case "Registered":
                    insertMasterDB(cmds);
                    break;
                case "ChangeName":
                    changeName(cmds);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(dis, client);
        }
    }

    private static void close(DataInputStream dis, Socket socket) {
        try {
            if (dis != null) {
                dis.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doLoop() {
        System.out.println("服务器起始点"); //调试信息
        Socket client = null;
        DataInputStream dis = null;
        try {
            System.out.println("接到客户端socket请求"); // 调试信息
            client = server.accept();
            dis = new DataInputStream(client.getInputStream());
            String cmd = dis.readUTF();
            System.out.println("运行命令 => " + cmd);
            String[] cmds = cmd.split("\\s+"); // 用空格把账号和密码分开存储
            if (cmds.length < 3) {
                System.err.println("命令长度异常 => " + cmd);
                return;
            }
            switch (cmds[2]) {
                case "Register":
                    System.out.println("等待下一步指令");
                    register();
                    break;
                // 如果发来登录账号+密码+login做如下操作
                case "Login":
                    System.out.println("进入登陆判断");
                    login(client, cmds);
                    break;
                case "Registered":
                    insertMasterDB(cmds);
                    break;
                case "ChangeName":
                    changeName(cmds);
                    break;
                case "ChangePic":
                    changePic(cmds);
                    break;
                case "ChangeStyle":
                    changeStyle(cmds);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(dis, client);
        }
    }

    public static void main(String[] args) {
        MyServer server = new MyServer();
        server.run(args);
    }

    private static void insertMasterDB(String[] commands) {
        ServerDatabase masterDB = new ServerDatabase();
        masterDB.connectSQL();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义要输出日期字符串的格式
        Date startTime = new Date();
        String startTimeSting = sdf.format(startTime);
        String s = "select * from userdata";// 调试信息
        String insert = "insert into userdata(username,password,time,name,picture,style) " + "values('" + commands[0] + "','" + MD5(commands[1]) + "','" + startTimeSting + "', NULL, NULL, NULL)";
        if (masterDB.insertSQL(insert)) {
            System.out.println("insert successfully");
            ResultSet resultSet = masterDB.selectSQL(s);// 调试信息
            masterDB.layoutStyle2(resultSet);// 调试信息
        }
        masterDB.deconnectSQL();// 关闭连接
    }

    private static void changeName(String[] commands) {
        ServerDatabase masterDB = new ServerDatabase();
        masterDB.connectSQL();
        String s = "select * from userdata";// 调试信息
        String update = "update userdata set name = '" + commands[1] + "' where username = '" + commands[0] + "'";
        System.out.println(update);
        if (masterDB.updateSQL(update)) {
            System.out.println("change successfully");
            ResultSet resultSet = masterDB.selectSQL(s);// 调试信息
            masterDB.layoutStyle2(resultSet);// 调试信息
        }
        masterDB.deconnectSQL();// 关闭连接
    }

    private static void changePic(String[] commands) {
        ServerDatabase masterDB = new ServerDatabase();
        masterDB.connectSQL();
        String s = "select * from userdata";// 调试信息
        String update = "update userdata set pic = '" + commands[1] + "' where username = '" + commands[0] + "'";
        if (masterDB.updateSQL(update)) {
            System.out.println("change successfully");
            ResultSet resultSet = masterDB.selectSQL(s);// 调试信息
            masterDB.layoutStyle2(resultSet);// 调试信息
        }
        masterDB.deconnectSQL();// 关闭连接
    }

    private static void changeStyle(String[] commands) {
        ServerDatabase masterDB = new ServerDatabase();
        masterDB.connectSQL();
        String s = "select * from userdata";// 调试信息
        String update = "update userdata set style = '" + commands[1] + "' where username = '" + commands[0] + "'";
        if (masterDB.updateSQL(update)) {
            System.out.println("change successfully");
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
                String name = resultSet.getString(4);
                String pic_num = String.valueOf(resultSet.getInt(5));
                String style = resultSet.getString(6);
                System.out.println("YES " + name + " " + pic_num + " " + style);
                dos.writeUTF("YES " + name + " " + pic_num + " " + style);
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
