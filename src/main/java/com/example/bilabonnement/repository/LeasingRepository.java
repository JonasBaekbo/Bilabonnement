//Adam, Johanne
package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.*;

import java.sql.*;
import java.util.ArrayList;

import static com.example.bilabonnement.utility.DatabaseConnectionManager.getConnection;

public class LeasingRepository implements IRepository<Leasing> {
    private final CarRepository carRepository = new CarRepository();
    private final CarStatusRepository carStatusRepository = new CarStatusRepository();


    @Override
    public boolean create(Leasing leasing) {
        Connection conn = getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                    INSERT INTO
                    leasing(
                    customer_id,
                    car_id,
                    start_date,
                    end_date,
                    included_km,
                    leasing_added)
                    VALUES (?,?,?,?,?,?)""");
            pstmt.setInt(1, leasing.getCustomerID());
            pstmt.setInt(2, leasing.getCarID());
            pstmt.setObject(3, leasing.getStartDate());
            pstmt.setObject(4, leasing.getEndDate());
            pstmt.setInt(5, leasing.getIncludedKM());
            pstmt.setTimestamp(6, leasing.getTimeAdded());
            pstmt.execute();

            PreparedStatement pstmt2 = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            pstmt2.execute();
            ResultSet resultSet = pstmt2.getResultSet();
            resultSet.next();
            int leasingId = resultSet.getInt(1);

            Car car = carRepository.getSingleById(leasing.getCarID());
            car.setCurrentLeasing(leasingId);
            String leasingtype = leasing.getLeasingType();
            CarStatus carStatus = carStatusRepository.getByName(leasingtype);
            carRepository.updateCarStatus(carStatus, car);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Leasing getSingleById(int id) {
        return null;
    }

    @Override
    public ArrayList getAllEntities() {
        return null;
    }

    @Override
    public boolean update(Leasing leasing) {
        return false;
    }


}