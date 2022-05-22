package com.example.bilabonnement.models;

import java.sql.Date;


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

    public int getDamageID() { return this.damage.getDamageID(); }

    public int getCarID() { return this.car.getCarID(); }

    public String getLicencePlate() { return this.car.getLicencePlate(); }

    public String getVinNumber() { return this.car.getVinNumber(); }

    public String getDamageDescription () { return this.damage.getDamageDescription(); }

    public Double getPrice() { return this.damage.getPrice(); }

    public String getClaimant() { return this.damage.getClaimant(); }

    public Date getDamageRegistationsDate() { return this.damage.getDamageRegistationsDate(); }


}
