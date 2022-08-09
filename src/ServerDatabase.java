import java.sql.*;

public class ServerDatabase {
    private Connection conn = null;
    private PreparedStatement statement = null;

    // connect to MySQL
    public void connectSQL() {
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useSSL=false"; //port：3306 database:master-database
        String username = "root"; //user
        String password = "root"; //password
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //加载驱动，连接数据库
            conn = DriverManager.getConnection(url, username, password);
        }
        //捕获加载驱动程序异常
        catch (ClassNotFoundException cnfex) {
            System.err.println("装载 JDBC/ODBC 驱动程序失败。");
            cnfex.printStackTrace();
        }
        //捕获连接数据库异常
        catch (SQLException sqlex) {
            System.err.println("无法连接数据库");
            sqlex.printStackTrace();
        }
    }

    // disconnect to MySQL
    public void deconnectSQL() {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            System.out.println("关闭数据库问题 ：");
            e.printStackTrace();
        }
    }

    // execute selection language
    public ResultSet selectSQL(String sql) {
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // execute insertion language
    public boolean insertSQL(String sql) {
        updateSQL(sql);
        return false;
    }

    //execute delete language
    public boolean deleteSQL(String sql) {
        updateSQL(sql);
        return false;
    }

    //execute update language
    public boolean updateSQL(String sql) {
        try {
            statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("插入数据库时出错：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("插入时出错：");
            e.printStackTrace();
        }
        return false;
    }

    // show data in ju_users
    public void layoutStyle2(ResultSet rs) {
        System.out.println("-----------------");
        System.out.println("username" + "\t" + "password");
        System.out.println("-----------------");
        try {
            while (rs.next()) {
                System.out.println(rs.getString("username") + "\t"
                        + rs.getString("password") + "\n");
            }
        } catch (SQLException e) {
            System.out.println("显示时数据库出错。");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("显示出错。");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerDatabase h = new ServerDatabase();
        h.connectSQL();
        String select = "select * from userdata where username="
                + "'w'" + " and password="
                + "'w'" + ";";
        ResultSet resultSet = h.selectSQL(select);
        h.layoutStyle2(resultSet);//调试信息
        String s = "select * from userdata";

        String insert = "insert into userdata(username,password) " +
                "values('aaron','102938475610')";
        String update = "update userdata set password ='123456789' where username= 'aaron'";
        String delete = "delete from userdata where username= 'aaron'";

        if (h.insertSQL(insert)) {
            System.out.println("insert successfully");
            resultSet = h.selectSQL(s);
            h.layoutStyle2(resultSet);
        }
        if (h.updateSQL(update)) {
            System.out.println("update successfully");
            resultSet = h.selectSQL(s);
            h.layoutStyle2(resultSet);
        }
        if (h.deleteSQL(delete)) {
            System.out.println("delete successfully");
            resultSet = h.selectSQL(s);
            h.layoutStyle2(resultSet);
        }
        h.deconnectSQL();
    }
}
