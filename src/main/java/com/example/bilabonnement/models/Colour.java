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

    public void setColourID(int colourID) {
        this.colourID = colourID;
    }

    public String getColourName() {
        return colourName;
    }

    public void setColourName(String colourName) {
        this.colourName = colourName;
    }
}
