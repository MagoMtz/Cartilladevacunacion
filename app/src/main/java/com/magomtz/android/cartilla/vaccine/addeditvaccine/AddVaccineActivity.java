package com.magomtz.android.cartilla.vaccine.addeditvaccine;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.magomtz.android.cartilla.R;

public class AddVaccineActivity extends AppCompatActivity implements AddVaccineFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);

        Bundle extras = getIntent().getExtras();
        int petId = extras.getInt("petId");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.add_vaccine_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.add_vaccine_fragment, AddVaccineFragment.newInstance(petId));
        ft.commit();


    }

    @Override
    public void updateTicket(String path) {
        Toast.makeText(getApplicationContext(), "path "+path, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addVaccine() {
        Toast.makeText(getApplicationContext(), "addVaccine()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
