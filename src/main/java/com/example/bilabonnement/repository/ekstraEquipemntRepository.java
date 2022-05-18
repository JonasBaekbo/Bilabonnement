package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.CarEconomy;
import com.example.bilabonnement.models.Damage;
import com.example.bilabonnement.models.ekstraEquipemnt;
import com.example.bilabonnement.ulility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ekstraEquipemntRepository implements IRepository<ekstraEquipemnt>{

    CarRepository cr=new CarRepository();

    @Override
    public boolean create(ekstraEquipemnt entity) {
        return false;
    }

    @Override
    public ekstraEquipemnt getSingleById(int id) {
        return null;
    }

    @Override
    public List<ekstraEquipemnt> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<ekstraEquipemnt> allCars = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        List<String> ekstraEquipment = new ArrayList<>();
        int counter = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement("""
                    SELECT COUNT(*) cars;
                    """);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int totalAmountOfCars = rs.getInt(1);

            pstmt = conn.prepareStatement("""
                    SELECT cars.registration_number, extra_equipemnt_description.extra_equipemnt_description
                              FROM
                              extra_equipemnt_description
                              JOIN
                              extra_equipemnt_m2m on extra_equipemnt_m2m.extra_equipemnt_id = extra_equipemnt_description.extra_equipemnt_id
                              JOIN
                              cars on extra_equipemnt_m2m.car_id = cars.car_id
                              WHERE
                              extra_equipemnt_m2m.car_id =?
                    """);
            rs = pstmt.executeQuery();
            for (int i = 0; i < totalAmountOfCars; i++) {
                pstmt.setInt(1, );


            }



        }catch (SQLException e){
            System.out.println("Something wrong in statement :)");
            e.printStackTrace();
        }
        return allCars;
    }

    @Override
    public boolean update(ekstraEquipemnt entity) {
        return false;
    }
}
