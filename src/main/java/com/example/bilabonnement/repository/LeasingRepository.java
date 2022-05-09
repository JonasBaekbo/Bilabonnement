package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Leasing;
import com.example.bilabonnement.ulility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeasingRepository implements IRepository<Leasing> {

    @Override
    public boolean create(Leasing entity) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO leasing(`kunde_id`,`id_bil`, `start_dato`, `slut_dato`, `inkluderede_km`) VALUES (?,?,?,?,?)");
            pstmt.setInt(1, entity.getCustomerID());
            pstmt.setInt(2, entity.getCarID());
            pstmt.setDate(3, (java.sql.Date) entity.getStartDate());
            pstmt.setDate(4, (java.sql.Date) entity.getEndDate());
            pstmt.setInt(5, entity.getIncludedKM());
            pstmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Leasing getSingleById(int id) {
        return null;
    }

    @Override
    public List getAllEntities() {
        return null;
    }
}