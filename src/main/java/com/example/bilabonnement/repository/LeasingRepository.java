package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.*;
import com.example.bilabonnement.servises.DateTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class LeasingRepository implements IRepository<Leasing> {
    private CarRepository carRepository = new CarRepository();
    private CarStatusRepository csr = new CarStatusRepository();



    @Override
    public boolean create(Leasing entity) {
        Connection conn = getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO leasing(customer_id, car_id, start_date, end_date, included_km,leasing_added) VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, entity.getCustomerID());
            pstmt.setInt(2, entity.getCarID());
            pstmt.setObject(3, entity.getStartDate());
            pstmt.setObject(4, entity.getEndDate());
            pstmt.setInt(5, entity.getIncludedKM());
            pstmt.setTimestamp(6, entity.getTimeAdded());
            pstmt.execute();

            PreparedStatement pstmt2 = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            pstmt2.execute();
            ResultSet resultSet = pstmt2.getResultSet();
            resultSet.next();
            int leasingId = resultSet.getInt(1);

            Car car = carRepository.getSingleById(entity.getCarID());
            car.setCurrentLeasing(leasingId);
            String leasingtype=entity.getLeasingType();
            CarStatus carStatus = csr.getByName(leasingtype);
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
    public List getAllEntities() {


        return null;
    }

    @Override
    public boolean update(Leasing entity) {
        return false;
    }


}