package com.magomtz.android.cartilla.model;

import android.content.ContentValues;

import com.magomtz.android.cartilla.db.contract.VaacinationCardContract;

/**
 * Created by Mago on 19/11/2017.
 */

public class Pet {
    private int petId;
    private String petKind;
    private String petSex;
    private String petName;
    private String petBirthday;
    private String petBreed;
    private String petColor;
    private String petIdNumber;
    private String petParticularSigns;
    private String petMicroChip;
    private String petTattoo;
    private int ownerId;
    private int vaccineId;

    public Pet() {
    }

    public Pet(int petId) {
        this.petId = petId;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(VaacinationCardContract.PetData.PET_KIND, petKind);
        values.put(VaacinationCardContract.PetData.PET_SEX, String.valueOf(petSex));
        values.put(VaacinationCardContract.PetData.PET_NAME, petName);
        values.put(VaacinationCardContract.PetData.PET_BIRTHDAY, String.valueOf(petBirthday));
        values.put(VaacinationCardContract.PetData.PET_BREED, petBreed);
        values.put(VaacinationCardContract.PetData.PET_COLOR, petColor);
        values.put(VaacinationCardContract.PetData.PET_ID_NUMBER, petIdNumber);
        values.put(VaacinationCardContract.PetData.PET_PARTICULAR_SIGNS, petParticularSigns);
        values.put(VaacinationCardContract.PetData.PET_MICRO_CHIP, petMicroChip);
        values.put(VaacinationCardContract.PetData.PET_TATTOO, petTattoo);
        values.put(VaacinationCardContract.OwnerData.OWNER_ID, ownerId);
        values.put(VaacinationCardContract.VaccineData.VACCINE_ID, vaccineId);
        return values;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetKind() {
        return petKind;
    }

    public void setPetKind(String petKind) {
        this.petKind = petKind;
    }

    public String getPetSex() {
        return petSex;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetBirthday() {
        return petBirthday;
    }

    public void setPetBirthday(String petBirthday) {
        this.petBirthday = petBirthday;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetColor() {
        return petColor;
    }

    public void setPetColor(String petColor) {
        this.petColor = petColor;
    }

    public String getPetIdNumber() {
        return petIdNumber;
    }

    public void setPetIdNumber(String petIdNumber) {
        this.petIdNumber = petIdNumber;
    }

    public String getPetParticularSigns() {
        return petParticularSigns;
    }

    public void setPetParticularSigns(String petParticularSigns) {
        this.petParticularSigns = petParticularSigns;
    }

    public String getPetMicroChip() {
        return petMicroChip;
    }

    public void setPetMicroChip(String petMicroChip) {
        this.petMicroChip = petMicroChip;
    }

    public String getPetTattoo() {
        return petTattoo;
    }

    public void setPetTattoo(String petTattoo) {
        this.petTattoo = petTattoo;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }
}
