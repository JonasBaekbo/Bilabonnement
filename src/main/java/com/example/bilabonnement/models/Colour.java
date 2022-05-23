package com.example.bilabonnement.models;

public class Colour {
    private int colourID;
    private String colourName;

    public Colour(int colourID, String colourName) {
        this.colourID = colourID;
        this.colourName = colourName;
    }

    public int getColourID() {
        return colourID;
    }

    public String getColourName() {
        return colourName;
    }
}