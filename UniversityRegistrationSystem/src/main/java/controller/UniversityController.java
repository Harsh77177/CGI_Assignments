package controller;

import service.UniversityService;
import model.Student;
import model.Course;
import java.util.*;

public class UniversityController {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UniversityService service = new UniversityService();

        while (true) {
            System.out.println("\n--- University Course Registration ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Register Student for Course");
            System.out.println("4. View All Students with Registered Courses");
            System.out.println("5. Search Courses by Min Credits");
            System.out.println("6. Get Students Registered in a Particular Course");
            System.out.println("7. Sort Students by Year & Name");
            System.out.println("8. Calculate Total Credits per Student");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.next();
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    service.addStudent(name, year);
                    break;
                case 2:
                    System.out.print("Enter course title: ");
                    String title = sc.next();
                    System.out.print("Enter credits: ");
                    int credits = sc.nextInt();
                    service.addCourse(title, credits);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Enter course ID: ");
                    int cid = sc.nextInt();
                    service.registerStudent(sid, cid);
                    break;
                case 4:
                    service.viewAllStudentsWithCourses();
                    break;
                case 5:
                    System.out.print("Enter min credits: ");
                    int minCredits = sc.nextInt();
                    service.searchCoursesByMinCredits(minCredits)
                           .forEach(System.out::println);
                    break;
                case 6:
                    System.out.print("Enter course ID: ");
                    int courseId = sc.nextInt();
                    service.getStudentsByCourse(courseId)
                           .forEach(System.out::println);
                    break;
                case 7:
                    service.sortStudentsByYearAndName()
                           .forEach(System.out::println);
                    break;
                case 8:
                    service.calculateTotalCreditsPerStudent()
                           .forEach((s, total) -> System.out.println(s + " -> Total Credits: " + total));
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}
