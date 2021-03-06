//Adam, Johanne
package com.example.bilabonnement.models;

import com.example.bilabonnement.services.DateTool;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Leasing {
    private final DateTool dateTool = new DateTool();

    private int customerID;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int includedKM;
    private int carID;
    private int leasingId;
    private String leasingType;
    private Timestamp timeAdded;

    //Opret leasing i database
    public Leasing(int customerID, LocalDateTime startDate, LocalDateTime endDate, int includedKM, int carID, String leasingType) {
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.includedKM = includedKM;
        this.carID = carID;
        this.leasingType = leasingType;
        this.timeAdded = dateTool.getTimeStamp();
    }

    //Læs leasing ind fra database
    public Leasing(int leasingId, int customerID, LocalDateTime startDate, LocalDateTime endDate, int includedKM, int carID, Timestamp timeAdded) {
        this.leasingId = leasingId;
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.includedKM = includedKM;
        this.carID = carID;
        this.timeAdded = timeAdded;
    }

    public int getCustomerID() {
        return customerID;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public int getIncludedKM() {
        return includedKM;
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

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public String getLeasingType() {
        return leasingType;
    }

}




