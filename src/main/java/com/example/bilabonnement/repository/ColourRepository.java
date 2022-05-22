package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Colour;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColourRepository extends ReadOnlyTable<Colour> {

    public List<Colour> getAll() {
        ArrayList<Colour> colours = new ArrayList<>();

        try {
            ResultSet resultSet = getResultSet("SELECT colour_id, colour FROM car_colour");
            while (resultSet.next()) {
                Colour colour = new Colour(resultSet.getInt("colour_id"), resultSet.getString("colour"));
                colours.add(colour);
            }
            return colours;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Colour getByID(int id) {
        try {
            ResultSet resultSet = getByID("SELECT colour_id, colour FROM car_colour WHERE colour_id = ?", id);
            Colour colour = new Colour(resultSet.getInt("colour_id"), resultSet.getString("colour"));
            return colour;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
