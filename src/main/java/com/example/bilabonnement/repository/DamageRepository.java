//Johanne, Jonas
package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.CarStatus;
import com.example.bilabonnement.models.Damage;
import com.example.bilabonnement.services.DateTool;

import java.sql.*;
import java.util.ArrayList;

import static com.example.bilabonnement.utility.DatabaseConnectionManager.getConnection;

public class DamageRepository implements IRepository<Damage> {
    private final DateTool dateTool = new DateTool();

    private final CarRepository carRepository = new CarRepository();

    private final CarStatusRepository carStatusRepository = new CarStatusRepository();

    @Override
    public boolean create(Damage damage) {
        Connection conn = getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                    INSERT INTO
                    damages (car_id,
                    damage_description,
                    damages_cost_kr,
                    claimant,
                    damage_date,
                    damage_added)
                    VALUES (?,?,?,?,?,?)
                    """);
            pstmt.setInt(1, damage.getCarID());
            pstmt.setString(2, damage.getDamageDescription());
            pstmt.setDouble(3, damage.getPrice());
            pstmt.setString(4, damage.getClaimant());

            pstmt.setDate(5, dateTool.getUtilDateAsSQL(damage.getDamageRegistrationsDate()));
            pstmt.setTimestamp(6, damage.getTimeStamp());
            pstmt.execute();
            CarStatus carStatus = carStatusRepository.getByName("skadet");
            updateAndSetCArStatus(carStatus, damage);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public ArrayList<Damage> getAllEntities() {
        return null;
    }


    public void closeDamage(int damageId, Date damageFixedDate) {
        Damage damage = getSingleById(damageId);
        damage.setDamageFixedDate(damageFixedDate);
        update(damage);
        int carID = damage.getCarID();
        boolean isLast = checkIsLast(damageId, carID);
        if (isLast) {
            CarStatus carStatus = carStatusRepository.getByName("hjemme");
            Car car = carRepository.getSingleById(damage.getCarID());
            car.setCarStatus(carStatus);
            car.setCurrentLeasing(null);
            carRepository.update(car);
        }
    }


    private void updateAndSetCArStatus(CarStatus carStatus, Damage damage) {
        Car car = carRepository.getSingleById(damage.getCarID());
        carRepository.updateCarStatus(carStatus, car);

    }

    public boolean checkIsLast(int damageID, int carID) {
        Connection conn = getConnection();
        try {
            String countDamgesLeft = """
                    SELECT COUNT(*)
                    FROM bilabonnement.damages
                    WHERE damages_id<>? AND car_id =? AND damage_closed IS NULL
                    """;
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
        }
        return false;
    }

    @Override
    public Damage getSingleById(int id) {
        Connection conn = getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                    SELECT
                    car_id,
                    damage_description,
                    damages_cost_kr,
                    claimant,
                    damage_date,
                    damage_closed,
                    damage_added
                    FROM
                    damages
                    WHERE damages_id = ?
                    """);
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
                    resultSet.getTimestamp("damage_added"));
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

            PreparedStatement pstmt = conn.prepareStatement("""
                    UPDATE
                    damages
                    SET car_id= ?,
                    damage_description = ?,
                    damages_cost_kr= ?,
                    claimant= ?,
                    damage_date= ?,
                    damage_closed= ?,
                    damage_added = ?
                    WHERE damages_id = ?
                    """);
            pstmt.setInt(1, damage.getCarID());
            pstmt.setString(2, damage.getDamageDescription());
            pstmt.setDouble(3, damage.getPrice());
            pstmt.setString(4, damage.getClaimant());
            pstmt.setDate(5, dateTool.getUtilDateAsSQL(damage.getDamageRegistrationsDate()));
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
