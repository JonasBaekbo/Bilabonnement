package com.example.bilabonnement.models;

import com.example.bilabonnement.servises.DateTool;

import java.sql.Timestamp;

public class Car {
    private final DateTool dateTool =new DateTool();

    private int carID;
    private CarStatus carStatus;
    private CarModel carModel;
    private FuelType fuelType;
    private GearType gearType;
    private Colour colour;
    private String vinNumber;
    private String numberPlate;
    private Integer currentLeasing;
    private Timestamp registrationDate;

    public Car(
            CarStatus carStatus,
            CarModel carModel,
            FuelType fuelType,
            GearType gearType,
            Colour colour,
            String numberPlate,
            String vinNumber
    ) {
        this.carStatus = carStatus;
        this.carModel = carModel;
        this.fuelType = fuelType;
        this.gearType = gearType;
        this.colour = colour;
        this.numberPlate = numberPlate;
        this.vinNumber = vinNumber;
        this.registrationDate = dateTool.getTimeStamp();
    }

    public Car(int carID, CarStatus carStatus, CarModel carModel, FuelType fuelType, GearType gearType, Colour colour, String vinNumber, String numberPlate, Integer currentLeasing, Timestamp registrationDate) {
        this.carID = carID;
        this.carStatus = carStatus;
        this.carModel = carModel;
        this.fuelType = fuelType;
        this.gearType = gearType;
        this.colour = colour;
        this.vinNumber = vinNumber;
        this.numberPlate = numberPlate;
        this.currentLeasing = currentLeasing;
        this.registrationDate = registrationDate;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
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

    public String getManufacturer() { return this.carModel.getManufacturer(); }

    public String getModelName() { return this.carModel.getModelName(); }

    public String getColourName() { return this.colour.getColourName(); }

    public String getFuelTypeName() { return this.fuelType.getFuelTypeName(); }

    public String getGearTypeName() { return this.gearType.getGearTypeName(); }
}