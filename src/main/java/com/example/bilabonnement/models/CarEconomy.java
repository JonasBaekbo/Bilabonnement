package com.example.bilabonnement.models;

import java.sql.Timestamp;

public class CarEconomy {
    private Car car;
    private int registrationFee;
    private int pricePrMonth;


    public CarEconomy(Car car, int pricePrMonth) {
        this.car = car;
        this.pricePrMonth = pricePrMonth;

    }

    public int getRegistrationFee() {
        return registrationFee;
    }

    public int getPricePrMonth() {
        return pricePrMonth;
    }

    public void setPricePrMonth(int pricePrMonth) {
        this.pricePrMonth = pricePrMonth;
    }


    public String getModelName() {
        return car.getModelName();
    }


    public String getFuelType() {
        return car.getFuelType();
    }


    public String getRegistrationNumber() {
        return car.getNumberPlate();
    }


    public String getVinNumber() {
        return car.getNumberPlate();
    }


    public String getColour() {
        return car.getColour();
    }


    public String getGearType() {
        return car.getGearType();
    }


    public int getCarID() {
        return car.getCarID();
    }

    public String getCarStatus() {
        return car.getCarStatus();
    }

    public int getCurrentLeasing() {
        return car.getCurrentLeasing();
    }

    public Timestamp getRegistrationDate() {
        return car.getRegistrationDate();
    }


}