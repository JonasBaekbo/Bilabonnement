package com.example.bilabonnement.models;

public class CarModel {
    private int modelID;
    private String modelName;
    private String manufacturer;

    public CarModel(int modelID, String modelName, String manufacturer) {
        this.modelID = modelID;
        this.modelName = modelName;
        this.manufacturer = manufacturer;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
