package com.example.dao;

import com.example.model.Course;
import com.example.util.Databaseutil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Course_dao {

    public List<Course> getAllCourses() throws Exception {
        List<Course> courses = new ArrayList<>();
        String query="SELECT * FROM courses";
        try(Connection con=Databaseutil.getConnection();
            Statement stm=con.createStatement();
            ResultSet rs=stm.executeQuery(query)
            ){
            while(rs.next()){
                Course course=new Course();
                course.setCourseCode(rs.getString("course_code"));
                course.setTitle(rs.getString("title"));
                course.setDescription(rs.getString("description"));
                course.setCapacity(rs.getInt("capacity"));
                course.setSchedule(rs.getString("schedule"));
                courses.add(course);
            }
        }
        return courses;
    }
    public void addCourse(Course course) throws Exception {
        String query="INSERT INTO courses (course_code, title, description, capacity, schedule) VALUES(?,?,?,?,?)";
        try(Connection con=Databaseutil.getConnection();
        PreparedStatement pst=con.prepareStatement(query)){
            pst.setString(1,course.getTitle());
            pst.setString(2,course.getDescription());
            pst.setInt(3,course.getCapacity());
            pst.setString(4,course.getSchedule());
            pst.setString(5,course.getCourseCode());
            pst.executeUpdate();
        }
    }

    public void updateCourse(Course course) throws Exception {
        String query="UPDATE courses SET title=?,description=?,capacity=?,schedule=? WHERE course_code=?";
        try(Connection con=Databaseutil.getConnection();
            PreparedStatement pst=con.prepareStatement(query)){
            pst.setString(1,course.getTitle());
            pst.setString(2,course.getDescription());
            pst.setInt(3,course.getCapacity());
            pst.setString(4,course.getSchedule());
            pst.setString(5,course.getCourseCode());
            pst.executeUpdate();
        }
    }
    public void deleteCourse(Course course) throws Exception {
        String query="DELETE FROM courses WHERE course_code=?";
        try(Connection con=Databaseutil.getConnection();
        PreparedStatement pst=con.prepareStatement(query)){
            pst.setString(1, course.getCourseCode());
            pst.executeUpdate();
        }
    }

}
