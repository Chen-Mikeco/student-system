import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    // (1) 功能：添加学生信息到数据库
    public static void addStudent(Student student) {
        String sql = "INSERT INTO students (name, gender, class_name, math_score, java_score) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setString(3, student.getClassName());
            pstmt.setDouble(4, student.getMathScore());
            pstmt.setDouble(5, student.getJavaScore());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ 成功添加学生：" + student.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n🔍 查询结果 (ID=" + id + "):");
                System.out.println("姓名: " + rs.getString("name") +
                        ", 班级: " + rs.getString("class_name") +
                        ", Java成绩: " + rs.getDouble("java_score"));
            } else {
                System.out.println("❌ 未找到ID为 " + id + " 的学生");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // (3) 功能：显示所有学生信息
    public static void showAllStudents() {
        String sql = "SELECT * FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n📋 所有学生列表：");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | 姓名: " + rs.getString("name") +
                        " | 高数: " + rs.getDouble("math_score") +
                        " | Java: " + rs.getDouble("java_score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // (4) 功能：计算学生各科目的平均分数
    public static void calculateAverageScores() {
        String sql = "SELECT AVG(math_score) as avg_math, AVG(java_score) as avg_java FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("\n📊 平均分统计：");
                System.out.println("高数平均分: " + rs.getDouble("avg_math"));
                System.out.println("Java平均分: " + rs.getDouble("avg_java"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- 测试主程序 ---
    public static void main(String[] args) {
        // 1. 先添加几个测试数据 (为了演示效果)
        System.out.println("=== 开始执行任务 (1) 添加数据 ===");
        addStudent(new Student(0, "张三", "男", "计算机1班", 85.5, 90.0));
        addStudent(new Student(0, "李四", "女", "计算机1班", 92.0, 88.5));
        addStudent(new Student(0, "王五", "男", "计算机2班", 60.0, 75.0));

        // 2. 显示所有学生
        System.out.println("\n=== 开始执行任务 (3) 显示所有 ===");
        showAllStudents();

        // 3. 查询 ID 为 1 的学生
        System.out.println("\n=== 开始执行任务 (2) ID查询 ===");
        getStudentById(1);

        // 4. 计算平均分
        System.out.println("\n=== 开始执行任务 (4) 计算平均分 ===");
        calculateAverageScores();
    }
}