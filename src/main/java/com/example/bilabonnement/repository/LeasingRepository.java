package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.Leasing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class LeasingRepository implements IRepository<Leasing> {

    @Override
    public boolean create(Leasing entity) {
        Connection conn = getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO leasing(`kunde_id`,`id_bil`, `start_dato`, `slut_dato`, `inkluderede_km`,timeAdded) VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, entity.getCustomerID());
            pstmt.setInt(2, entity.getCarID());
            pstmt.setDate(3, entity.getUtilDateAsSQL(entity.getStartDate()));
            pstmt.setDate(4, entity.getUtilDateAsSQL(entity.getEndDate()));
            pstmt.setInt(5, entity.getIncludedKM());
            pstmt.setTimestamp(6,entity.getTimeAdded());
            pstmt.execute();

            PreparedStatement pstmt2 = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            pstmt2.execute();
            ResultSet resultSet = pstmt2.getResultSet();
            resultSet.next();
            int leasingId = resultSet.getInt(1);

            CarRepository carRepository = new CarRepository();
            Car car = carRepository.getSingleById(entity.getCarID());
            car.setCarStatus("udlejet");
            car.setCurrentLeasing(leasingId);
            carRepository.update(car);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void makeLease(int customerID,  Date startDate, Date endDate, int includedKM, int carID){
        Leasing lease = new Leasing(customerID, getSQLDateAsUtil(startDate), getSQLDateAsUtil(endDate), includedKM, carID);
        create(lease);
    }


    private Date getSQLDateAsUtil(Date sqlDate) {
        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
        return utilDate;
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