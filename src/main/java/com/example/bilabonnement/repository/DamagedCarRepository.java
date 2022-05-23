package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.Damage;
import com.example.bilabonnement.models.DamagedCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;


public class DamagedCarRepository {

    private final CarRepository carRepository = new CarRepository();
    private final DamageRepository damageRepository =new DamageRepository();


    public List<DamagedCar> getAllDamgesCars() {
        Connection conn = getConnection();
        ArrayList<DamagedCar> allDamagedCars = new ArrayList<>();

        String sql = "SELECT cars.car_id, damages.damages_id, cars.vin_number, cars.licence_plate, manufacturer.manufacturer, car_models.model, damages.damage_description, damages.damages_cost_kr, damages.claimant, damages.damage_date FROM cars JOIN car_models ON cars.car_model_id = car_models.car_model_id JOIN manufacturer ON car_models.manufacturer_id = manufacturer.manufacturer_id JOIN car_status ON cars.car_status_id = car_status.car_status_id JOIN damages ON cars.car_id = damages.car_id WHERE  damage_closed is null ORDER BY damages.damages_id, cars.car_id ";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            while (resultSet.next()) {
                Car car = carRepository.getSingleById(resultSet.getInt("car_id"));
                Damage damage = damageRepository.getSingleById(resultSet.getInt("damages_id"));
                DamagedCar damagedCar = new DamagedCar(car, damage);

                allDamagedCars.add(damagedCar);
            }
            return allDamagedCars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
