//Mikkel

package com.example.bilabonnement.models;

import java.time.LocalDateTime;

public class CarEconomy {

    private Car car;
    private double pricePrMonth;
    private LocalDateTime leasingStartDate;
    private LocalDateTime leasingEndDate;

    public CarEconomy(Car car, int pricePrMonth, LocalDateTime leasingStartDate, LocalDateTime leasingEndDate) {
        this.car = car;
        this.pricePrMonth = pricePrMonth;
        this.leasingStartDate = leasingStartDate;
        this.leasingEndDate = leasingEndDate;
    }

    public LocalDateTime getLeasingStartDate() {
        return leasingStartDate;
    }

    public LocalDateTime getLeasingEndDate() {
        return leasingEndDate;
    }

    public double getPricePrMonth() {
        return pricePrMonth;
    }

    public int getCarID() {
        return this.car.getCarID();
    }

    public String getLicencePlate() {
        return this.car.getLicencePlate();
    }

    public int getCurrentLeasing() {
        return this.car.getCurrentLeasing();
    }

    public String getCarStatus() {
        CarStatus carStatus = this.car.getCarStatus();
        String status = carStatus.getCarStatusName();
        return status;
    }
}