package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.CarEconomy;
import com.example.bilabonnement.ulility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EconomyRepository implements IRepository<CarEconomy> {

    CarRepository cr=new CarRepository();

    public int totalMonthlyIncome(List<CarEconomy> carEconomies){

        return 0;
    }

    public List<CarEconomy> getAllRentedCars() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<CarEconomy> allCars = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                    SELECT 
                    cars.car_id,
                    car_status.car_status,
                    car_models.price_per_month 
                    FROM cars 
                    JOIN  car_models ON cars.car_model = car_models.car_model_id
                    JOIN car_status ON cars.car_status=car_status.car_status_id;
                    """);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if(rs.getString(2).equals("udlejet-Limited")||rs.getString(2).equals("udlejet-unlimited")) {
                    Car car = cr.getSingleById(rs.getInt(1));
                    CarEconomy carEconomy=new CarEconomy(car, rs.getInt(3));
                    allCars.add(carEconomy);
                }
            }
        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allCars;
    }


    @Override
    public boolean create(CarEconomy entity) {
        return false;
    }

    @Override
    public CarEconomy getSingleById(int id) {
        return null;
    }

    @Override
    public List<CarEconomy> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<CarEconomy> allCars = new ArrayList<>();
        try {
            String select ="""
                    SELECT
                    cars.car_id,
                    car_models.price_per_month
                    FROM cars
                    JOIN car_status ON cars.car_status = car_status.car_status_id
                    JOIN car_models ON cars.car_model=car_models.car_model_id
                    WHERE cars.car_status IN (1,5)""";
            PreparedStatement stmt = conn.prepareStatement(select);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Car car = cr.getSingleById(rs.getInt(1));
                CarEconomy carEconomy=new CarEconomy(car, rs.getInt(2));
                    allCars.add(carEconomy);
                }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement :)");
            e.printStackTrace();
        }
        return allCars;
    }

    @Override
    public boolean update(CarEconomy entity) {
        return false;
    }

}
