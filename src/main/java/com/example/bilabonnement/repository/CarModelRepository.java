package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.CarModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class CarModelRepository extends ReadOnlyTable<CarModel> {
    public List<CarModel> getAll() {
        ArrayList<CarModel> carModels = new ArrayList<>();

        try {
            ResultSet resultSet = getResultSet("SELECT car_model_id,model,manufacturer.manufacturer FROM car_models join manufacturer on manufacturer.manufacturer_id = car_models.manufacturer");
            while (resultSet.next()) {
                CarModel carModel = new CarModel(resultSet.getInt("car_model_id"), resultSet.getString("model"), resultSet.getString("manufacturer"));
                carModels.add(carModel);
            }
            return carModels;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CarModel getByID(int id) {
        try {
            ResultSet resultSet = getByID("SELECT car_model_id,model,manufacturer.manufacturer FROM car_models join manufacturer on manufacturer.manufacturer_id = car_models.manufacturer WHERE car_model_id = ?", id);
            CarModel carModel = new CarModel(resultSet.getInt("car_model_id"), resultSet.getString("model"), resultSet.getString("manufacturer"));
            return carModel;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
