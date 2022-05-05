package com.example.bilabonnement.functions;

import java.util.Date;

public class Bil {

    String modelnavn;
    String biltype;
    String udlejet;
    int registreringsafgift;
    Date udlejningsperiode;
    String skader;
    String nummerplade;
    int stelnummer;
    String farve;

    public Bil(String modelnavn, String biltype, String udlejet, int registreringsafgift, Date udlejningsperiode, String skader, String nummerplade, int stelnummer, String farve) {
        this.modelnavn = modelnavn;
        this.biltype = biltype;
        this.udlejet = udlejet;
        this.registreringsafgift = registreringsafgift;
        this.udlejningsperiode = udlejningsperiode;
        this.skader = skader;
        this.nummerplade = nummerplade;
        this.stelnummer = stelnummer;
        this.farve = farve;
    }

    public String getModelnavn() {
        return modelnavn;
    }

    public void setModelnavn(String modelnavn) {
        this.modelnavn = modelnavn;
    }

    public String getBiltype() {
        return biltype;
    }

    public void setBiltype(String biltype) {
        this.biltype = biltype;
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
}
