package com.example.bilabonnement.models;

public class StatisticsItem {
    private String status;
    private int numberOfCars;

    public StatisticsItem(String status, int numberOfCars) {
        this.status = status;
        this.numberOfCars = numberOfCars;
    }

    public String getStatus() {
        return status;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }
}
