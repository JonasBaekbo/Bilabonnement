package com.example.bilabonnement.models;

import java.util.Date;

public class Car {
    private int bilID;
    private int stelnummer;
    private String nummerplade;
    private String modelnavn;
    private Enum brændstoftype;
    private String farve;
    private String geartype;
    private String status;
    private int registreringsafgift;
    private Date udlejetFra;
    private Date udlejetTil;
    private String skader;

    public Car(int bilID, int stelnummer, String nummerplade, String modelnavn, Enum brændstoftype, String farve, String geartype, String status, int registreringsafgift, Date udlejetFra, Date udlejetTil, String skader) {
        this.bilID = bilID;
        this.stelnummer = stelnummer;
        this.nummerplade = nummerplade;
        this.modelnavn = modelnavn;
        this.brændstoftype = brændstoftype;
        this.farve = farve;
        this.geartype = geartype;
        this.status = status;
        this.registreringsafgift = registreringsafgift;
        this.udlejetFra = udlejetFra;
        this.udlejetTil = udlejetTil;
        this.skader = skader;
    }

    public Car(int stelnummer, String nummerplade, String modelnavn, Enum brændstoftype, String farve, String geartype, String status, int registreringsafgift, Date udlejetFra, Date udlejetTil, String skader) {
        this.stelnummer = stelnummer;
        this.nummerplade = nummerplade;
        this.modelnavn = modelnavn;
        this.brændstoftype = brændstoftype;
        this.farve = farve;
        this.geartype = geartype;
        this.status = status;
        this.registreringsafgift = registreringsafgift;
        this.udlejetFra = udlejetFra;
        this.udlejetTil = udlejetTil;
        this.skader = skader;
    }

    public String getModelnavn() {
        return modelnavn;
    }

    public void setModelnavn(String modelnavn) {
        this.modelnavn = modelnavn;
    }

    public Enum getBrændstoftype() {
        return brændstoftype;
    }


    public int getRegistreringsafgift() {
        return registreringsafgift;
    }

    public void setRegistreringsafgift(int registreringsafgift) {
        this.registreringsafgift = registreringsafgift;
    }


    public String getSkader() {
        return skader;
    }

    public void setSkader(String skader) {
        this.skader = skader;
    }

    public String getNummerplade() {
        return nummerplade;
    }

    public void setNummerplade(String nummerplade) {
        this.nummerplade = nummerplade;
    }

    public int getStelnummer() {
        return stelnummer;
    }

    public void setStelnummer(int stelnummer) {
        this.stelnummer = stelnummer;
    }

    public String getFarve() {
        return farve;
    }

    public void setFarve(String farve) {
        this.farve = farve;
    }

    public String getGeartype() {
        return geartype;
    }

    public void setGeartype(String geartype) {
        this.geartype = geartype;
    }

    public int getBilID() {
        return bilID;
    }

    public void setBilID(int bilID) {
        this.bilID = bilID;
    }

    public void setBrændstoftype(Enum brændstoftype) {
        this.brændstoftype = brændstoftype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getUdlejetFra() {
        return udlejetFra;
    }

    public void setUdlejetFra(Date udlejetFra) {
        this.udlejetFra = udlejetFra;
    }

    public Date getUdlejetTil() {
        return udlejetTil;
    }

    public void setUdlejetTil(Date udlejetTil) {
        this.udlejetTil = udlejetTil;
    }
}
