package com.magomtz.android.cartilla.model;

import android.content.ContentValues;

import com.magomtz.android.cartilla.db.contract.VaacinationCardContract;

/**
 * Created by Mago on 19/11/2017.
 */

public class Owner {
    private int ownerId;
    private String ownerLastName;
    private String ownerFirstName;
    private String ownerAddress;
    private String ownerPhone;

    public Owner() {
    }

    public Owner(int ownerId) {
        this.ownerId = ownerId;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(VaacinationCardContract.OwnerData.OWNER_LAST_NAME, ownerLastName);
        values.put(VaacinationCardContract.OwnerData.OWNER_FIRST_NAME, ownerFirstName);
        values.put(VaacinationCardContract.OwnerData.OWNER_ADDRESS, ownerAddress);
        values.put(VaacinationCardContract.OwnerData.OWNER_PHONE, ownerPhone);
        return values;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerAddres() {
        return ownerAddress;
    }

    public void setOwnerAddres(String ownerAddres) {
        this.ownerAddress = ownerAddres;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
}
