package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.Damage;
import com.example.bilabonnement.models.DamagedCar;
import com.example.bilabonnement.servises.DateTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class DamageRepository implements IRepository<Damage> {
    private final DateTool dateTool = new DateTool();

    private final CarRepository carRepository = new CarRepository();
    private final DamageRepository damageRepository = new DamageRepository();

    @Override
    public boolean create(Damage entity) {
        Connection conn = getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO damages (car_id, description, damages_cost_kr, claimant,damage_date,damage_added) VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, entity.getCarID());
            pstmt.setString(2, entity.getDamageDescription());
            pstmt.setInt(3, entity.getPrice());
            pstmt.setString(4, entity.getDamageRapporter());

            pstmt.setDate(5, dateTool.getUtilDateAsSQL(entity.getDamageRegistationsDate()));
            pstmt.setTimestamp(6, entity.getTimeStamp());
            pstmt.execute();

            CarRepository carRepository = new CarRepository();
            Car car = carRepository.getSingleById(entity.getCarID());
            car.setCarStatus("skadet");
            carRepository.update(car);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<DamagedCar> getAllDamgesCars() {
        ArrayList<DamagedCar> allDamagedCars = new ArrayList<>();

        String sql = """
                SELECT
                	cars.car_id,
                    damages.damages_id,
                    cars.vin_number,
                    cars.registration_number,
                    manufacturer.manufacturer,
                    car_models.model,
                    car_status.car_status,
                    damages.description,
                    damages.damages_cost_kr,
                    damages.claimant,
                    damages.damage_date,
                    damages.damage_closed
                FROM
                	cars
                JOIN
                    car_models ON cars.car_model = car_models.car_model_id
                JOIN
                    manufacturer ON car_models.manufacturer = manufacturer.manufacturer_id
                JOIN
                    car_status ON cars.car_status = car_status.car_status_id
                JOIN
                    damages ON cars.car_id = damages.car_id
                WHERE cars.car_status = 3
                """;

        Connection conn = getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            while (resultSet.next()) {

                Car car = carRepository.getSingleById(resultSet.getInt(1));
                Damage damage = damageRepository.getSingleById(resultSet.getInt(2));
                DamagedCar damagedCar = new DamagedCar(car, damage);

/*
                DamagedCar damagedCar = new DamagedCar(
                //int carID, int damageID, String chassisNumber, String registrationNumber, String modelName, String carStatus, String damageDescription, int price, String damageRapporter, Date damageRegistationsDate, Date damageFixedDate) {
                resultSet.getInt(1),  // carID
                        resultSet.getInt(2),  // damageID
                        resultSet.getString(3),  // chassisNumber
                        resultSet.getString(4),  // registrationNumber,
                        resultSet.getString(5),  // producerName
                        resultSet.getString(6),  // modelName
                        resultSet.getString(7),  // status
                        resultSet.getString(8),  // damageDescription
                        resultSet.getInt(9),  // price
                        resultSet.getString(10),  // damageRapporter
                        resultSet.getDate(11),  // damageRegistrationDate
                        resultSet.getDate(12)  // damageFixedDate
                );*/
                allDamagedCars.add(damagedCar);
            }
            return allDamagedCars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Damage getSingleById(int id) {
        return null;
    }

    @Override
    public List<Damage> getAllEntities() {
        return null;
    }

    @Override
    public boolean update(Damage entity) {
        Connection conn = getConnection();
        try {

            // Update "skader"
            PreparedStatement pstmt = conn.prepareStatement("UPDATE damages SET car_id= ?, description = ? ,damages_cost_kr= ?, claimant= ?, damage_date= ?, damage_closed= ?, damage_added = ? WHERE damages_id = ?");
            pstmt.setInt(1, entity.getCarID());
            pstmt.setString(2, entity.getDamageDescription());
            pstmt.setInt(3, entity.getPrice());
            pstmt.setString(4, entity.getDamageRapporter());
            pstmt.setDate(5, dateTool.getUtilDateAsSQL(entity.getDamageRegistationsDate()));
            pstmt.setDate(6, dateTool.getUtilDateAsSQL(entity.getDamageFixedDate()));
            pstmt.setInt(7, entity.getCarID());
            pstmt.execute();

            CarRepository carRepository = new CarRepository();
            Car car = carRepository.getSingleById(entity.getCarID());
            car.setCarStatus("hjemme");
            carRepository.update(car);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
