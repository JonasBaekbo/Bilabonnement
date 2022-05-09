package com.example.bilabonnement.models;

import java.util.Date;

public class Leje {

    private String customerID;
    private Date startDate;
    private Date endDate;
    private int includedKM;
    private int carID;

    public Leje(String customerID, Date startDate, Date endDate, int includedKM, int carID) {
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.includedKM = includedKM;
        this.carID = carID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getIncludedKM() {
        return includedKM;
    }

    public void setIncludedKM(int includedKM) {
        this.includedKM = includedKM;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }
}
