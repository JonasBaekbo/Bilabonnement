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
    public boolean create(Damage damage) {
        Connection conn = getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO damages (car_id, damage_description, damages_cost_kr, claimant,damage_date,damage_added) VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, damage.getCarID());
            pstmt.setString(2, damage.getDamageDescription());
            pstmt.setDouble(3, damage.getPrice());
            pstmt.setString(4, damage.getClaimant());

            pstmt.setDate(5, dateTool.getUtilDateAsSQL(damage.getDamageRegistationsDate()));
            pstmt.setTimestamp(6, damage.getTimeStamp());
            pstmt.execute();

            updateAndSetCArStatus("skadet", damage);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Damage> getAllEntities() {
        return null;
    }

    public List<DamagedCar> getAllDamgesCars() {
        Connection conn = getConnection();
        ArrayList<DamagedCar> allDamagedCars = new ArrayList<>();

        String sql = """
                 SELECT
                   cars.car_id,
                   damages.damages_id,
                   cars.vin_number,
                   cars.numberplate,
                   manufacturer.manufacturer,
                   car_models.model,
                   damages.damage_description,
                   damages.damages_cost_kr,
                   damages.claimant,
                   damages.damage_date
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
                 WHERE damage_closed is null
                """;

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

    public void closeDamage(int damageId, Date damageFixedDate) {
        Damage damage = getSingleById(damageId);
        damage.setDamageFixedDate(damageFixedDate);
        update(damage);
        int carID=damage.getCarID();
        boolean isLast=checkIsLast(damageId,carID);
        if(isLast){
        updateAndSetCArStatus("hjemme", damage);}
    }


    private void updateAndSetCArStatus(String carStatus, Damage damage) {
        Car car = carRepository.getSingleById(damage.getCarID());
        carRepository.updateCarStatus(carStatus, car);

    }

    private boolean checkIsLast(int damageID, int carID) {

        Connection conn = getConnection();
        try {

            String countDamgesLeft = "SELECT COUNT(*) FROM bilabonnement.damages where damages_id<>? and car_id =? and damage_closed is null";
            PreparedStatement pstmt = conn.prepareStatement(countDamgesLeft);
            pstmt.setInt(1, damageID);
            pstmt.setInt(2, carID);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();

            rs.next();
            int damgesLeft = rs.getInt(1);
            return damgesLeft == 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in checkIsLast-method");
        }
        return false;
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
                    resultSet.getDouble("damages_cost_kr"),
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
    public boolean update(Damage damage) {
        Connection conn = getConnection();
        try {

            PreparedStatement pstmt = conn.prepareStatement("UPDATE damages SET car_id= ?, damage_description = ? ,damages_cost_kr= ?, claimant= ?, damage_date= ?, damage_closed= ?, damage_added = ? WHERE damages_id = ?");
            pstmt.setInt(1, damage.getCarID());
            pstmt.setString(2, damage.getDamageDescription());
            pstmt.setDouble(3, damage.getPrice());
            pstmt.setString(4, damage.getClaimant());
            pstmt.setDate(5, dateTool.getUtilDateAsSQL(damage.getDamageRegistationsDate()));
            pstmt.setDate(6, dateTool.getUtilDateAsSQL(damage.getDamageFixedDate()));
            pstmt.setTimestamp(7, damage.getTimeStamp());
            pstmt.setInt(8, damage.getDamageID());
            pstmt.execute();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
