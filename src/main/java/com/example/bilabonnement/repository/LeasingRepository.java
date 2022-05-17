package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.Leasing;
import com.example.bilabonnement.servises.DateTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class LeasingRepository implements IRepository<Leasing> {
private DateTool dateTool =new DateTool();
private CarRepository carRepository = new CarRepository();

    @Override
    public boolean create(Leasing entity) {
        Connection conn = getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO leasing(customer_id, car_id, start_date, end_date, included_km,leasing_added) VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, entity.getCustomerID());
            pstmt.setInt(2, entity.getCarID());
            pstmt.setDate(3, dateTool.getUtilDateAsSQL(entity.getStartDate()));
            pstmt.setDate(4, dateTool.getUtilDateAsSQL(entity.getEndDate()));
            pstmt.setInt(5, entity.getIncludedKM());
            pstmt.setTimestamp(6,entity.getTimeAdded());
            pstmt.execute();

            PreparedStatement pstmt2 = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            pstmt2.execute();
            ResultSet resultSet = pstmt2.getResultSet();
            resultSet.next();
            int leasingId = resultSet.getInt(1);


            Car car = carRepository.getSingleById(entity.getCarID());
            car.setCurrentLeasing(leasingId);
            carRepository.updateCarStatus("udlejet",car);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void makeLease(int customerID,  Date startDate, Date endDate, int includedKM, int carID){
        Leasing lease = new Leasing(customerID, dateTool.getSQLDateAsUtil(startDate), dateTool.getSQLDateAsUtil(endDate), includedKM, carID);
        create(lease);
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