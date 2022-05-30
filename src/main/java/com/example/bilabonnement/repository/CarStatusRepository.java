//Johanne
package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.CarStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarStatusRepository extends ReadOnlyTable<CarStatus> {

    public List<CarStatus> getAll() {
        ArrayList<CarStatus> carStatuses = new ArrayList<>();
        try {
            ResultSet resultSet = getResultSet("SELECT car_status_id,car_status FROM car_status");
            while (resultSet.next()) {
                CarStatus carStatus = new CarStatus(resultSet.getInt("car_status_id"), resultSet.getString("car_status"));
                carStatuses.add(carStatus);
            }
            return carStatuses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CarStatus getByID(int id) {
        try {
            ResultSet resultSet = getByID("SELECT car_status_id,car_status FROM car_status WHERE car_status_id = ?", id);
            CarStatus carStatus = new CarStatus(resultSet.getInt("car_status_id"), resultSet.getString("car_status"));
            return carStatus;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CarStatus getByName(String name) {
        try {
            ResultSet resultSet = getByName("SELECT car_status_id,car_status FROM car_status WHERE car_status = ?", name);
            CarStatus carStatus = new CarStatus(resultSet.getInt("car_status_id"), resultSet.getString("car_status"));
            return carStatus;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
