package dao;

import model.Course;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class CourseDAO {

    public void addCourse(Course course) {
        String sql = "INSERT INTO courses(title, credits) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getTitle());
            ps.setInt(2, course.getCredits());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Course(
                        rs.getInt("course_id"),
                        rs.getString("title"),
                        rs.getInt("credits")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
