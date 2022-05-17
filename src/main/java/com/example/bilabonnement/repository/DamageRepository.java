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

    @Override
    public boolean create(Damage entity) {
        Connection conn = getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO damages (car_id, damage_description, damages_cost_kr, claimant,damage_date,damage_added) VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, entity.getCarID());
            pstmt.setString(2, entity.getDamageDescription());
            pstmt.setInt(3, entity.getPrice());
            pstmt.setString(4, entity.getDamageRapporter());

            pstmt.setDate(5, dateTool.getUtilDateAsSQL(entity.getDamageRegistationsDate()));
            pstmt.setTimestamp(6, entity.getTimeStamp());
            pstmt.execute();

            //CarRepository carRepository = new CarRepository();
            Car car = carRepository.getSingleById(entity.getCarID());
            car.setCarStatus("skadet");
            carRepository.update(car);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean closeDamage(int id){


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
                    damages.damage_description,
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
                Damage damage = getSingleById(resultSet.getInt(2));
                DamagedCar damagedCar = new DamagedCar(car, damage);

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

        Connection conn = getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT car_id, damage_description, damages_cost_kr, claimant, damage_date, damage_closed, damage_added FROM damages WHERE damages_id = ?");
            pstmt.setInt(1, id);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            resultSet.next();

            Damage damage = new Damage(
                    id,
                    resultSet.getInt("car_id"),
                    resultSet.getString("damage_description"),
                    resultSet.getInt("damages_cost_kr"),
                    resultSet.getString("claimant"),
                    resultSet.getDate("damage_date"),
                    resultSet.getDate("damage_closed"),
                    resultSet.getTimestamp("damage_added")
            );
            return damage;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            PreparedStatement pstmt = conn.prepareStatement("UPDATE damages SET car_id= ?, damage_description = ? ,damages_cost_kr= ?, claimant= ?, damage_date= ?, damage_closed= ?, damage_added = ? WHERE damages_id = ?");
            pstmt.setInt(1, entity.getCarID());
            pstmt.setString(2, entity.getDamageDescription());
            pstmt.setInt(3, entity.getPrice());
            pstmt.setString(4, entity.getDamageRapporter());
            pstmt.setDate(5, dateTool.getUtilDateAsSQL(entity.getDamageRegistationsDate()));
            pstmt.setDate(6, dateTool.getUtilDateAsSQL(entity.getDamageFixedDate()));
            pstmt.setInt(7, entity.getCarID());
            pstmt.execute();

            //CarRepository carRepository = new CarRepository();
            Car car = carRepository.getSingleById(entity.getCarID());
            car.setCarStatus("hjemme");
            carRepository.update(car);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setCarStatus(String carStatus, Damage entity){
        Car car = carRepository.getSingleById(entity.getCarID());
        car.setCarStatus(carStatus);
        carRepository.update(car);
    }

}
