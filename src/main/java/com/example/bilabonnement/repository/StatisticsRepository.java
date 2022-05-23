package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.StatisticsItem;

import java.sql.*;
import java.util.ArrayList;

import static com.example.bilabonnement.utility.DatabaseConnectionManager.getConnection;

public class StatisticsRepository {

  /*  public int getNumberOfCarsLeased() {
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
        }
        return -1;
    }
*/

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
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
