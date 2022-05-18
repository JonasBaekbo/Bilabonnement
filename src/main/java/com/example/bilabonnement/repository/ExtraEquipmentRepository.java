package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.ekstraEquipemnt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class ExtraEquipmentRepository implements IRepository<ekstraEquipemnt> {
    CarRepository cr=new CarRepository();

    public int getnumberOfCars() {
        Connection conn = getConnection();

            try {

                String numberOfCars = "SELECT COUNT(*) FROM cars";
                PreparedStatement pstmt = conn.prepareStatement(numberOfCars);
                pstmt.execute();
                ResultSet rs = pstmt.getResultSet();

                rs.next();
                int totalNumberOfCars = rs.getInt(1);
                System.out.println(totalNumberOfCars);
                return totalNumberOfCars;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("error in getNumbersOfCarsInTotal-method");
            }
            return -1;
        }


        public List<String> getListOfExtraEquipment ( int carID){
            Connection conn = getConnection();

            ArrayList<String> extraEquipment = new ArrayList<>();
            String sql = """
                    SELECT extra_equipemnt_description.extra_equipemnt_description
                                 FROM
                                 extra_equipemnt_description
                                 JOIN
                                 extra_equipemnt_m2m on extra_equipemnt_m2m.extra_equipemnt_id = extra_equipemnt_description.extra_equipemnt_id
                                 JOIN
                                 cars on extra_equipemnt_m2m.car_id = cars.car_id
                                 WHERE
                                 extra_equipemnt_m2m.car_id =?
                       """;


            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, carID);
                pstmt.execute();

                ResultSet resultSet = pstmt.getResultSet();
                while (resultSet.next()) {
                    String equipment = resultSet.getString(1);

                    extraEquipment.add(equipment);
                }
                return extraEquipment;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public  List<ekstraEquipemnt> getAllExtraEqupment(){
            List<ekstraEquipemnt> allCars = new ArrayList<>();
            int numberOfCars=getnumberOfCars();

            for (int i = 0; i < numberOfCars; i++) {
               ArrayList<String>exraequipment= (ArrayList<String>) getListOfExtraEquipment(i+1);
                Car car = cr.getSingleById(i+1);
               allCars.add(new ekstraEquipemnt(car,exraequipment));

            }
            return allCars;
        }


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
        return null;
    }

    @Override
    public boolean update(ekstraEquipemnt entity) {
        return false;
    }
}

