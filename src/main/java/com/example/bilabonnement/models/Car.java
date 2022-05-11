package com.example.bilabonnement.models;

import java.time.LocalDate;
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
    private String damages;
    private String currentLeasing;
    private Date registrationDate;

    public Car(int carID, String chassisNumber, String registrationNumber, String modelName, String fuelType, String colour, String gearType, String status, int registrationFee, String injuries, String currentLeasing, Date registrationDate) {
        this.carID = carID;
        this.chassisNumber = chassisNumber;
        this.registrationNumber = registrationNumber;
        this.modelName = modelName;
        this.fuelType = fuelType;
        this.colour = colour;
        this.gearType = gearType;
        this.carStatus = status;
        this.registrationFee = registrationFee;
        this.damages = injuries;
        this.currentLeasing = currentLeasing;
        this.registrationDate = registrationDate;

    }

    public Car(String chassisNumber, String registrationNumber, String modelName, String fuelType, String colour, String gearType, String status, int registrationFee, String injuries, String currentLeasing) {
        this.chassisNumber = chassisNumber;
        this.registrationNumber = registrationNumber;
        this.modelName = modelName;
        this.fuelType = fuelType;
        this.colour = colour;
        this.gearType = gearType;
        this.carStatus = status;
        this.registrationFee = registrationFee;
        this.damages = injuries;
        this.currentLeasing = currentLeasing;
        this.registrationDate = getDate();
    }

    public java.sql.Date getUtilAsSQL(Date utilDate) {
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    private Date getDate() {
        LocalDate localDate = LocalDate.now();
        Date currentDate = java.sql.Date.valueOf(localDate);
        return currentDate;
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


    public String getDamages() {
        return damages;
    }

    public void setDamages(String damages) {
        this.damages = damages;
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

    public String getCurrentLeasing() {
        return currentLeasing;
    }

    public void setCurrentLeasing(String currentLeasing) {
        this.currentLeasing = currentLeasing;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}