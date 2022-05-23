package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.*;
import com.example.bilabonnement.servises.DateTool;

import java.sql.*;
import java.util.ArrayList;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class CarRepository implements IRepository<Car> {
    private final DateTool dateTool = new DateTool();

    private final CarStatusRepository carStatusRepository = new CarStatusRepository();
    private final CarModelRepository carModelRepository = new CarModelRepository();
    private final FuelTypeRepository fuelTypeRepository = new FuelTypeRepository();
    private final GearTypeRepository gearTypeRepository = new GearTypeRepository();
    private final ColourRepository colourRepository = new ColourRepository();

    @Override
    public boolean create(Car car) {
        Connection conn = getConnection();
        try {
            // Get id_status
            int statusId = getStatusID(car);
            int carModelID = car.getCarModel().getModelID();
            int fuelTypeID = car.getFuelType().getFuelTypeID();
            int gearTypeID = car.getGearType().getGearTypeID();
            int colourID = car.getColour().getColourID();

            // Add to "biler"
            PreparedStatement pstmt = conn.prepareStatement("""
                   INSERT INTO cars
                   (car_status_id, vin_number, licence_plate, car_model_id, fuel_type_id, colour_id, gear_type_id, current_leasing_id, car_added)
                   VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                   """);

            pstmt.setInt(1, statusId);
            pstmt.setString(2, car.getVinNumber());
            pstmt.setObject(3, car.getLicencePlate());
            pstmt.setInt(4, carModelID);
            pstmt.setInt(5, fuelTypeID);
            pstmt.setInt(6, colourID);
            pstmt.setInt(7, gearTypeID);
            pstmt.setObject(8, car.getCurrentLeasing());
            pstmt.setDate(9, dateTool.getUtilDateAsSQL(car.getRegistrationDate()));
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT car_id, vin_number, licence_plate, car_model_id, fuel_type_id, colour_id, gear_type_id, car_status_id, current_leasing_id, car_added,registration_fee FROM cars WHERE car_id = ?");
            pstmt.setInt(1, id);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            resultSet.next();

            CarStatus carStatus = carStatusRepository.getByID(resultSet.getInt("car_status_id"));
            CarModel carModel = carModelRepository.getByID(resultSet.getInt("car_model_id"));
            FuelType fuelType = fuelTypeRepository.getByID(resultSet.getInt("fuel_type_id"));
            GearType gearType = gearTypeRepository.getByID(resultSet.getInt("gear_type_id"));
            Colour colour = colourRepository.getByID(resultSet.getInt("colour_id"));

            Car car = new Car(
                    resultSet.getInt("car_id"),
                    carStatus,
                    carModel,
                    fuelType,
                    gearType,
                    colour,
                    resultSet.getString("vin_number"),
                    resultSet.getString("licence_plate"),
                    resultSet.getInt("current_leasing_id"),
                    resultSet.getTimestamp("car_added"),
                    resultSet.getDouble("registration_fee")
            );
            return car;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Car> getAllEntities() {
        //Vi fra sortere status 5, da det er status for biler der ikke er hos bilabonnement.dk
        ArrayList<Car> allCars;
        String sql = """
                SELECT
                    cars.car_id,
                    cars.car_status_id,
                    cars.licence_plate,
                    cars.vin_number,
                    cars.current_leasing_id,
                    cars.car_added,
                    cars.car_model_id,
                    cars.fuel_type_id,
                    cars.gear_type_id,
                    cars.colour_id,
                    cars.registration_fee
                FROM
                    cars
                WHERE
                    cars.car_status_id<>5
                """;
        allCars=findCars(sql);
        return allCars;
    }

    public ArrayList<Car> getAllFreeCars() {

        ArrayList<Car> allFreeCars;
        //Vi søger efter car_status=4, da det er id for "hjemme"
        String sql = """
                SELECT
                    cars.car_id,
                    cars.car_status_id,
                    cars.licence_plate,
                    cars.vin_number,
                    cars.current_leasing_id,
                    cars.car_added,
                    cars.car_model_id,
                    cars.fuel_type_id,
                    cars.gear_type_id,
                    cars.colour_id,
                    cars.registration_fee
                FROM
                    cars
                WHERE
                    cars.car_status_id = 4
                """;
        allFreeCars = findCars(sql);
        return allFreeCars;
    }

    public ArrayList<Car> getCarsMissigLicence() {
        ArrayList<Car> missingLicense;

        String sql= """
                SELECT
                    cars.car_id,
                    cars.car_status_id,
                    cars.licence_plate,
                    cars.vin_number,
                    cars.current_leasing_id,
                    cars.car_added,
                    cars.car_model_id,
                    cars.fuel_type_id,
                    cars.gear_type_id,
                    cars.colour_id,
                    cars.registration_fee
                FROM
                    cars
                WHERE
                    cars.licence_plate IS null OR cars.licence_plate=''
                """;
        missingLicense=findCars(sql);
        return missingLicense;
    }

    private ArrayList<Car> findCars(String sql) {
        Connection conn = getConnection();
        ArrayList<Car> cars = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            while (resultSet.next()) {
                CarStatus carStatus = carStatusRepository.getByID(resultSet.getInt("car_status_id"));
                CarModel carModel = carModelRepository.getByID(resultSet.getInt("car_model_id"));
                FuelType fuelType = fuelTypeRepository.getByID(resultSet.getInt("fuel_type_id"));
                GearType gearType = gearTypeRepository.getByID(resultSet.getInt("gear_type_id"));
                Colour colour = colourRepository.getByID(resultSet.getInt("colour_id"));

                Car car = new Car(
                        resultSet.getInt("car_id"),
                        carStatus,
                        carModel,
                        fuelType,
                        gearType,
                        colour,
                        resultSet.getString("vin_number"),
                        resultSet.getString("licence_plate"),
                        resultSet.getInt("current_leasing_id"),
                        resultSet.getTimestamp("car_added"),
                        resultSet.getDouble("registration_fee"));

                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Car car) {
        Connection conn = getConnection();
        try {
            // Get id_status
            int statusId = getStatusID(car);

            // Update "biler"
            PreparedStatement pstmt = conn.prepareStatement("UPDATE cars SET car_status_id = ?, vin_number = ? ,licence_plate= ?, car_model_id= ?, fuel_type_id= ?, colour_id= ?, gear_type_id= ?, current_leasing_id= ?, car_added= ?, registration_fee=? WHERE car_id = ?");
            pstmt.setInt(1, statusId);
            pstmt.setString(2, car.getVinNumber());
            pstmt.setString(3, car.getLicencePlate());
            pstmt.setInt(4, car.getModelID());
            pstmt.setInt(5, car.getFuelTypeID());
            pstmt.setInt(6, car.getColourID());
            pstmt.setInt(7, car.getGearTypeID());
            pstmt.setObject(8, car.getCurrentLeasing());
            pstmt.setDate(9, dateTool.getUtilDateAsSQL(car.getRegistrationDate()));
            pstmt.setObject(10, car.getRegistrationFee());
            pstmt.setInt(11, car.getCarID());
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