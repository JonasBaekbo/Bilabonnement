//Johanne
package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.StatisticsItem;

import java.sql.*;
import java.util.ArrayList;

import static com.example.bilabonnement.utility.DatabaseConnectionManager.getConnection;

public class StatisticsRepository {

    private StatisticsItem getNumberOfCars() {
        Connection conn = getConnection();
        try {
            //Vi sortere status 5 fra, da det er status for biler der ikke er hos bilabonnement.dk
            String searchForCarsLeased = "SELECT COUNT(*) FROM cars WHERE cars.car_status_id <>5";
            PreparedStatement pstmt = conn.prepareStatement(searchForCarsLeased);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();

            rs.next();
            int numCars = rs.getInt(1);
            StatisticsItem carsInTotal = new StatisticsItem("Samlet Antal Biler", numCars);

            return carsInTotal;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<StatisticsItem> getCarsPerStatus() {
        ArrayList<StatisticsItem> result = new ArrayList<>();

        Connection conn = getConnection();

        String sql = """
                SELECT
                car_status.car_status,
                COUNT(*) AS number_of_cars
                FROM
                cars
                JOIN
                car_status ON cars.car_status_id = car_status.car_status_id
                GROUP BY  cars.car_status_id
                ORDER BY  cars.car_status_id""";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            while (resultSet.next()) {
                StatisticsItem item = new StatisticsItem(resultSet.getString("car_status"), resultSet.getInt("number_of_cars"));
                result.add(item);
            }
            StatisticsItem carsInTotal = getNumberOfCars();
            result.add(carsInTotal);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
