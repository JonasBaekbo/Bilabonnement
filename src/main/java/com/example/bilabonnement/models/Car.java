package com.example.bilabonnement.models;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Car {
    private int carID;
    private String chassisNumber;
    private String registrationNumber;
    private String modelName;
    private String fuelType;
    private String colour;
    private String gearType;
    private String carStatus;
    private int registrationFee;
    private int currentLeasing;
    private Timestamp registrationDate;
    private String currentLeasing;
    private Date registrationDate;
    private int pricePrMonth;

    public Car(int carID, String chassisNumber, String registrationNumber, String modelName, String fuelType, String colour, String gearType, String status, int registrationFee, int currentLeasing, Timestamp registrationDate) {
        this.carID = carID;
        this.chassisNumber = chassisNumber;
        this.registrationNumber = registrationNumber;
        this.modelName = modelName;
        this.fuelType = fuelType;
        this.colour = colour;
        this.gearType = gearType;
        this.carStatus = status;
        this.registrationFee = registrationFee;
        this.currentLeasing = currentLeasing;
        this.registrationDate = registrationDate;

    }

    public Car(String chassisNumber, String registrationNumber, String modelName, String fuelType, String colour, String gearType, String status, int registrationFee, int currentLeasing) {
        this.chassisNumber = chassisNumber;
        this.registrationNumber = registrationNumber;
        this.modelName = modelName;
        this.fuelType = fuelType;
        this.colour = colour;
        this.gearType = gearType;
        this.carStatus = status;
        this.registrationFee = registrationFee;
        this.currentLeasing = currentLeasing;
        this.registrationDate = getTimeStamp();
    }

    private Timestamp getTimeStamp() {
        LocalDateTime localDateTime= LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }


    public java.sql.Date getUtilDateAsSQL(Date utilDate) {
    public Car(int carID, String carStatus, int pricePrMonth) {
        this.carID = carID;
        this.carStatus = carStatus;
        this.pricePrMonth = pricePrMonth;

    }

    public Car(int carID, String registrationNumber, String currentLeasing, String carStatus, int pricePrMonth) {
        this.carID = carID;
        this.registrationNumber = registrationNumber;
        this.currentLeasing = currentLeasing;
        this.carStatus = carStatus;
        this.pricePrMonth = pricePrMonth;
    }

    public java.sql.Date getUtilAsSQL(Date utilDate) {
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }


    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getFuelType() {
        return fuelType;
    }


    public int getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(int registrationFee) {
        this.registrationFee = registrationFee;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public int getCurrentLeasing() {
        return currentLeasing;
    }

    public void setCurrentLeasing(int currentLeasing) {
        this.currentLeasing = currentLeasing;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getPricePrMonth() {
        return pricePrMonth;
    }

    public void setPricePrMonth(int pricePrMonth) {
        this.pricePrMonth = pricePrMonth;
    }
}