package com.example.bilabonnement.functions;

import com.example.bilabonnement.servises.FuelType;

import java.util.Date;

public class Bil {

    private String modelnavn;
    private Enum brændstoftype;
    private String udlejet;
    private int registreringsafgift;
    private Date udlejningsperiode;
    private String skader;
    private String nummerplade;
    private int stelnummer;
    private String farve;
    private String geartype;


    public Bil(String modelnavn, Enum brændstoftype, String udlejet, int registreringsafgift, Date udlejningsperiode, String skader, String nummerplade, int stelnummer, String farve, String geartype) {
        this.modelnavn = modelnavn;
        this.brændstoftype = brændstoftype;
        this.udlejet = udlejet;
        this.registreringsafgift = registreringsafgift;
        this.udlejningsperiode = udlejningsperiode;
        this.skader = skader;
        this.nummerplade = nummerplade;
        this.stelnummer = stelnummer;
        this.farve = farve;
        this.geartype = geartype;
    }

    public String getModelnavn() {
        return modelnavn;
    }

    public void setModelnavn(String modelnavn) {
        this.modelnavn = modelnavn;
    }

    public Enum<FuelType> getBrændstoftype() {
        return brændstoftype;
    }

    public void setBrændstoftype(Enum<FuelType> brændstoftype) {
        this.brændstoftype = brændstoftype;
    }

    public String getUdlejet() {
        return udlejet;
    }

    public void setUdlejet(String udlejet) {
        this.udlejet = udlejet;
    }

    public int getRegistreringsafgift() {
        return registreringsafgift;
    }

    public void setRegistreringsafgift(int registreringsafgift) {
        this.registreringsafgift = registreringsafgift;
    }

    public Date getUdlejningsperiode() {
        return udlejningsperiode;
    }

    public void setUdlejningsperiode(Date udlejningsperiode) {
        this.udlejningsperiode = udlejningsperiode;
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


}
