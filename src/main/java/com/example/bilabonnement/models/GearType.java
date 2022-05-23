package com.example.bilabonnement.models;

public class GearType {
    private int gearTypeID;
    private String gearTypeName;

    public GearType(int gearTypeID, String gearTypeName) {
        this.gearTypeID = gearTypeID;
        this.gearTypeName = gearTypeName;
    }

    public int getGearTypeID() {
        return gearTypeID;
    }

    public String getGearTypeName() {
        return gearTypeName;
    }
}
