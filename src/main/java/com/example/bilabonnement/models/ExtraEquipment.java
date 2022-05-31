//Mikkel, Johanne

package com.example.bilabonnement.models;

public class ExtraEquipment {

    private Car car;
    private int extraEquipmentID;
    private String extraEquipmentDescription;

    public ExtraEquipment(Car car, String extraEquipmentDescription) {
        this.car = car;
        this.extraEquipmentDescription = extraEquipmentDescription;
    }

    public ExtraEquipment(Car car, int extraEquipmentID) {
        this.car = car;
        this.extraEquipmentID = extraEquipmentID;
    }

    public ExtraEquipment(int extraEquipmentID, String extraEquipmentDescription) {
        this.extraEquipmentID = extraEquipmentID;
        this.extraEquipmentDescription = extraEquipmentDescription;
    }

    public Car getCar() {
        return car;
    }

    public int getCarID() {
        return car.getCarID();
    }

    public int getExtraEquipmentID() {
        return extraEquipmentID;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getLicencePlate() {
        return car.getLicencePlate();
    }

    public String getExtraEquipmentDescription() {
        return extraEquipmentDescription;
    }

    public String getVinNumber() {
        return this.car.getVinNumber();
    }

}
