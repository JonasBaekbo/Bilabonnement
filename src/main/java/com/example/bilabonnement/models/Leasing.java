package com.example.bilabonnement.models;

import com.example.bilabonnement.servises.DateTool;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Leasing {
    private final DateTool dateTool =new DateTool();

    private int customerID;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int includedKM;
    private int carID;
    private int leasingId;
    private String leasingType;
    private Timestamp timeAdded;

    public Leasing(int customerID, LocalDateTime startDate, LocalDateTime endDate, int includedKM, int carID, String leasingType) {
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.includedKM = includedKM;
        this.carID = carID;
        this.leasingType = leasingType;
        this.timeAdded=dateTool.getTimeStamp();
    }

    public Leasing(int leasingId, int customerID, LocalDateTime startDate, LocalDateTime endDate, int includedKM, int carID, Timestamp timeAdded) {
        this.leasingId=leasingId;
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.includedKM = includedKM;
        this.carID = carID;
        this.timeAdded=timeAdded;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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

    public int getLeasingId() {
        return leasingId;
    }

    public void setLeasingId(int leasingId) {
        this.leasingId = leasingId;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getLeasingType() {
        return leasingType;
    }

    public void setLeasingType(String leasingType) {
        this.leasingType = leasingType;
    }
}




