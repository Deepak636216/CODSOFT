package com.example.test;
import com.example.util.Databaseutil;
import java.sql.*;
public class ConnectionTest {
    public static void main(String[] args) {
        try(Connection con=Databaseutil.getConnection()){
            if(con!=null){
                System.out.println("Connected");
            }
            else{
                System.out.println("Not Connected");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
