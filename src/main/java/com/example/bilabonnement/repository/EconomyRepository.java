package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.ulility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EconomyRepository implements IRepository<Car> {

    public int totalMonthlyIncome(List<Car> cars){




        return 0;
    }

    public List<Car> getAllRentedCars() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Car> allCars = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT biler.bil_id,status.status,bilmodeller.pris_pr_måned FROM status LEFT JOIN biler RIGHT JOIN bilmodeller");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if(rs.getString(2).equals("udlejet")) {
                    Car temp = new Car(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3)
                    );
                    allCars.add(temp);
                }
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allCars;
    }

    @Override
    public boolean create(Car entity) {
        return false;
    }

    @Override
    public Car getSingleById(int id) {
        return null;
    }

    @Override
    public List<Car> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Car> allCars = new ArrayList<>();
        try {
            String select ="SELECT"+
                    " bilabonnement.biler.bil_id," +
                    " bilabonnement.biler.registreringsnummer,"+
                    " bilabonnement.biler.aktuel_leasingaftale," +
                    " bilabonnement.status.status," +
                    " bilabonnement.bilmodeller.pris_pr_måned " +
                    "FROM biler INNER JOIN status ON biler.status = status.id_status INNER JOIN" +
                    " bilmodeller ON biler.model=bilmodeller.id_bilmodeller " +
                    "WHERE biler.status =1";
            PreparedStatement stmt = conn.prepareStatement(select);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {

                    Car temp = new Car(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5)
                    );
                    allCars.add(temp);
                }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement :)");
            e.printStackTrace();
        }
        return allCars;
    }

    @Override
    public boolean update(Car entity) {
        return false;
    }
}
