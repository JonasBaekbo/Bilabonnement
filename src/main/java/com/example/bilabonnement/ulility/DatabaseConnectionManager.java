package com.example.bilabonnement.ulility;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;

    private DatabaseConnectionManager(){}

    public static Connection getConnection(){
        if(conn != null){
            return conn;
        }
        try{
            InputStream propertiesStream = new FileInputStream("src/main/resources/application.properties");
            Properties props = new Properties();
            props.load(propertiesStream);

            url = props.getProperty("spring.datasource.url");
            username = props.getProperty("spring.datasource.username");
            password = props.getProperty("spring.datasource.password");
            conn = DriverManager.getConnection(url, username, password);
        }

        catch(SQLException | IOException e){
            e.printStackTrace();
        }

        return conn;
    }
}
