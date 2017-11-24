package com.magomtz.android.cartilla.model;

import android.content.ContentValues;

import com.magomtz.android.cartilla.db.contract.VaacinationCardContract;

import java.util.Date;

/**
 * Created by Mago on 19/11/2017.
 */

public class Vaccine {
    private int vaccineId;
    private String vaccineDate;
    private String vaccineTicket;
    private String vaccineNextAppointment;

    public Vaccine() {
    }

    public Vaccine(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(VaacinationCardContract.VaccineData.VACCINE_DATE, String.valueOf(vaccineDate));
        values.put(VaacinationCardContract.VaccineData.VACCINE_TICKET, vaccineTicket);
        values.put(VaacinationCardContract.VaccineData.VACCINE_NEXT_APPOINTMENT, String.valueOf(vaccineNextAppointment));
        return values;
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(String vaccineDate) {
        this.vaccineDate = vaccineDate;
    }

    public String getVaccineTicket() {
        return vaccineTicket;
    }

    public void setVaccineTicket(String vaccineTicket) {
        this.vaccineTicket = vaccineTicket;
    }

    public String getVaccineNextAppointment() {
        return vaccineNextAppointment;
    }

    public void setVaccineNextAppointment(String vaccineNextAppointment) {
        this.vaccineNextAppointment = vaccineNextAppointment;
    }
}
