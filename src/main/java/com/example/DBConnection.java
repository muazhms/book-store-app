package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String dbhost = "jdbc:mysql://us-cdbr-east-02.cleardb.com/heroku_dc0b44c59eae21f";
    private static String username = "bbb7f232662a21";
    private static String password = "f22ddf62";
    private static String driver_name = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    @SuppressWarnings("finally")
    public static Connection createNewDBconnection() {
        try  {
            if(conn == null || conn.isClosed()){
                Class.forName(driver_name);
                conn = DriverManager.getConnection(
                        dbhost, username, password);
            }
        } catch (SQLException e) {
            System.out.println("Cannot create database connection");
            e.printStackTrace();
        } finally {
            return conn;
        }
    }
}
