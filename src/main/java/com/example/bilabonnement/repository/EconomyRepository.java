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

    DateTool dateTool =new DateTool();
    CarRepository carRepository = new CarRepository();
    CarStatusRepository carStatusRepository = new CarStatusRepository();


    @Override
    public ArrayList<CarEconomy> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        ArrayList<CarEconomy> carEconomies = new ArrayList<>();

        //Vi søger på status 1 og 2 da det er status-id for de to lejetyper, og det kun er dem vi ønsker at se her
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                    SELECT
                    cars.car_id,
                    cars.licence_plate,
                    cars.current_leasing_id,
                    car_status.car_status,
                    car_models.price_per_month,
                    leasing.start_date,
                    leasing.end_date
                    FROM
                    cars
                    JOIN
                    car_models ON cars.car_model_id = car_models.car_model_id
                    JOIN
                    car_status ON cars.car_status_id=car_status.car_status_id
                    JOIN
                    leasing ON cars.current_leasing_id = leasing.leasing_id
                    where cars.car_status_id in (1,2)
                    """);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Car car = carRepository.getSingleById(rs.getInt("car_id"));
                CarStatus carStatus = carStatusRepository.getByName(rs.getString("car_status"));
                car.setCarStatus(carStatus);
                CarEconomy carEconomy = new CarEconomy(
                        car,
                        rs.getInt("price_per_month"),
                        (rs.getTimestamp("start_date").toLocalDateTime()),
                        (rs.getTimestamp("end_date").toLocalDateTime())
                );
                carEconomies.add(carEconomy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carEconomies;
    }

    @Override
    public boolean create(CarEconomy carEconomy) {
        return false;
    }

    @Override
    public CarEconomy getSingleById(int id) {
        return null;
    }

    @Override
    public boolean update(CarEconomy carEconomy) {
        return false;
    }


    public double totalMonthlyIncomeThisMonthFromRentedCars(){
        List<CarEconomy> carEconomies = this.getAllEntities();
        double totalMoeny = 0;
        for (CarEconomy carEconomy : carEconomies) {
            double moneyPrMonthFromCar = carEconomy.getPricePrMonth();
            totalMoeny = totalMoeny + moneyPrMonthFromCar;
        }
        return totalMoeny;
    }

}
