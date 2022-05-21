package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.CarModel;
import com.example.bilabonnement.models.Colour;
import com.example.bilabonnement.models.FuelType;
import com.example.bilabonnement.models.GearType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class GearTypeRepository extends ReadOnlyTable<GearType> {
    public List<GearType> getAll() {
        ArrayList<GearType> gearTypes = new ArrayList<>();

        try {
            ResultSet resultSet = getResultSet("SELECT gear_type_id,gear_type FROM gear_type");
            while (resultSet.next()) {
                GearType gearType = new GearType(resultSet.getInt("gear_type_id"), resultSet.getString("gear_type"));
                gearTypes.add(gearType);
            }
            return gearTypes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GearType getByID(int id) {
        try {
            ResultSet resultSet = getByID("SELECT gear_type_id,gear_type FROM gear_type WHERE gear_type_id = ?", id);
            GearType gearType = new GearType(resultSet.getInt("gear_type_id"), resultSet.getString("gear_type"));
            return gearType;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
