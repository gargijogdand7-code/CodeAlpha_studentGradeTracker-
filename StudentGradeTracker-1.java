import java.util.ArrayList;
import java.util.Scanner;

/**
 * CodeAlpha Java Internship - Task 1
 * Student Grade Tracker
 *
 * Features:
 *  - Add students
 *  - Add grades for a student
 *  - View individual student report (average, highest, lowest)
 *  - View summary report of ALL students
 *  - Uses ArrayList to store dynamic data
 */
public class StudentGradeTracker {

    // ---------- Student class ----------
    static class Student {
        private String name;
        private ArrayList<Double> grades;

        public Student(String name) {
            this.name = name;
            this.grades = new ArrayList<>();
        }

        public void addGrade(double grade) {
            grades.add(grade);
        }

        public String getName() {
            return name;
        }

        public ArrayList<Double> getGrades() {
            return grades;
        }

        public double getAverage() {
            if (grades.isEmpty()) return 0.0;
            double sum = 0;
            for (double g : grades) sum += g;
            return sum / grades.size();
        }

        public double getHighest() {
            if (grades.isEmpty()) return 0.0;
            double max = grades.get(0);
            for (double g : grades) if (g > max) max = g;
            return max;
        }

        public double getLowest() {
            if (grades.isEmpty()) return 0.0;
            double min = grades.get(0);
            for (double g : grades) if (g < min) min = g;
            return min;
        }
    }

    // ---------- Main program ----------
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   STUDENT GRADE TRACKER - CodeAlpha  ");
        System.out.println("=====================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    addGradeToStudent();
                    break;
                case "3":
                    viewStudentReport();
                    break;
                case "4":
                    viewSummaryReport();
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.\n");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n----------- MENU -----------");
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. View Individual Student Report");
        System.out.println("4. View Summary Report (All Students)");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        students.add(new Student(name));
        System.out.println("Student \"" + name + "\" added successfully.");
    }

    private static Student findStudent(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    private static void addGradeToStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found. Please add a student first.");
            return;
        }
        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();
        Student s = findStudent(name);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter grade (0-100): ");
        try {
            double grade = Double.parseDouble(sc.nextLine().trim());
            if (grade < 0 || grade > 100) {
                System.out.println("Grade must be between 0 and 100.");
                return;
            }
            s.addGrade(grade);
            System.out.println("Grade added for " + s.getName());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered.");
        }
    }

    private static void viewStudentReport() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();
        Student s = findStudent(name);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("\n---- Report for " + s.getName() + " ----");
        System.out.println("Grades   : " + s.getGrades());
        System.out.printf("Average  : %.2f%n", s.getAverage());
        System.out.printf("Highest  : %.2f%n", s.getHighest());
        System.out.printf("Lowest   : %.2f%n", s.getLowest());
    }

    private static void viewSummaryReport() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n================ SUMMARY REPORT ================");
        System.out.printf("%-15s %-10s %-10s %-10s%n", "Name", "Average", "Highest", "Lowest");
        System.out.println("-------------------------------------------------");
        for (Student s : students) {
            System.out.printf("%-15s %-10.2f %-10.2f %-10.2f%n",
                    s.getName(), s.getAverage(), s.getHighest(), s.getLowest());
        }
        System.out.println("=================================================");
    }
}
