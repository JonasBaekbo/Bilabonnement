package com.example.bilabonnement.models;

public class ExtraEquipment {

    private Car car;
    private int ekstraEquipemntdescriptionID;
    private String ekstraEquipemntdescription;

    public ExtraEquipment(Car car, String ekstraEquipemntdescription) {
        this.car = car;
        this.ekstraEquipemntdescription = ekstraEquipemntdescription;
    }

    public ExtraEquipment(Car car, int ekstraEquipemntdescriptionID) {
        this.car = car;
        this.ekstraEquipemntdescriptionID = ekstraEquipemntdescriptionID;
    }

    public ExtraEquipment(int ekstraEquipemntdescriptionID, String ekstraEquipemntdescription) {
        this.ekstraEquipemntdescriptionID=ekstraEquipemntdescriptionID;
        this.ekstraEquipemntdescription = ekstraEquipemntdescription;
    }

    public Car getCar() {
        return car;
    }

    public int getCarID(){
        return car.getCarID();
    }

    public int getEkstraEquipemntdescriptionID() {
        return ekstraEquipemntdescriptionID;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getLicencePlate(){
        return car.getLicencePlate();
    }

    public String getEkstraEquipemntdescription() {
        return ekstraEquipemntdescription;
    }

    public void setEkstraEquipemntdescription(String ekstraEquipemntdescription) {
        this.ekstraEquipemntdescription = ekstraEquipemntdescription;
    }
}
