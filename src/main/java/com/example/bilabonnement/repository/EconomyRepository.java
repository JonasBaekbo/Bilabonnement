package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.CarEconomy;
import com.example.bilabonnement.models.CarStatus;
import com.example.bilabonnement.servises.DateTool;
import com.example.bilabonnement.ulility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EconomyRepository implements IRepository<CarEconomy> {

    CarRepository cr = new CarRepository();
    CarStatusRepository csr = new CarStatusRepository();

    public int totalMonthlyIncomeThisMonthFromRentedCars(){
        List<CarEconomy> carEconomies = this.getAllEntities();
        int totalMoeny = 0;
        for (CarEconomy carEconomy : carEconomies) {
            int moneyPrMonthFromCar = carEconomy.getPricePrMonth();
            totalMoeny = totalMoeny + moneyPrMonthFromCar;
        }
        return totalMoeny;
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
        DateTool dateTool = new DateTool();
        List<CarEconomy> carEconomies = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                    SELECT
                    cars.car_id,
                    cars.numberplate,
                    cars.current_leasing,
                    car_status.car_status,
                    car_models.price_per_month,
                    leasing.start_date,
                    leasing.end_date
                    FROM
                    cars
                    JOIN
                    car_models ON cars.car_model = car_models.car_model_id
                    JOIN
                    car_status ON cars.car_status=car_status.car_status_id
                    JOIN
                    leasing ON cars.current_leasing = leasing.leasing_id
                    where cars.car_status in (1,2)
                    """);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Car car = cr.getSingleById(rs.getInt("car_id"));
                CarStatus carStatus = csr.getByName(rs.getString("car_status"));
                car.setCarStatus(carStatus);
                CarEconomy carEconomy = new CarEconomy(
                        car,
                        rs.getInt("price_per_month"),
                        rs.getTimestamp("start_date"),
                        rs.getTimestamp("end_date")
                );
                carEconomies.add(carEconomy);
            }
        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return carEconomies;
    }

    @Override
    public boolean update(CarEconomy entity) {
        return false;
    }

}
