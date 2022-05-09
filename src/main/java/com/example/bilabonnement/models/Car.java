package com.example.bilabonnement.models;

import java.util.Date;

public class Car {
    private int carID;
    private int chassisNumber;
    private String registrationNumber;
    private String modelName;
    private Enum fuelType;
    private String colour;
    private String gearType;
    private String carStatus;
    private int registrationFee;
    private Date rentedFrom;
    private Date rentedTo;
    private String damages;

    public Car(int carID, int chassisNumber, String registrationNumber, String modelName, Enum fuelType, String colour, String gearType, String status, int registrationFee, Date rentedFrom, Date rentedTo, String injuries) {
        this.carID = carID;
        this.chassisNumber = chassisNumber;
        this.registrationNumber = registrationNumber;
        this.modelName = modelName;
        this.fuelType = fuelType;
        this.colour = colour;
        this.gearType = gearType;
        this.carStatus = status;
        this.registrationFee = registrationFee;
        this.rentedFrom = rentedFrom;
        this.rentedTo = rentedTo;
        this.damages = injuries;
    }

    public Car(int chassisNumber, String registrationNumber, String modelName, Enum fuelType, String colour, String gearType, String status, int registrationFee, Date rentedFrom, Date rentedTo, String injuries) {
        this.chassisNumber = chassisNumber;
        this.registrationNumber = registrationNumber;
        this.modelName = modelName;
        this.fuelType = fuelType;
        this.colour = colour;
        this.gearType = gearType;
        this.carStatus = status;
        this.registrationFee = registrationFee;
        this.rentedFrom = rentedFrom;
        this.rentedTo = rentedTo;
        this.damages = injuries;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Enum getFuelType() {
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

    public int getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(int chassisNumber) {
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

    public void setFuelType(Enum fuelType) {
        this.fuelType = fuelType;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }
    public Date getRentedFrom() {
        return rentedFrom;
    }

    public void setRentedFrom(Date rentedFrom) {
        this.rentedFrom = rentedFrom;
    }

    public Date getRentedTo() {
        return rentedTo;
    }

    public void setRentedTo(Date rentedTo) {
        this.rentedTo = rentedTo;
    }
}
