package com.magomtz.android.cartilla.petlist.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.magomtz.android.cartilla.R;
import com.magomtz.android.cartilla.db.SQLiteVaccinationCard;
import com.magomtz.android.cartilla.db.contract.VaacinationCardContract;
import com.magomtz.android.cartilla.model.Owner;
import com.magomtz.android.cartilla.model.Pet;
import com.magomtz.android.cartilla.model.Vaccine;
import com.magomtz.android.cartilla.pet.AddEditPetActivity;
import com.magomtz.android.cartilla.vaccine.PetInfoActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class PetListActivity extends AppCompatActivity implements PetListFragmentListener {
    public static ArrayList<Pet> arrayListPet;
    public static HashMap<Integer, Owner> ownerMap;
    public static HashMap<Integer, Vaccine> vaccineMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        arrayListPet = new ArrayList<>();
        ownerMap = new HashMap<>();
        vaccineMap = new HashMap<>();


        //populateArrayListPet();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.pet_list_activity_tittle));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, PetListFragment.newInstance());
        ft.commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPet();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //if(arrayListPet!=null)
        populateArrayListPet();
        populateOwnerMap();
        populateVaccineMap();
        PetListFragment.adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemListClick(int position) {
        navigateToPetInfoActivity(position);
    }

    private void navigateToPetInfoActivity(int position) {
        int ownerId = arrayListPet.get(position).getOwnerId();

        Intent intent = new Intent(getApplicationContext(), PetInfoActivity.class);
        intent.putExtra("petPosition", position);
        intent.putExtra("ownerId", ownerId);
        startActivity(intent);
    }

    private void addPet(){
        Intent intent = new Intent(getApplicationContext(), AddEditPetActivity.class);
        startActivity(intent);
    }

    private void populateArrayListPet(){
        SQLiteVaccinationCard petDB = new SQLiteVaccinationCard(getApplicationContext());
        Cursor cursor = petDB.getAllPets();
        arrayListPet.clear();
        while (cursor.moveToNext()){
            Pet pet = new Pet();
            pet.setPetId(cursor.getInt(cursor.getColumnIndex(VaacinationCardContract.PetData.PET_ID)));
            pet.setPetKind(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.PetData.PET_KIND)));
            pet.setPetSex(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.PetData.PET_SEX)));
            pet.setPetName(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.PetData.PET_NAME)));
            pet.setPetBirthday(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.PetData.PET_BIRTHDAY)));
            pet.setPetBreed(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.PetData.PET_BREED)));
            pet.setPetColor(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.PetData.PET_COLOR)));
            pet.setPetIdNumber(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.PetData.PET_ID_NUMBER)));
            pet.setPetParticularSigns(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.PetData.PET_PARTICULAR_SIGNS)));
            pet.setPetMicroChip(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.PetData.PET_MICRO_CHIP)));
            pet.setPetTattoo(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.PetData.PET_TATTOO)));
            pet.setOwnerId(cursor.getInt(cursor.getColumnIndex(VaacinationCardContract.OwnerData.OWNER_ID)));
            pet.setVaccineId(cursor.getInt(cursor.getColumnIndex(VaacinationCardContract.VaccineData.VACCINE_ID)));
            arrayListPet.add(pet);
        }
        petDB.close();
        cursor.close();
    }

    private void populateOwnerMap(){
        SQLiteVaccinationCard ownerDB = new SQLiteVaccinationCard(getApplicationContext());
        Cursor cursor = ownerDB.getAllOwners();
        ownerMap.clear();
        while (cursor.moveToNext()){
            Owner owner = new Owner();
            owner.setOwnerId(cursor.getInt(cursor.getColumnIndex(VaacinationCardContract.OwnerData.OWNER_ID)));
            owner.setOwnerFirstName(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.OwnerData.OWNER_FIRST_NAME)));
            owner.setOwnerLastName(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.OwnerData.OWNER_LAST_NAME)));
            owner.setOwnerAddres(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.OwnerData.OWNER_ADDRESS)));
            owner.setOwnerPhone(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.OwnerData.OWNER_PHONE)));
            ownerMap.put(cursor.getInt(cursor.getColumnIndex(VaacinationCardContract.OwnerData.OWNER_ID)), owner);
        }
        ownerDB.close();
        cursor.close();
    }

    private void populateVaccineMap(){
        SQLiteVaccinationCard vaccineDB = new SQLiteVaccinationCard(getApplicationContext());
        Cursor cursor = vaccineDB.getAllVaccines();
        vaccineMap.clear();
        while (cursor.moveToNext()){
            Vaccine vaccine = new Vaccine();
            vaccine.setVaccineId(cursor.getInt(cursor.getColumnIndex(VaacinationCardContract.VaccineData.VACCINE_ID)));
            vaccine.setVaccineDate(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.VaccineData.VACCINE_DATE)));
            vaccine.setVaccineTicket(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.VaccineData.VACCINE_TICKET)));
            vaccine.setVaccineNextAppointment(cursor.getString(cursor.getColumnIndex(VaacinationCardContract.VaccineData.VACCINE_NEXT_APPOINTMENT)));
            vaccineMap.put(vaccine.getVaccineId(), vaccine);
        }
        vaccineDB.close();
        cursor.close();
    }

}
