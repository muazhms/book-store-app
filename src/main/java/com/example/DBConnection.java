package com.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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
//        try  {
//            Class.forName(driver_name);
//            conn = DriverManager.getConnection(
//                    dbhost, username, password);
//        } catch (SQLException e) {
//            System.out.println("Cannot create database connection");
//            e.printStackTrace();
//        } finally {
//            return conn;
//        }
        try {
            if(conn == null || conn.isClosed()){
                Context context = new InitialContext();
                DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/BookStoreDB");
                conn = dataSource.getConnection();
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
