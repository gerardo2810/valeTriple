package com.example.demo.Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DaoBase {
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        String user = "root";
        String pass= "root";
        String url="jdbc:mysql://localhost:3306/hr?serverTimezone=America/Lima";
        return DriverManager.getConnection(url,user,pass);
    }
}
