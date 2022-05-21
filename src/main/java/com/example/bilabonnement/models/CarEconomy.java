package com.example.bilabonnement.models;

import java.sql.Timestamp;
import java.util.Date;

public class CarEconomy {
    private Car car;
    private int pricePrMonth;
    private Date leasingStartDate;
    private Date leasingEndDate;

    public CarEconomy(Car car, int pricePrMonth) {
        this.car = car;
        this.pricePrMonth = pricePrMonth;
    }

    public Date getLeasingStartDate() {
        return leasingStartDate;
    }

    public Date getLeasingEndDate() {
        return leasingEndDate;
    }

    public CarEconomy(Car car, int pricePrMonth, Date leasingStartDate, Date leasingEndDate) {
        this.car = car;
        this.pricePrMonth = pricePrMonth;
        this.leasingStartDate = leasingStartDate;
        this.leasingEndDate = leasingEndDate;
    }

    public int getPricePrMonth() {
        return pricePrMonth;
    }

}