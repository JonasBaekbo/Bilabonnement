package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.Damage;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class DamageRepository implements IRepository<Damage>{

    @Override
    public boolean create(Damage entity) {
        Connection conn = getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO skader (id_bil, beskrivelse, omkostning_kr, anmelder,skade_dato,timestamp ) VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, entity.getCarID());
            pstmt.setString(2,entity.getDamageDescription());
            pstmt.setInt(3,entity.getPrice());
            pstmt.setString(4,entity.getDamageRapporter());
            pstmt.setDate(5, entity.getUtilDateAsSQL(entity.getDamageRegistationsDate()));
            pstmt.setTimestamp(6,entity.getTimeStamp());
            pstmt.execute();

            PreparedStatement pstmt2 = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            pstmt2.execute();
            ResultSet resultSet = pstmt2.getResultSet();
            resultSet.next();
            int leasingId = resultSet.getInt(1);

            CarRepository carRepository = new CarRepository();
            Car car = carRepository.getSingleById(entity.getCarID());
            car.setCarStatus("skadet");
            car.setCurrentLeasing(leasingId);
            carRepository.update(car);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
        return false;
    }

    public static void main(String[] args) {
        DamageRepository dr=new DamageRepository();
        Damage damage = new Damage(2,"bule i højre side dør", 1200,"FDM", new Date(2022,13,6));
        dr.create(damage);
    }
}
