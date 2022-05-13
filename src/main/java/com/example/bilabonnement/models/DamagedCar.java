package com.example.bilabonnement.models;

import java.sql.Date;


public class DamagedCar {

    private int carID;

    private int damageID;
    private String chassisNumber;
    private String registrationNumber;
    private String producerName;
    private String modelName;
    private String carStatus;
    private String damageDescription;
    private int price;
    private String damageRapporter;
    private java.sql.Date damageRegistationsDate;
    private Date damageFixedDate;

    public DamagedCar(int carID, int damageID, String chassisNumber, String registrationNumber, String producerName, String modelName, String carStatus, String damageDescription, int price, String damageRapporter, Date damageRegistationsDate, Date damageFixedDate) {
        this.carID = carID;
        this.damageID = damageID;
        this.chassisNumber = chassisNumber;
        this.registrationNumber = registrationNumber;
        this.producerName = producerName;
        this.modelName = modelName;
        this.carStatus = carStatus;
        this.damageDescription = damageDescription;
        this.price = price;
        this.damageRapporter = damageRapporter;
        this.damageRegistationsDate = damageRegistationsDate;
        this.damageFixedDate = damageFixedDate;
    }

    //skal slettes kun til test
    @Override
    public String toString() {
        return "DamagedCar{" +
                "carID=" + carID +
                ", damageID=" + damageID +
                ", chassisNumber='" + chassisNumber + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", producerName='" + producerName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", carStatus='" + carStatus + '\'' +
                ", damageDescription='" + damageDescription + '\'' +
                ", price=" + price +
                ", damageRapporter='" + damageRapporter + '\'' +
                ", damageRegistationsDate=" + damageRegistationsDate +
                ", damageFixedDate=" + damageFixedDate +
                '}';
    }
}
