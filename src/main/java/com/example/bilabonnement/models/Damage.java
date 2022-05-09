package com.example.bilabonnement.models;

import java.sql.Date;

public class Damage {

    private String damageDescription;
    private int price;
    private Date damageRegistationsDate;
    private Date damageFixedDate;

    public Damage(String damageDescription, int price,Date damageRegistationsDate) {
        this.damageDescription = damageDescription;
        this.price = price;
        this.damageRegistationsDate=damageRegistationsDate;
        this.damageFixedDate=null;
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
}

