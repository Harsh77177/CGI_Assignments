package model;

public class Course {
    private int courseId;
    private String title;
    private int credits;

    public Course(int courseId, String title, int credits) {
        this.courseId = courseId;
        this.title = title;
        this.credits = credits;
    }

    public Course(String title, int credits) {
        this.title = title;
        this.credits = credits;
    }

    public int getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }

    @Override
    public String toString() {
        return courseId + " - " + title + " (" + credits + " credits)";
    }
}
