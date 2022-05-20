package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.Damage;
import com.example.bilabonnement.models.DamagedCar;
import com.example.bilabonnement.models.Leasing;
import com.example.bilabonnement.servises.DateTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class LeasingRepository implements IRepository<Leasing> {
    private DateTool dateTool = new DateTool();
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
            carRepository.updateCarStatus(leasingtype, car);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

/*
    public void makeLease(int customerID, Date startDate, Date endDate, int includedKM, int carID) {
        Leasing lease = new Leasing(customerID, dateTool.getSQLDateAsUtil(startDate), dateTool.getSQLDateAsUtil(endDate), includedKM, carID);
        create(lease);
    }
*/

    @Override
    public Leasing getSingleById(int id) {
        return null;
    }

    @Override
    public List getAllEntities() {

        Connection conn = getConnection();
        ArrayList<Car> allFreeCars = new ArrayList<>();

        String sql = """
                    SELECT 
                        cars.car_id,
                        cars.numberplate,
                        cars.vin_number,
                        manufacturer.manufacturer,
                        car_models.model,
                        car_colour.colour,
                        fuel_types.fuel_type,
                        gear_type.gear_type,
                         car_status.car_status
                    FROM
                        cars
                    JOIN
                        car_models ON cars.car_model = car_models.car_model_id
                    JOIN
                        manufacturer ON car_models.manufacturer = manufacturer.manufacturer_id
                    JOIN
                        car_status ON cars.car_status = car_status.car_status_id
                    JOIN
                        car_colour on cars.colour=car_colour.colour_id
                    JOIN
                        fuel_types on cars.fuel_type=fuel_types.fuel_type_id
                    JOIN
                        gear_type on cars.gear_type=gear_type.gear_type_id
                    WHERE 
                     cars.car_status = 4
                    """;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            while (resultSet.next()) {
                Car car = new Car(resultSet.getInt("car_id"),
                        resultSet.getString("vin_number"),
                        resultSet.getString("numberplate"),
                        resultSet.getString("manufacturer"),
                        resultSet.getString("model"),
                        resultSet.getString("colour"),
                        resultSet.getString("fuel_type"),
                        resultSet.getString("gear_type"));


                allFreeCars.add(car);
            }
            return allFreeCars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Leasing entity) {
        return false;
    }


}