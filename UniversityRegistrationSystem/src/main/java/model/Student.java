package model;

public class Student {
    private int studentId;
    private String name;
    private int year;

    public Student(int studentId, String name, int year) {
        this.studentId = studentId;
        this.name = name;
        this.year = year;
    }

    public Student(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return studentId + " - " + name + " (Year " + year + ")";
    }
}
