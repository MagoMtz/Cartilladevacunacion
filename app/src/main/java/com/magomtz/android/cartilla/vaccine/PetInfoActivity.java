package com.magomtz.android.cartilla.vaccine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.magomtz.android.cartilla.R;
import com.magomtz.android.cartilla.petlist.ui.PetListActivity;
import com.magomtz.android.cartilla.vaccine.addeditvaccine.AddVaccineActivity;
import com.magomtz.android.cartilla.vaccine.petinfo.PetInfoFragment;
import com.magomtz.android.cartilla.vaccine.adapter.SectionsPagerAdapter;
import com.magomtz.android.cartilla.vaccine.vaccineinfo.VaccineFragmentListener;
import com.magomtz.android.cartilla.vaccine.vaccineinfo.VaccineInfoFragment;

public class PetInfoActivity extends AppCompatActivity implements VaccineFragmentListener{
    private ViewPager viewPager;
    private int petId;
    private int ownerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);

        Bundle bundle = getIntent().getExtras();
        petId = bundle.getInt("petPosition");
        ownerId = bundle.getInt("ownerId");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.pet_info_activity_title, PetListActivity.arrayListPet.get(petId).getPetName()));

        viewPager = (ViewPager)findViewById(R.id.pager);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(PetInfoFragment.newInstance(petId, ownerId), getString(R.string.title_pet_section));
        adapter.addFragment(VaccineInfoFragment.newInstance(petId, ownerId), getString(R.string.title_vaccine_section));
        viewPager.setAdapter(adapter);

        TabLayout tabs = (TabLayout)findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void newVaccine(int petId) {
        Intent intent = new Intent(getApplicationContext(), AddVaccineActivity.class);
        intent.putExtra("petId", petId);
        startActivity(intent);
    }

    @Override
    public void updateTicket(String path) {
        Toast.makeText(getApplicationContext(), "update path: "+path, Toast.LENGTH_SHORT).show();
    }
}
