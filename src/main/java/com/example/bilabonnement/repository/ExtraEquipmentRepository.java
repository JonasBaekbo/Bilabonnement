package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.ExtraEquipment;

import java.sql.*;
import java.util.ArrayList;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class ExtraEquipmentRepository implements IRepository<ExtraEquipment> {
    CarRepository carRepository =new CarRepository();


        public ArrayList<ExtraEquipment> getExtraEquipmentList() {
            //Vi fra sortere status 5, da det er status for biler der ikke er hos bilabonnement.dk
            Connection conn = getConnection();

            String sql = """
            SELECT
            cars.car_id,
            GROUP_CONCAT(extra_equipemnt_description.extra_equipemnt_description) AS extra_equipment
            FROM
            cars
            LEFT JOIN
            extra_equipemnt_m2m ON extra_equipemnt_m2m.car_id = cars.car_id
            LEFT JOIN
            extra_equipemnt_description ON extra_equipemnt_description.extra_equipemnt_id = extra_equipemnt_m2m.extra_equipemnt_id
            WHERE cars.car_status_id<>5
            GROUP BY cars.car_id
            """;

            ArrayList<ExtraEquipment> result = new ArrayList<>();

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.execute();
                ResultSet resultSet = pstmt.getResultSet();
                while (resultSet.next()) {
                    Car car = carRepository.getSingleById(resultSet.getInt("car_id"));
                    String descriptions = resultSet.getString("extra_equipment");
                    ExtraEquipment extra = new ExtraEquipment(car, descriptions);
                    result.add(extra);
                }
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }


    @Override
    public boolean create(ExtraEquipment entity) {
        Connection conn = getConnection();
        String sql = """
            insert into 
                extra_equipemnt_m2m (extra_equipemnt_id, car_id) 
            values (?, ?)
            ON DUPLICATE KEY UPDATE
                extra_equipemnt_id = ?, car_id = ?
            """;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, entity.getEkstraEquipemntdescriptionID());
            pstmt.setInt(2, entity.getCarID());
            pstmt.setInt(3, entity.getEkstraEquipemntdescriptionID());
            pstmt.setInt(4, entity.getCarID());
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ExtraEquipment getSingleById(int id) {
        return null;
    }

    @Override
    public ArrayList<ExtraEquipment> getAllEntities() {
        Connection conn = getConnection();

        String sql = "Select extra_equipemnt_description.extra_equipemnt_id,extra_equipemnt_description.extra_equipemnt_description from extra_equipemnt_description";

        ArrayList<ExtraEquipment> result = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            while (resultSet.next()) {
                int descriptionID=resultSet.getInt("extra_equipemnt_id");
                String description = resultSet.getString("extra_equipemnt_description");
                ExtraEquipment extra = new ExtraEquipment(descriptionID, description);
                result.add(extra);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(ExtraEquipment entity) {
        return false;
    }
}

