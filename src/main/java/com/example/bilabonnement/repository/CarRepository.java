package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.*;
import com.example.bilabonnement.servises.DateTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class CarRepository implements IRepository<Car> {
    DateTool dateTool = new DateTool();

    CarStatusRepository csr = new CarStatusRepository();
    CarModelRepository cmr = new CarModelRepository();
    FuelTypeRepository ftr = new FuelTypeRepository();
    GearTypeRepository gtr = new GearTypeRepository();
    ColourRepository colourRepository = new ColourRepository();

    @Override
    public boolean create(Car entity) {
        Connection conn = getConnection();
        try {
            // Get id_status
            int statusId = getStatusID(entity);
            int carModelID = entity.getCarModel().getModelID();
            int fuelTypeID = entity.getFuelType().getFuelTypeID();
            int gearTypeID = entity.getGearType().getGearTypeID();
            int colourID = entity.getColour().getColourID();

            // Add to "biler"
            PreparedStatement pstmt = conn.prepareStatement(
                    """
                                INSERT INTO cars 
                                (car_status, vin_number, numberplate, car_model, fuel_type, colour, gear_type, current_leasing, car_added)
                                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                            """
            );
            pstmt.setInt(1, statusId);
            pstmt.setString(2, entity.getVinNumber());
            pstmt.setObject(3, entity.getNumberPlate());
            pstmt.setInt(4, carModelID);
            pstmt.setInt(5, fuelTypeID);
            pstmt.setInt(6, colourID);
            pstmt.setInt(7, gearTypeID);
            pstmt.setObject(8, entity.getCurrentLeasing());
            pstmt.setDate(9, dateTool.getUtilDateAsSQL(entity.getRegistrationDate()));
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Car getSingleById(int id) {
        Connection conn = getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT car_id, vin_number, numberplate, car_model, fuel_type, colour, gear_type, car_status, current_leasing, car_added FROM cars WHERE car_id = ?");
            pstmt.setInt(1, id);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            resultSet.next();

            CarStatus carStatus = csr.getByID(resultSet.getInt("car_status"));
            CarModel carModel = cmr.getByID(resultSet.getInt("car_model"));
            FuelType fuelType = ftr.getByID(resultSet.getInt("fuel_type"));
            GearType gearType = gtr.getByID(resultSet.getInt("gear_type"));
            Colour colour = colourRepository.getByID(resultSet.getInt("colour"));

            Car car = new Car(
                    resultSet.getInt("car_id"),
                    carStatus,
                    carModel,
                    fuelType,
                    gearType,
                    colour,
                    resultSet.getString("vin_number"),
                    resultSet.getString("numberplate"),
                    resultSet.getInt("current_leasing"),
                    resultSet.getTimestamp("car_added")
            );
            return car;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> getAllEntities() {
        //vi fra sortere status 5, da det er status for biler der ikke er hos bilabonnement.dk
        ArrayList<Car> allCars;
        String sql = """
                   
                SELECT
                cars.car_id,
                        cars.car_status,
                        cars.numberplate,
                        cars.vin_number,
                        cars.current_leasing,
                        cars.car_added,
                        cars.car_model,
                        cars.fuel_type,
                        cars.gear_type,
                        cars.colour
                FROM
                        cars
                WHERE
                cars.car_status<>5
                   """;

        allCars = (ArrayList<Car>) findCars(sql);
        return allCars;
    }


    public List<Car> getAllFreeCars() {
        ArrayList<Car> allFreeCars;
        String sql = """
                SELECT 
                    cars.car_id,
                    cars.car_status,
                    cars.numberplate,
                    cars.vin_number,
                    cars.current_leasing,
                    cars.car_added,
                    cars.car_model,
                    cars.fuel_type,
                    cars.gear_type,
                    cars.colour
                FROM
                    cars
                WHERE                     
                    cars.car_status = 4
                """;


        allFreeCars = (ArrayList<Car>) findCars(sql);
        return allFreeCars;
    }

    private List<Car> findCars(String sql) {
        Connection conn = getConnection();
        ArrayList<Car> cars = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            while (resultSet.next()) {
                CarStatus carStatus = csr.getByID(resultSet.getInt("car_status"));
                CarModel carModel = cmr.getByID(resultSet.getInt("car_model"));
                FuelType fuelType = ftr.getByID(resultSet.getInt("fuel_type"));
                GearType gearType = gtr.getByID(resultSet.getInt("gear_type"));
                Colour colour = colourRepository.getByID(resultSet.getInt("colour"));

                Car car = new Car(
                        resultSet.getInt("car_id"),
                        carStatus,
                        carModel,
                        fuelType,
                        gearType,
                        colour,
                        resultSet.getString("vin_number"),
                        resultSet.getString("numberplate"),
                        resultSet.getInt("current_leasing"),
                        resultSet.getTimestamp("car_added")
                );


                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean update(Car entity) {
        Connection conn = getConnection();
        try {
            // Get id_status
            int statusId = getStatusID(entity);

            // Update "biler"
            PreparedStatement pstmt = conn.prepareStatement("UPDATE cars SET car_status = ?, vin_number = ? ,numberplate= ?, car_model= ?, fuel_type= ?, colour= ?, gear_type= ?, current_leasing= ?, car_added= ? WHERE car_id = ?");
            pstmt.setInt(1, statusId);
            pstmt.setString(2, entity.getVinNumber());
            pstmt.setString(3, entity.getNumberPlate());
            pstmt.setInt(4, entity.getCarModel().getModelID());
            pstmt.setInt(5, entity.getFuelType().getFuelTypeID());
            pstmt.setInt(6, entity.getColour().getColourID());
            pstmt.setInt(7, entity.getGearType().getGearTypeID());
            pstmt.setObject(8, entity.getCurrentLeasing() == 0 ? null : entity.getCurrentLeasing());
            pstmt.setDate(9, dateTool.getUtilDateAsSQL(entity.getRegistrationDate()));
            pstmt.setInt(10, entity.getCarID());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int getStatusID(Car car) {
        CarStatus carStatus = car.getCarStatus();
        return carStatus.getCarStatusId();
    }

    public void updateCarStatus(CarStatus carStatus, Car car) {
        car.setCarStatus(carStatus);
        update(car);
    }

}



