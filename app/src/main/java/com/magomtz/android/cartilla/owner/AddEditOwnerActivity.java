package com.magomtz.android.cartilla.owner;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.magomtz.android.cartilla.R;
import com.magomtz.android.cartilla.pet.AddEditPetFragment;

public class AddEditOwnerActivity extends AppCompatActivity implements AddEditOwnerListener {
    private int ownerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_owner);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.add_owner_activity_tittle));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = new Bundle();
        ownerId = bundle.getInt("ownerId");


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(ownerId!=0){
            getSupportActionBar().setTitle(getString(R.string.edit_owner_activity_tittle));
            ft.replace(R.id.add_edit_owner_fragment, AddEditOwnerFragment.newInstance(AddEditPetFragment.EDIT_PET));
        }else{
            ft.replace(R.id.add_edit_owner_fragment, AddEditOwnerFragment.newInstance(AddEditPetFragment.ADD_PET));
        }
        ft.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void addOwner() {
        Toast.makeText(getApplicationContext(), getString(R.string.add_edit_owner_succesfull), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void editOwner(int id) {

    }
}
