package com.example.bilabonnement.models;

public class Damage {

    private String skadetype;
    private int pris;

    public Damage(String skadetype, int pris) {
        this.skadetype = skadetype;
        this.pris = pris;
    }

    public String getSkadetype() {
        return skadetype;
    }

    public void setSkadetype(String skadetype) {
        this.skadetype = skadetype;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }
}

