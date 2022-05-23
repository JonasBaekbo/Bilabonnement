package com.example.bilabonnement.models;

public class ExtraEquipment {

    private Car car;
    private int ekstraEquipemntID;
    private String ekstraEquipemntDescription;

    public ExtraEquipment(Car car, String ekstraEquipemntDescription) {
        this.car = car;
        this.ekstraEquipemntDescription = ekstraEquipemntDescription;
    }

    public ExtraEquipment(Car car, int ekstraEquipemntID) {
        this.car = car;
        this.ekstraEquipemntID = ekstraEquipemntID;
    }

    public ExtraEquipment(int ekstraEquipemntID, String ekstraEquipemntDescription) {
        this.ekstraEquipemntID = ekstraEquipemntID;
        this.ekstraEquipemntDescription = ekstraEquipemntDescription;
    }

    public Car getCar() {
        return car;
    }

    public int getCarID(){
        return car.getCarID();
    }

    public int getEkstraEquipemntID() {
        return ekstraEquipemntID;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getLicencePlate(){
        return car.getLicencePlate();
    }

    public String getEkstraEquipemntDescription() {
        return ekstraEquipemntDescription;
    }

    public String getVinNumber(){
        return this.car.getVinNumber();
    }

}
