package com.example.dao;
import com.example.model.Student;
import com.example.util.Databaseutil;

import java.sql.*;
public class Student_dao {
    public void registerStudent(Student student, String courseCode) throws Exception {
        addStudent(student); // Ensure the student exists in the student table
        if(!courseExists(courseCode)){
            throw new SQLException("Course with code " + courseCode + " does not exist");
        }
        String query = "INSERT INTO registrations (student_id, course_code) VALUES (?, ?)";
        try (Connection conn = Databaseutil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, courseCode);
            pstmt.executeUpdate();
        }
    }

    private boolean courseExists(String courseCode) throws Exception {
        String query="SELECT COUNT(*) FROM courses WHERE course_code=?";
        try(Connection con=Databaseutil.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query)
        ){
            pstmt.setString(1, courseCode);
            try(ResultSet rs=pstmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1)>0;
                }
            }
        }
        return false;
    }

    public void addStudent(Student student) throws Exception {
        if(!studentExists(student.getStudentId())){
            String query = "INSERT INTO students (student_id, name) VALUES (?, ?)";
            try (Connection conn = Databaseutil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, student.getStudentId());
                pstmt.setString(2, student.getName());
                pstmt.executeUpdate();
            }
        }
    }

    private boolean studentExists(String studentId) throws Exception {
        String query = "SELECT COUNT(*) FROM students WHERE student_id = ?";
        try (Connection conn = Databaseutil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void updateStudent(Student student) throws Exception {
        String query = "UPDATE students SET name = ? WHERE student_id = ?";
        try (Connection conn = Databaseutil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getStudentId());
            pstmt.executeUpdate();
        }
    }

    public void deleteStudent(String studentId) throws Exception {
        String query = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = Databaseutil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();
        }
    }

    public void dropCourse(String studentId, String courseCode) throws Exception {
        String query = "DELETE FROM registrations WHERE student_id = ? AND course_code = ?";
        try (Connection conn = Databaseutil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, studentId);
            pstmt.setString(2, courseCode);
            pstmt.executeUpdate();
        }
    }
}
