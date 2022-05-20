package com.example.bilabonnement.models;

import com.example.bilabonnement.servises.DateTool;

import java.sql.Timestamp;

public class Car {
    private final DateTool dateTool =new DateTool();

    private int carID;
    private String vinNumber;
    private String numberPlate;
    private String manufacturer;
    private String modelName;
    private String fuelType;
    private String colour;
    private String gearType;
    private String carStatus;
    private Integer currentLeasing;
    private Timestamp registrationDate;


    public Car(int carID, String vinNumber, String numberPlate, String modelName, String fuelType, String colour, String gearType, String status, Integer currentLeasing, Timestamp registrationDate) {
        this.carID = carID;
        this.vinNumber = vinNumber;
        this.numberPlate = numberPlate;
        this.modelName = modelName;
        this.fuelType = fuelType;
        this.colour = colour;
        this.gearType = gearType;
        this.carStatus = status;
        this.currentLeasing = currentLeasing;
        this.registrationDate = registrationDate;

    }

    public Car(String vinNumber, String numberPlate, String modelName, String fuelType, String colour, String gearType, String status, Integer currentLeasing) {
        this.vinNumber = vinNumber;
        this.numberPlate = numberPlate;
        this.modelName = modelName;
        this.fuelType = fuelType;
        this.colour = colour;
        this.gearType = gearType;
        this.carStatus = status;
        this.currentLeasing = currentLeasing;
        this.registrationDate = dateTool.getTimeStamp();
    }



    public Car(int carID, String numberPlate, Integer currentLeasing, String carStatus) {
        this.carID = carID;
        this.numberPlate = numberPlate;
        this.currentLeasing = currentLeasing;
        this.carStatus = carStatus;

    }

    public Car(int carID,String vinNumber,String numberPlate ,String manufacturer, String modelName, String colour,String fuelType, String gearType){
        this.carID=carID;
        this.vinNumber=vinNumber;
        this.numberPlate=numberPlate;
        this.manufacturer=manufacturer;
        this.modelName=modelName;
        this.fuelType=fuelType;
        this.colour=colour;
        this.gearType=gearType;

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


    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
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

    public Integer getCurrentLeasing() {
        return currentLeasing;
    }

    public void setCurrentLeasing(Integer currentLeasing) {
        this.currentLeasing = currentLeasing;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}