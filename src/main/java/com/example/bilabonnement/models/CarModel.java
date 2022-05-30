//Johanne
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

    public String getModelName() {
        return modelName;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
