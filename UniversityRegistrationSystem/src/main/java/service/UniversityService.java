package service;

import dao.*;
import model.*;
import java.util.*;
import java.util.stream.*;

public class UniversityService {
    private StudentDAO studentDAO = new StudentDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private RegistrationDAO regDAO = new RegistrationDAO();

    public void addStudent(String name, int year) {
        studentDAO.addStudent(new Student(name, year));
    }

    public void addCourse(String title, int credits) {
        courseDAO.addCourse(new Course(title, credits));
    }

    public void registerStudent(int studentId, int courseId) {
        regDAO.registerStudent(studentId, courseId);
    }

    public void viewAllStudentsWithCourses() {
        Map<Student, List<Course>> data = regDAO.getAllRegistrations();
        data.forEach((s, courses) -> {
            System.out.println(s);
            courses.forEach(c -> System.out.println("   " + c));
        });
    }

    public List<Course> searchCoursesByMinCredits(int minCredits) {
        return courseDAO.getAllCourses().stream()
                .filter(c -> c.getCredits() >= minCredits)
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByCourse(int courseId) {
        return regDAO.getAllRegistrations().entrySet().stream()
                .filter(e -> e.getValue().stream().anyMatch(c -> c.getCourseId() == courseId))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Student> sortStudentsByYearAndName() {
        return studentDAO.getAllStudents().stream()
                .sorted(Comparator.comparing(Student::getYear).thenComparing(Student::getName))
                .collect(Collectors.toList());
    }

    public Map<Student, Integer> calculateTotalCreditsPerStudent() {
        return regDAO.getAllRegistrations().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().mapToInt(Course::getCredits).sum()
                ));
    }
}
