package com.example.bilabonnement.repository;

import java.sql.*;
import java.util.ArrayList;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class StatisticsRepository {

    public int getNumberOfCarsLeased() {
        Connection conn = getConnection();
        try {

            String searchForCarsLeased = "SELECT COUNT(*) FROM cars WHERE cars.car_status_id IN (1,2)";
            PreparedStatement pstmt = conn.prepareStatement(searchForCarsLeased);
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


   /*
select
    car_status.car_status,
    count(*)
from
	cars
join
	car_status on cars.car_status_id = car_status.car_status_id
group by
    cars.car_status_id
order by
    cars.car_status_id
    */
}
