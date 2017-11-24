package com.magomtz.android.cartilla.pet;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.magomtz.android.cartilla.R;

public class AddEditPetActivity extends AppCompatActivity implements AddEditPetListener{
    private int petId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_pet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.add_pet_activity_tittle));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = new Bundle();
        petId = bundle.getInt("petId");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(petId!=0){
            getSupportActionBar().setTitle(getString(R.string.edit_pet_activity_tittle));
            ft.replace(R.id.add_edit_pet_fragment, AddEditPetFragment.newInstance(AddEditPetFragment.EDIT_PET));
        }else{
            ft.replace(R.id.add_edit_pet_fragment, AddEditPetFragment.newInstance(AddEditPetFragment.ADD_PET));
        }
        ft.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void addPet() {
        Toast.makeText(getApplicationContext(), getString(R.string.add_edit_pet_succesfull), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void editPet(int id) {

    }
}
