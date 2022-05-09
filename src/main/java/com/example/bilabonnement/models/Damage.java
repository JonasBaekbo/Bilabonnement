package com.example.bilabonnement.models;

public class Damage {

    private String damageDescription;
    private int price;

    public Damage(String damageDescription, int price) {
        this.damageDescription = damageDescription;
        this.price = price;
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
}

