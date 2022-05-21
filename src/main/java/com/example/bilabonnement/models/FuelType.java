package com.example.bilabonnement.models;

public class FuelType {
    private int fuelTypeID;
    private String fuelTypeName;

    public FuelType(int fuelTypeID, String fuelTypeName) {
        this.fuelTypeID = fuelTypeID;
        this.fuelTypeName = fuelTypeName;
    }

    public int getFuelTypeID() {
        return fuelTypeID;
    }

    public void setFuelTypeID(int fuelTypeID) {
        this.fuelTypeID = fuelTypeID;
    }

    public String getFuelTypeName() {
        return fuelTypeName;
    }

    public void setFuelTypeName(String fuelTypeName) {
        this.fuelTypeName = fuelTypeName;
    }
}
