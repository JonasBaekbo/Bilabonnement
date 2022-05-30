//Mikkel, Johanne

package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.ExtraEquipment;

import java.sql.*;
import java.util.ArrayList;

import static com.example.bilabonnement.utility.DatabaseConnectionManager.getConnection;

public class ExtraEquipmentRepository implements IRepository<ExtraEquipment> {
    private final CarRepository carRepository = new CarRepository();


    public ArrayList<ExtraEquipment> getExtraEquipmentCarList() {
        Connection conn = getConnection();
        ArrayList<ExtraEquipment> result = new ArrayList<>();
        //Vi fra sortere status 5, da det er status for biler der ikke er hos bilabonnement.dk
        String sql = """
                SELECT
                cars.car_id,
                GROUP_CONCAT(extra_equipment_description.extra_equipment_description) AS extra_equipment
                FROM
                cars
                LEFT JOIN
                extra_equipment_m2m ON extra_equipment_m2m.car_id = cars.car_id
                LEFT JOIN
                extra_equipment_description ON extra_equipment_description.extra_equipment_id = extra_equipment_m2m.extra_equipment_id
                WHERE cars.car_status_id<>5
                GROUP BY cars.car_id""";
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
                INSERT INTO
                extra_equipment_m2m (extra_equipment_id, car_id)
                VALUES (?, ?)
                ON DUPLICATE KEY UPDATE extra_equipment_id = ?, car_id = ?""";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, entity.getExtraEquipmentID());
            pstmt.setInt(2, entity.getCarID());
            pstmt.setInt(3, entity.getExtraEquipmentID());
            pstmt.setInt(4, entity.getCarID());
            pstmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<ExtraEquipment> getAllEntities() {
        Connection conn = getConnection();

        String sql = """
                SELECT
                extra_equipment_description.extra_equipment_id,
                extra_equipment_description.extra_equipment_description
                FROM
                extra_equipment_description""";

        ArrayList<ExtraEquipment> result = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            while (resultSet.next()) {
                int descriptionID = resultSet.getInt("extra_equipment_id");
                String description = resultSet.getString("extra_equipment_description");
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

    @Override
    public ExtraEquipment getSingleById(int id) {
        return null;
    }
}

