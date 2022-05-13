package com.example.bilabonnement.models;

import com.example.bilabonnement.servises.DateTool;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Leasing {
    private final DateTool dateTool =new DateTool();

    private int customerID;
    private Date startDate;
    private Date endDate;
    private int includedKM;
    private int carID;
    private int leasingId;
    private Timestamp timeAdded;

    public Leasing(int customerID, Date startDate, Date endDate, int includedKM, int carID) {
        this.customerID = customerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.includedKM = includedKM;
        this.carID = carID;
        this.timeAdded=dateTool.getTimeStamp();
    }


    public Leasing(int leasingId, int customerID,  Date startDate, Date endDate, int includedKM, int carID, Timestamp timeAdded) {
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
}




