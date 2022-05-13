package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.Damage;
import com.example.bilabonnement.models.DamagedCar;
import com.example.bilabonnement.servises.DateTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.bilabonnement.ulility.DatabaseConnectionManager.getConnection;

public class DamageRepository implements IRepository<Damage>{
   private DateTool dateTool =new DateTool();

    @Override
    public boolean create(Damage entity) {
        Connection conn = getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO skadeliste (id_bil, beskrivelse, omkostning_kr, anmelder,skade_dato,timestamp ) VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1, entity.getCarID());
            pstmt.setString(2,entity.getDamageDescription());
            pstmt.setInt(3,entity.getPrice());
            pstmt.setString(4,entity.getDamageRapporter());

            pstmt.setDate(5, dateTool.getUtilDateAsSQL(entity.getDamageRegistationsDate()));
            pstmt.setTimestamp(6,entity.getTimeStamp());
            pstmt.execute();

            CarRepository carRepository = new CarRepository();
            Car car = carRepository.getSingleById(entity.getCarID());
            car.setCarStatus("skadet");
            carRepository.update(car);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<DamagedCar> getAllDamgesCars(){
        ArrayList<DamagedCar>allDamagedCars=new ArrayList<>();

        String sql = """
                SELECT
                	biler.bil_id,
                    skadeliste.id_skade,
                    biler.stelnummer,
                    biler.registreringsnummer,
                    producent.prducent,
                    bilmodeller.bilmodel,
                    status.status,
                    skadeliste.beskrivelse,
                    skadeliste.omkostning_kr,
                    skadeliste.anmelder,
                    skadeliste.skade_dato,
                    skadeliste.skade_afsluttet
                FROM
                	biler
                JOIN
                    bilmodeller ON biler.model = bilmodeller.id_bilmodeller
                JOIN
                    producent ON bilmodeller.producent = producent.id_producent
                JOIN
                    status ON biler.status = status.id_status
                JOIN
                    skadeliste ON biler.bil_id = skadeliste.id_bil
                WHERE biler.status = 3
                """;

        Connection conn = getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            ResultSet resultSet = pstmt.getResultSet();
            while (resultSet.next()) {
                DamagedCar damagedCar = new DamagedCar(
                //int carID, int damageID, String chassisNumber, String registrationNumber, String modelName, String carStatus, String damageDescription, int price, String damageRapporter, Date damageRegistationsDate, Date damageFixedDate) {
                resultSet.getInt(1),  // carID
                        resultSet.getInt(2),  // damageID
                        resultSet.getString(3),  // chassisNumber
                        resultSet.getString(4),  // registrationNumber,
                        resultSet.getString(5),  // producerName
                        resultSet.getString(6),  // modelName
                        resultSet.getString(7),  // status
                        resultSet.getString(8),  // damageDescription
                        resultSet.getInt(9),  // price
                        resultSet.getString(10),  // damageRapporter
                        resultSet.getDate(11),  // damageRegistrationDate
                        resultSet.getDate(12)  // damageFixedDate
                );
                allDamagedCars.add(damagedCar);
            }
            return allDamagedCars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    @Override
    public Damage getSingleById(int id) {
        return null;
    }

    @Override
    public List<Damage> getAllEntities() {
        return null;
    }

    @Override
    public boolean update(Damage entity) {
            Connection conn = getConnection();
            try {

                // Update "skader"
                PreparedStatement pstmt = conn.prepareStatement("UPDATE skadeliste SET id_bil= ?, beskrivelse = ? ,omkostning_kr= ?, anmelder= ?, skade_dato= ?, skade_afsluttet= ?, timestamp = ? WHERE id_skade = ?");
                pstmt.setInt(1, entity.getCarID());
                pstmt.setString(2, entity.getDamageDescription());
                pstmt.setInt(3, entity.getPrice());
                pstmt.setString(4, entity.getDamageRapporter());
                pstmt.setDate(5, dateTool.getUtilDateAsSQL(entity.getDamageRegistationsDate()));
                pstmt.setDate(6, dateTool.getUtilDateAsSQL(entity.getDamageFixedDate()));
                pstmt.setInt(7, entity.getCarID());
                pstmt.execute();

                CarRepository carRepository = new CarRepository();
                Car car = carRepository.getSingleById(entity.getCarID());
                car.setCarStatus("hjemme");
                carRepository.update(car);

                return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }


    public static void main(String[] args) {
        DamageRepository dr=new DamageRepository();
        ArrayList<DamagedCar>all= (ArrayList<DamagedCar>) dr.getAllDamgesCars();
        System.out.println(all);
    }
}
