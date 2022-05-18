package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.ekstraEquipemnt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class ExtraEquipmentRepository {

    public int getnumberOfCars() {
        Connection conn = getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                    SELECT COUNT(*) cars;
                    """);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int totalAmountOfCars = rs.getInt(1);
            return totalAmountOfCars;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

        public List<String> getListOfExtraEquipment ( int carID){
            Connection conn = getConnection();

            ArrayList<String> extraEquipment = new ArrayList<>();
            String sql = """
                    SELECT cars.registration_number, extra_equipemnt_description.extra_equipemnt_description
                                 FROM
                                 extra_equipemnt_description
                                 JOIN
                                 extra_equipemnt_m2m on extra_equipemnt_m2m.extra_equipemnt_id = extra_equipemnt_description.extra_equipemnt_id
                                 JOIN
                                 cars on extra_equipemnt_m2m.car_id = cars.car_id
                                 WHERE
                                 extra_equipemnt_m2m.car_id =?
                       """;


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

        public  List<ekstraEquipemnt> getAllExtraEqupment(){
            List<ekstraEquipemnt> allCars = new ArrayList<>();
            int numberOfCars=getnumberOfCars();

            for (int i = 0; i < numberOfCars; i++) {
               List<String>exraequipment=getListOfExtraEquipment(i);

            }
            return allCars;
        }


    }

