package com.example.util;
import java.sql.*;
public class Databaseutil {
    public static Connection getConnection() throws Exception{
        String url="jdbc:postgresql://localhost:5432/course_registration";
        String username="postgres";
        String password="Deepak5.";

        return DriverManager.getConnection(url,username,password);
    }
}
