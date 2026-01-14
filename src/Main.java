import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== 🎓 学生管理系统控制台 ==========");
            System.out.println("1. 添加学生信息");
            System.out.println("2. 根据ID查询学生");
            System.out.println("3. 显示所有学生");
            System.out.println("4. 统计科目平均分");
            System.out.println("0. 退出系统");
            System.out.println("=========================================");
            System.out.print("👉 请输入您的选择 (0-4): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("--- 请输入学生信息 ---");
                    System.out.print("姓名: ");
                    String name = scanner.next();
                    System.out.print("性别: ");
                    String gender = scanner.next();
                    System.out.print("班级: ");
                    String className = scanner.next();
                    System.out.print("高数成绩: ");
                    double mathScore = scanner.nextDouble();
                    System.out.print("Java成绩: ");
                    double javaScore = scanner.nextDouble();

                    // 创建对象并调用之前写的 Manager 添加
                    Student newStudent = new Student(0, name, gender, className, mathScore, javaScore);
                    StudentManager.addStudent(newStudent);
                    break;

                case 2:
                    // 查询逻辑
                    System.out.print("请输入要查询的学生ID: ");
                    int id = scanner.nextInt();
                    StudentManager.getStudentById(id);
                    break;

                case 3:
                    // 显示所有
                    StudentManager.showAllStudents();
                    break;

                case 4:
                    // 统计平均分
                    StudentManager.calculateAverageScores();
                    break;

                case 0:
                    // 退出
                    System.out.println("👋 系统已退出，再见！");
                    return; // 结束程序

                default:
                    System.out.println("❌ 输入无效，请重新输入！");
            }
        }
    }
}
// 实训提交测试
