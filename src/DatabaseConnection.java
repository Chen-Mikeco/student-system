import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8";
    private static final String USER = "root"; //
    private static final String PASSWORD = "123456"; //

    public static Connection getConnection() {
        Connection conn = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ 数据库连接成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ 找不到数据库驱动，请检查是否添加了 mysql-connector-j jar包");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ 数据库连接失败，请检查账号密码或URL");
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        getConnection();
    }
}