//Mikkel, Adam

package com.example.bilabonnement.models;

import com.example.bilabonnement.services.DateTool;

import java.sql.Timestamp;

public class Car {


    private final DateTool dateTool = new DateTool();

    private int carID;
    private CarStatus carStatus;
    private CarModel carModel;
    private FuelType fuelType;
    private GearType gearType;
    private Colour colour;
    private String vinNumber;
    private String licencePlate;
    private Integer currentLeasing;
    private Double registrationFee;
    private Timestamp registrationDate;


    //Opret bil i database
    public Car(
            CarStatus carStatus, CarModel carModel, FuelType fuelType, GearType gearType, Colour colour, String licencePlate, String vinNumber, Double registrationFee) {
        this.carStatus = carStatus;
        this.carModel = carModel;
        this.fuelType = fuelType;
        this.gearType = gearType;
        this.colour = colour;
        this.licencePlate = licencePlate;
        this.vinNumber = vinNumber;
        this.registrationFee = registrationFee;
        this.registrationDate = dateTool.getTimeStamp();
    }

    //Læs bil ind fra database
    public Car(int carID, CarStatus carStatus, CarModel carModel, FuelType fuelType, GearType gearType, Colour colour, String vinNumber, String licencePlate, Integer currentLeasing, Timestamp registrationDate, Double registrationFee) {
        this.carID = carID;
        this.carStatus = carStatus;
        this.carModel = carModel;
        this.fuelType = fuelType;
        this.gearType = gearType;
        this.colour = colour;
        this.vinNumber = vinNumber;
        this.licencePlate = licencePlate;
        this.currentLeasing = currentLeasing;
        this.registrationDate = registrationDate;
        this.registrationFee = registrationFee;
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

    public FuelType getFuelType() {
        return fuelType;
    }

    public GearType getGearType() {
        return gearType;
    }

    public Colour getColour() {
        return colour;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
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

    public String getManufacturer() {
        return this.carModel.getManufacturer();
    }

    public String getModelName() {
        return this.carModel.getModelName();
    }

    public String getColourName() {
        return this.colour.getColourName();
    }

    public String getFuelTypeName() {
        return this.fuelType.getFuelTypeName();
    }

    public String getGearTypeName() {
        return this.gearType.getGearTypeName();
    }

    public int getModelID() {
        return this.carModel.getModelID();
    }

    public int getFuelTypeID() {
        return this.fuelType.getFuelTypeID();
    }

    public int getColourID() {
        return this.colour.getColourID();
    }

    public int getGearTypeID() {
        return this.gearType.getGearTypeID();
    }

    public Double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(Double registrationFee) {
        this.registrationFee = registrationFee;
    }
}