package com.example.bilabonnement.models;

import java.util.ArrayList;

public class ekstraEquipemnt {

    private Car car;
    private ArrayList<String> ekstraEquipemntdescription;

    public ekstraEquipemnt (Car car, ArrayList<String> ekstraEquipemntdescription){
        this.car = car;
        this.ekstraEquipemntdescription = ekstraEquipemntdescription;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getNumberPlate(){
        return car.getNumberPlate();
    }

    public ArrayList<String> getEkstraEquipemntdescription() {
        return ekstraEquipemntdescription;
    }

    public void setEkstraEquipemntdescription(ArrayList<String> ekstraEquipemntdescription) {
        this.ekstraEquipemntdescription = ekstraEquipemntdescription;
    }
}
