//Johanne
package com.example.bilabonnement.models;

public class CarStatus {

    private int carStatusId;
    private String carStatusName;

    public CarStatus(int carStatusId, String carStatusName) {
        this.carStatusId = carStatusId;
        this.carStatusName = carStatusName;
    }

    public int getCarStatusId() {
        return carStatusId;
    }

    public String getCarStatusName() {
        return carStatusName;
    }

}
