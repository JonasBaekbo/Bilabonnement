package com.example.bilabonnement.models;

import com.example.bilabonnement.servises.EcomomyTool;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;


public class DamagedCar {


    private Car car;
    private Damage damage;

    public DamagedCar(Car car, Damage damage) {
        this.car = car;
        this.damage = damage;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public String getDamageDescription() {
        return damage.getDamageDescription();
    }


    public double getPrice() {
        return damage.getPrice();
    }


    public Date getDamageRegistationsDate() {
        return damage.getDamageRegistationsDate();
    }


    public Date getDamageFixedDate() {
        return damage.getDamageFixedDate();
    }


    public int getDamageID() {
        return damage.getDamageID();
    }


    public int getCarID() {
        return damage.getCarID();
    }


    public String getClaimant() {
        return damage.getClaimant();
    }


    public Timestamp getTimeStamp() {
        return damage.getTimeStamp();
    }


    public String getModelName() {
        return car.getModelName();
    }


    public String getFuelType() {
        return car.getFuelType();
    }


    public String getNumberPlate() {
        return car.getNumberPlate();
    }


    public String getColour() {
        return car.getColour();
    }


    public String getGearType() {
        return car.getGearType();
    }


    public String getCarStatus() {
        return car.getCarStatus();
    }


    public Integer getCurrentLeasing() {
        return car.getCurrentLeasing();
    }


    public Timestamp getRegistrationDate() {
        return car.getRegistrationDate();
    }


    public String getVinNumber() {
        return car.getVinNumber();
    }


    public String getManufacturer() {
        return car.getManufacturer();
    }


}
