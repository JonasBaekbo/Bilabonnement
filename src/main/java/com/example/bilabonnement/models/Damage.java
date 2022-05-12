package com.example.bilabonnement.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Damage {

    private int damageID;
    private int carID;
    private String damageDescription;
    private int price;
    private String damageRapporter;
    private Date damageRegistationsDate;
    private Date damageFixedDate;
    private Timestamp timestamp;

    public Damage(int carID, String damageDescription, int price,String damageRapporter, Date damageRegistationsDate) {
        this.carID=carID;
        this.damageDescription = damageDescription;
        this.price = price;
        this.damageRapporter=damageRapporter;
        this.damageRegistationsDate = damageRegistationsDate;
        this.damageFixedDate = null;
        this.timestamp = getTimeStamp();
    }

    public Damage(int damageID, int carID, String damageDescription, int price, String damageRapporter,Date damageRegistationsDate, Date damageFixedDate, Timestamp timestamp) {
        this.damageID = damageID;
        this.carID=carID;
        this.damageDescription = damageDescription;
        this.price = price;
        this.damageRapporter=damageRapporter;
        this.damageRegistationsDate = damageRegistationsDate;
        this.damageFixedDate = damageFixedDate;
        this.timestamp = timestamp;
    }


    public Timestamp getTimeStamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }

    public java.sql.Date getUtilDateAsSQL(java.util.Date utilDate) {
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }


    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public Timestamp getTimestamp() {
        return timestamp;
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

    public String getDamageRapporter() {
        return damageRapporter;
    }

    public void setDamageRapporter(String damageRapporter) {
        this.damageRapporter = damageRapporter;
    }
}

