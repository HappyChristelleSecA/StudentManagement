package main;

import model.Student;
import util.InputValidator;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add a new student");
            System.out.println("2. Display all students");
            System.out.println("3. Search for a student by ID");
            System.out.println("4. Update a student’s GPA");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchStudent(scanner);
                    break;
                case 4:
                    updateGPA(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student ID (S-XXXX): ");
        String id = scanner.nextLine();
        if (!InputValidator.isValidId(id)) {
            System.out.println("Invalid ID format!");
            return;
        }

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        if (StringUtils.isBlank(name)) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter student age: ");
        int age = scanner.nextInt();

        System.out.print("Enter student GPA: ");
        double gpa = scanner.nextDouble();
        if (!InputValidator.isValidGPA(gpa)) {
            System.out.println("Invalid GPA value!");
            return;
        }

        students.add(new Student(id, name, age, gpa));
        System.out.println("Student added successfully!");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter student ID to search: ");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println("Student found: " + student);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void updateGPA(Scanner scanner) {
        System.out.print("Enter student ID to update GPA: ");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.print("Enter new GPA: ");
                double gpa = scanner.nextDouble();
                if (InputValidator.isValidGPA(gpa)) {
                    student.setGpa(gpa);
                    System.out.println("GPA updated successfully!");
                } else {
                    System.out.println("Invalid GPA value!");
                }
                return;
            }
        }
        System.out.println("Student not found.");
    }
}
