package com.example.bilabonnement.models;

import java.util.Date;

public class Leasing {

    private String kundeid;
    private Date startdato;
    private Date slutdato;
    private int inkluderetkilometer;
    private int bilID;

    public Leasing(String kundeid, Date startdato, Date slutdato, int inkluderetkilometer, int bilID) {
        this.kundeid = kundeid;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.inkluderetkilometer = inkluderetkilometer;
        this.bilID = bilID;
    }

    public String getKundeid() {
        return kundeid;
    }

    public void setKundeid(String kundeid) {
        this.kundeid = kundeid;
    }

    public Date getStartdato() {
        return startdato;
    }

    public void setStartdato(Date startdato) {
        this.startdato = startdato;
    }

    public Date getSlutdato() {
        return slutdato;
    }

    public void setSlutdato(Date slutdato) {
        this.slutdato = slutdato;
    }

    public int getInkluderetkilometer() {
        return inkluderetkilometer;
    }

    public void setInkluderetkilometer(int inkluderetkilometer) {
        this.inkluderetkilometer = inkluderetkilometer;
    }

    public int getBilID() {
        return bilID;
    }

    public void setBilID(int bilID) {
        this.bilID = bilID;
    }

}
