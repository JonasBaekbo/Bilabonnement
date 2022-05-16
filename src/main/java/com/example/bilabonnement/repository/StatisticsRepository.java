package com.example.bilabonnement.repository;

import java.sql.*;
import java.util.ArrayList;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class StatisticsRepository {

    public int getNumberOfCarsLeased() {
        Connection conn = getConnection();
        try {

            String searchForCarsLeased = "SELECT COUNT(*) FROM cars WHERE `car_status` = ?";
            PreparedStatement pstmt = conn.prepareStatement(searchForCarsLeased);
            pstmt.setInt(1, 1);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();

            rs.next();
            int numCarsLeased = rs.getInt(1);

            return numCarsLeased;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in getNumberOfCarsLeased-method");
        }
        return -1;
    }

    public int getNumbersOfCarsInTotal() {
        Connection conn = getConnection();
        try {

            String numberOfCars = "SELECT COUNT(*) FROM cars";
            PreparedStatement pstmt = conn.prepareStatement(numberOfCars);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();

            rs.next();
            int totalNumberOfCars = rs.getInt(1);

            return totalNumberOfCars;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in getNumbersOfCarsInTotal-method");
        }
        return -1;
    }
}
