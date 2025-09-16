package dao;

import model.Student;
import model.Course;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class RegistrationDAO {

    public void registerStudent(int studentId, int courseId) {
        String sql = "INSERT INTO registrations(student_id, course_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public Map<Student, List<Course>> getAllRegistrations() {
        Map<Student, List<Course>> map = new HashMap<>();
        String sql = "SELECT s.student_id, s.name, s.year, c.course_id, c.title, c.credits " +
                     "FROM students s " +
                     "JOIN registrations r ON s.student_id = r.student_id " +
                     "JOIN courses c ON r.course_id = c.course_id";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(rs.getInt("student_id"), rs.getString("name"), rs.getInt("year"));
                Course c = new Course(rs.getInt("course_id"), rs.getString("title"), rs.getInt("credits"));
                map.computeIfAbsent(s, k -> new ArrayList<>()).add(c);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return map;
    }
}
