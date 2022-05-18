package com.example.bilabonnement.models;

import com.example.bilabonnement.servises.DateTool;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;

public class Damage {
    private final DateTool dateTool = new DateTool();


    private int damageID;
    private int carID;
    private String damageDescription;
    private double price;
    private String claimant;
    private Date damageRegistationsDate;
    private Date damageFixedDate;
    private Timestamp timestamp;

    public Damage(int carID, String damageDescription, double price,String claimant, Date damageRegistationsDate) {
        this.carID=carID;
        this.damageDescription = damageDescription;
        this.price = price;
        this.claimant =claimant;
        this.damageRegistationsDate = damageRegistationsDate;
        this.damageFixedDate = null;
        this.timestamp = dateTool.getTimeStamp();
    }

    public Damage(int damageID, int carID, String damageDescription, double price, String claimant,Date damageRegistationsDate, Date damageFixedDate, Timestamp timestamp) {
        this.damageID = damageID;
        this.carID=carID;
        this.damageDescription = damageDescription;
        this.price = price;
        this.claimant =claimant;
        this.damageRegistationsDate = damageRegistationsDate;
        this.damageFixedDate = damageFixedDate;
        this.timestamp = timestamp;
    }


    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDamageRegistationsDate() {
        return damageRegistationsDate;
    }

    public void setDamageRegistationsDate(Date damageRegistationsDate) {
        this.damageRegistationsDate = damageRegistationsDate;
    }

    public Date getDamageFixedDate() {
        return damageFixedDate;
    }

    public void setDamageFixedDate(Date damageFixedDate) {
        this.damageFixedDate = damageFixedDate;
    }

    public int getDamageID() {
        return damageID;
    }

    public void setDamageID(int damageID) {
        this.damageID = damageID;
    }


    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getClaimant() {
        return claimant;
    }

    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }


    public Timestamp getTimeStamp() {
            return timestamp;
        }

    @Override
    public String toString() {
        return "Damage{" +
                "dateTool=" + dateTool +
                ", damageID=" + damageID +
                ", carID=" + carID +
                ", damageDescription='" + damageDescription + '\'' +
                ", price=" + price +
                ", claimant='" + claimant + '\'' +
                ", damageRegistationsDate=" + damageRegistationsDate +
                ", damageFixedDate=" + damageFixedDate +
                ", timestamp=" + timestamp +
                '}';
    }
}

