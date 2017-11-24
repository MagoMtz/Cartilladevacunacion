package com.magomtz.android.cartilla.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.magomtz.android.cartilla.db.contract.VaacinationCardContract.OwnerData;
import com.magomtz.android.cartilla.db.contract.VaacinationCardContract.PetData;
import com.magomtz.android.cartilla.db.contract.VaacinationCardContract.VaccineData;
import com.magomtz.android.cartilla.model.Owner;
import com.magomtz.android.cartilla.model.Pet;
import com.magomtz.android.cartilla.model.Vaccine;

/**
 * Created by Mago on 19/11/2017.
 */

public class SQLiteVaccinationCard extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "VaccinationCard.db";
    private String querySQLite;


    public SQLiteVaccinationCard(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //OwnerTable
        querySQLite = "CREATE TABLE " + OwnerData.TABLE_NAME + " (" +
                OwnerData.OWNER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                OwnerData.OWNER_LAST_NAME + " TEXT, " +
                OwnerData.OWNER_FIRST_NAME + " TEXT, " +
                OwnerData.OWNER_ADDRESS + " TEXT, " +
                OwnerData.OWNER_PHONE + " TEXT," +
                "UNIQUE (" + OwnerData.OWNER_ID + "))";
        db.execSQL(querySQLite);

        //VaccineTable
        querySQLite = "CREATE TABLE " + VaccineData.TABLE_NAME + " (" +
                VaccineData.VACCINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VaccineData.VACCINE_DATE + " TEXT NOT NULL, " +
                VaccineData.VACCINE_TICKET + " TEXT, " +
                VaccineData.VACCINE_NEXT_APPOINTMENT + " TEXT," +
                PetData.PET_ID + " INTEGER," +
                "FOREIGN KEY (" + PetData.PET_ID + ") REFERENCES " + PetData.TABLE_NAME + "(" + PetData.PET_ID + ")," +
                "UNIQUE (" + VaccineData.VACCINE_ID + "))";
        db.execSQL(querySQLite);

        //PetTable
        querySQLite = "CREATE TABLE " + PetData.TABLE_NAME + " (" +
                PetData.PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PetData.PET_KIND + " TEXT NOT NULL, " +
                PetData.PET_SEX + " TEXT NOT NULL, " +
                PetData.PET_NAME + " TEXT NOT NULL, " +
                PetData.PET_BIRTHDAY + " TEXT, " +
                PetData.PET_BREED + " TEXT, " +
                PetData.PET_COLOR + " TEXT, " +
                PetData.PET_ID_NUMBER + " TEXT, " +
                PetData.PET_PARTICULAR_SIGNS + " TEXT, " +
                PetData.PET_MICRO_CHIP + " TEXT, " +
                PetData.PET_TATTOO + " TEXT, " +
                OwnerData.OWNER_ID + " INTEGER, " +
                VaccineData.VACCINE_ID + " INTEGER," +
                "FOREIGN KEY(" + OwnerData.OWNER_ID + ") REFERENCES "+ OwnerData.TABLE_NAME + "(" +OwnerData.OWNER_ID + "), " +
                //"FOREIGN KEY(" + VaccineData.VACCINE_ID + ") REFERENCES " + VaccineData.TABLE_NAME + "(" +VaccineData.VACCINE_ID +")," +
                "UNIQUE (" + PetData.PET_ID+"))";
        db.execSQL(querySQLite);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    public void registerPet(Pet pet){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(PetData.TABLE_NAME, null, pet.toContentValues());
        db.close();
    }

    /**
     * Opens a cursor that returns all pets in data base
     * note: do not forgot to close
     * @return Cursor type - all pets
     */
    public Cursor getAllPets(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(PetData.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }

    public Cursor getPetById(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(PetData.TABLE_NAME,
                null,
                PetData.PET_ID,
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        return cursor;
    }

    public void registerVaccine(Vaccine vaccine){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(VaccineData.TABLE_NAME, null, vaccine.toContentValues());
        db.close();
    }

    public Cursor getAllVaccines(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(VaccineData.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }

    public Cursor getVaccineById(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(VaccineData.TABLE_NAME,
                null,
                VaccineData.VACCINE_ID + " LIKE ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        return cursor;
    }

    public void registerOwner(Owner owner){
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(OwnerData.TABLE_NAME, null, owner.toContentValues());
        Log.d("SQLITE", "owner registered with id "+id);
        db.close();
    }

    public Cursor getAllOwners(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(OwnerData.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }

    public Cursor getOwnerById(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(OwnerData.TABLE_NAME,
                null,
                OwnerData.OWNER_ID,
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        return cursor;
    }

}
