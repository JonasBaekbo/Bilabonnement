package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.servises.DateTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class CarRepository implements IRepository<Car> {
    DateTool dateTool =new DateTool();

    @Override
    public boolean create(Car entity) {
        return false;
    }

    @Override
    public Car getSingleById(int id) {
        Connection conn = getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT car_id, vin_number, registration_number, car_model, fuel_type, colour, gear_type, car_status, current_leasing, car_added FROM cars WHERE car_id = ?");
            pstmt.setInt(1, id);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            resultSet.next();

            Car car = new Car(
                    resultSet.getInt("car_id"),
                    resultSet.getString("vin_number"),
                    resultSet.getString("registration_number"),
                    resultSet.getString("car_model"),
                    resultSet.getString("fuel_type"),
                    resultSet.getString("colour"),
                    resultSet.getString("gear_type"),
                    resultSet.getString("car_status"),
                    resultSet.getInt("current_leasing"),
                    resultSet.getTimestamp("car_added")
            );
            return car;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> getAllEntities() {
        return null;
    }

    @Override
    public boolean update(Car entity) {
        Connection conn = getConnection();
        try {
            // Get id_status
            int statusId = getStatusID(entity);

            // Update "biler"
            PreparedStatement pstmt = conn.prepareStatement("UPDATE cars SET car_status = ?, vin_number = ? ,registration_number= ?, car_model= ?, fuel_type= ?, colour= ?, gear_type= ?, current_leasing= ?, car_added= ? WHERE car_id = ?");
            pstmt.setInt(1, statusId);
            pstmt.setString(2, entity.getVinNumber());
            pstmt.setString(3, entity.getVinNumber());
            pstmt.setString(4, entity.getModelName());
            pstmt.setString(5, entity.getFuelType());
            pstmt.setString(6, entity.getColour());
            pstmt.setString(7, entity.getGearType());
            pstmt.setInt(8, entity.getCurrentLeasing());
            pstmt.setDate(9, dateTool.getUtilDateAsSQL(entity.getRegistrationDate()));
            pstmt.setInt(10, entity.getCarID());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int getStatusID(Car car) {
        Connection conn = getConnection();

        try {
            String status = car.getCarStatus();
            PreparedStatement pstmt = conn.prepareStatement("SELECT car_status_id FROM car_status WHERE car_status = ?");

            pstmt.setString(1, status);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            resultSet.next();
            int statusId = resultSet.getInt(1);
            return statusId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}



