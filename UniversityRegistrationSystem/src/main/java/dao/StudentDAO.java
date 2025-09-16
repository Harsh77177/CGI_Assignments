package dao;

import model.Student;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class StudentDAO {

    public void addStudent(Student student) {
        String sql = "INSERT INTO students(name, year) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getYear());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getInt("year")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
