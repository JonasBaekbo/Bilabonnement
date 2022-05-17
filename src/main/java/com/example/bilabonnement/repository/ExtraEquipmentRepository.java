package com.example.bilabonnement.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class ExtraEquipmentRepository {

    public List<String> getListOfExtraEquipment(int carID) {

        ArrayList<String> extraEquipment = new ArrayList<>();
        String sql = """
                 SELECT    
                 extra_equipemnt_description.extra_equipemnt_description
                 FROM
                 extra_equipemnt_description
                 JOIN
                extra_equipemnt_m2m on extra_equipemnt_m2m.extra_equipemnt_id = extra_equipemnt_description.extra_equipemnt_id
                WHERE
                extra_equipemnt_m2m.car_id =?;""";

        Connection conn = getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, carID);
            pstmt.execute();

            ResultSet resultSet = pstmt.getResultSet();
            while (resultSet.next()) {
                String equipment = resultSet.getString(1);

                extraEquipment.add(equipment);
            }
            return extraEquipment;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

