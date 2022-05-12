package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class CarRepository implements IRepository<Car> {
    @Override
    public boolean create(Car entity) {
        return false;
    }

    @Override
    public Car getSingleById(int id) {
        Connection conn = getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT bil_id, stelnummer, registreringsnummer, model, brændstoftype, farve, geartype, status, registreringsafgift,skader, aktuel_leasingaftale, bil_oprettet FROM biler WHERE bil_id = ?");
            pstmt.setInt(1, id);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            resultSet.next();

            Car car = new Car(
                    resultSet.getInt("bil_id"),
                    resultSet.getString("stelnummer"),
                    resultSet.getString("registreringsnummer"),
                    resultSet.getString("model"),
                    resultSet.getString("brændstoftype"),
                    resultSet.getString("farve"),
                    resultSet.getString("geartype"),
                    resultSet.getString("status"),
                    resultSet.getInt("registreringsafgift"),
                    resultSet.getString("skader"),
                    resultSet.getInt("aktuel_leasingaftale"),
                    resultSet.getTimestamp("bil_oprettet")
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
            PreparedStatement pstmt = conn.prepareStatement("UPDATE biler SET status = ?, stelnummer = ? ,registreringsnummer= ?, model= ?, brændstoftype= ?, farve= ?, geartype= ?, registreringsafgift =?,skader= ?, `aktuel_leasingaftale`= ?, bil_oprettet= ? WHERE bil_id = ?");
            pstmt.setInt(1, statusId);
            pstmt.setString(2, entity.getChassisNumber());
            pstmt.setString(3, entity.getRegistrationNumber());
            pstmt.setString(4, entity.getModelName());
            pstmt.setString(5, entity.getFuelType());
            pstmt.setString(6, entity.getColour());
            pstmt.setString(7, entity.getGearType());
            pstmt.setInt(8, entity.getRegistrationFee());
            pstmt.setString(9, entity.getDamages());
            pstmt.setInt(10, entity.getCurrentLeasing());
            pstmt.setDate(11, entity.getUtilDateAsSQL(entity.getRegistrationDate()));
            pstmt.setInt(12, entity.getCarID());
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT id_status FROM status WHERE status = ?");

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



