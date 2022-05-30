//Johanne
package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.FuelType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FuelTypeRepository extends ReadOnlyTable<FuelType> {

    public List<FuelType> getAll() {
        ArrayList<FuelType> fuelTypes = new ArrayList<>();

        try {
            ResultSet resultSet = getResultSet("SELECT fuel_type_id,fuel_type FROM fuel_types");
            while (resultSet.next()) {
                FuelType fuelType = new FuelType(resultSet.getInt("fuel_type_id"), resultSet.getString("fuel_type"));
                fuelTypes.add(fuelType);
            }
            return fuelTypes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FuelType getByID(int id) {
        try {
            ResultSet resultSet = getByID("SELECT fuel_type_id,fuel_type FROM fuel_types WHERE fuel_type_id = ?", id);
            FuelType fuelType = new FuelType(resultSet.getInt("fuel_type_id"), resultSet.getString("fuel_type"));
            return fuelType;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
