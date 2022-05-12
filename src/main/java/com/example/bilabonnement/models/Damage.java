package com.example.bilabonnement.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Damage {

    private int damageID;
    private String damageDescription;
    private int price;
    private Date damageRegistationsDate;
    private Date damageFixedDate;
    private Timestamp timestamp;

    public Damage(String damageDescription, int price, Date damageRegistationsDate) {
        this.damageDescription = damageDescription;
        this.price = price;
        this.damageRegistationsDate = damageRegistationsDate;
        this.damageFixedDate = null;
        this.timestamp = getTimeStamp();
    }

    public Damage(int damageID, String damageDescription, int price, Date damageRegistationsDate, Date damageFixedDate, Timestamp timestamp) {
        this.damageID = damageID;
        this.damageDescription = damageDescription;
        this.price = price;
        this.damageRegistationsDate = damageRegistationsDate;
        this.damageFixedDate = damageFixedDate;
        this.timestamp = timestamp;
    }

    private Timestamp getTimeStamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
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
}

