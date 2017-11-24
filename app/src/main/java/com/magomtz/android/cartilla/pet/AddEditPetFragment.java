package com.magomtz.android.cartilla.pet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.magomtz.android.cartilla.owner.AddEditOwnerActivity;
import com.magomtz.android.cartilla.R;
import com.magomtz.android.cartilla.db.SQLiteVaccinationCard;
import com.magomtz.android.cartilla.db.contract.VaacinationCardContract;
import com.magomtz.android.cartilla.model.Pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AddEditPetFragment extends Fragment{
    private static final String ARG_ADD_EDIT = "add_edit";
    public static final int ADD_PET = 0;
    public static final int EDIT_PET = 1;
    private int argAddEdit;

    private HashMap<String, Integer> mapOwners;


    private TextInputLayout petNameLayout;
    private TextInputEditText txtPetName;
    private TextInputLayout petKindLayout;
    private TextInputEditText txtPetKind;
    private Spinner spPetSex;
    private CalendarView petBirthdayCalendar;
    private TextInputLayout petBreedLayout;
    private TextInputEditText txtPetBreed;
    private TextInputLayout petColorLayout;
    private TextInputEditText txtPetColor;
    private TextInputLayout petIdNumberLayout;
    private TextInputEditText txtPetIdNumber;
    private TextInputLayout petParticularSignsLayout;
    private TextInputEditText txtPetParticularSigns;
    private CheckBox chkPetMicroChip;
    private CheckBox chkPetTattoo;
    private Spinner petOwner;
    private Button btnRegister;

    private int petId;
    private String petName;
    private String petKind;
    private String petSex;
    private String petColor;
    private String petIdNumber;
    private String petParticularSigns;
    private String petMicroChip;
    private String petTatttoo;
    private String petBirthDay;
    private String petBreed;
    private int ownerId;

    private AddEditPetListener mListener;

    public AddEditPetFragment() {
        // Required empty public constructor
    }

    public static AddEditPetFragment newInstance(int addEdit) {
        AddEditPetFragment fragment = new AddEditPetFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ADD_EDIT, addEdit);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            argAddEdit = getArguments().getInt(ARG_ADD_EDIT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mapOwners = new HashMap<>();


        View view =inflater.inflate(R.layout.fragment_add_edit_pet, container, false);

        petNameLayout = (TextInputLayout)view.findViewById(R.id.txt_pet_name_layout);
        txtPetName = (TextInputEditText)view.findViewById(R.id.txt_pet_name);
        petKindLayout= (TextInputLayout) view.findViewById(R.id.txt_pet_kind_layout);
        txtPetKind = (TextInputEditText)view.findViewById(R.id.txt_pet_kind);
        spPetSex = (Spinner) view.findViewById(R.id.pet_sex);
        petBirthdayCalendar = (CalendarView) view.findViewById(R.id.pet_birthday);
        petBreedLayout = (TextInputLayout) view.findViewById(R.id.txt_pet_breed_layout);
        txtPetBreed = (TextInputEditText) view.findViewById(R.id.txt_pet_breed);
        petColorLayout = (TextInputLayout) view.findViewById(R.id.txt_pet_color_layout);
        txtPetColor = (TextInputEditText) view.findViewById(R.id.txt_pet_color);
        petParticularSignsLayout = (TextInputLayout) view.findViewById(R.id.txt_pet_particular_signs_layout);
        txtPetParticularSigns = (TextInputEditText) view.findViewById(R.id.txt_pet_particular_signs);
        chkPetMicroChip = (CheckBox) view.findViewById(R.id.pet_micro_chip);
        chkPetTattoo = (CheckBox) view.findViewById(R.id.pet_tattoo);
        txtPetIdNumber = (TextInputEditText)view.findViewById(R.id.txt_pet_id_number);
        petOwner = (Spinner) view.findViewById(R.id.pet_owner);
        btnRegister = (Button)view.findViewById(R.id.btn_register_pet);

        petBirthdayCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                petBirthDay = dayOfMonth+"-"+month+"-"+year;
            }
        });

        initializeSpinners();
        initializeView(argAddEdit);
        addOwnerListener();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddEditPetListener) {
            mListener = (AddEditPetListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        initializeSpinners();
    }

    private void initializeSpinners(){
        ArrayAdapter<CharSequence> sexAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.sex, android.R.layout.simple_spinner_item);
        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPetSex.setAdapter(sexAdapter);

        List<String> ownerArray = new ArrayList<>();
        makeOwnerList(ownerArray);
        ownerArray.add(getString(R.string.add_edit_pet_add_owner));


        ArrayAdapter<String> ownerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ownerArray);
        ownerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petOwner.setAdapter(ownerAdapter);
    }

    private void makeOwnerList(List<String> list){
        SQLiteVaccinationCard ownerDB = new SQLiteVaccinationCard(getActivity());
        Cursor ownerList = ownerDB.getAllOwners();
        mapOwners.put(getString(R.string.add_edit_pet_add_owner), 0);
        while(ownerList.moveToNext()){
            int id =ownerList.getInt(ownerList.getColumnIndex(VaacinationCardContract.OwnerData.OWNER_ID));
            String name = ownerList.getString(ownerList.getColumnIndex(VaacinationCardContract.OwnerData.OWNER_FIRST_NAME)) +" "+ ownerList.getString(ownerList.getColumnIndex(VaacinationCardContract.OwnerData.OWNER_LAST_NAME));
            list.add(name);
            mapOwners.put(name, id);
        }
        ownerList.close();
        ownerDB.close();
    }

    private void addOwnerListener(){
        petOwner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(petOwner.getSelectedItem().toString().equals(getString(R.string.add_edit_pet_add_owner))){
                    startActivity(new Intent(getActivity(), AddEditOwnerActivity.class));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void initializeView(int addEdit){
        switch (addEdit){
            case ADD_PET:
                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        insertPet();
                    }
                });
                break;
            case EDIT_PET:
                break;
        }
    }

    private void insertPet(){
        boolean error = false;
        SQLiteVaccinationCard petDB = new SQLiteVaccinationCard(getActivity());

        Log.e("date", "s "+petBirthDay);


        petKind = txtPetKind.getText().toString();
        petName = txtPetName.getText().toString();
        petBreed = txtPetBreed.getText().toString();
        petColor = txtPetColor.getText().toString();
        petSex = spPetSex.getSelectedItem().toString();
        petIdNumber = txtPetIdNumber.getText().toString();
        petParticularSigns = txtPetParticularSigns.getText().toString();
        petMicroChip = chkPetMicroChip.isActivated()?"t":"f";
        petTatttoo = chkPetTattoo.isActivated()?"t":"f";
        ownerId = mapOwners.get(petOwner.getSelectedItem().toString());

        Pet pet = new Pet();
        pet.setPetKind(petKind);
        pet.setPetSex(petSex);
        pet.setPetName(petName);
        pet.setPetBirthday(petBirthDay);
        pet.setPetBreed(petBreed);
        pet.setPetColor(petColor);
        pet.setPetIdNumber(petIdNumber);
        pet.setPetParticularSigns(petParticularSigns);
        pet.setPetMicroChip(petMicroChip);
        pet.setPetTattoo(petTatttoo);
        pet.setOwnerId(ownerId);


        if(petKind.equals("")){
            petKindLayout.setError(getString(R.string.add_edit_pet_error_null_value));
            error = true;
        }
        if(petName.equals("")){
            petNameLayout.setError(getString(R.string.add_edit_pet_error_null_value));
            error = true;
        }
        if(petBreed.equals("")){
            petBreedLayout.setError(getString(R.string.add_edit_pet_error_null_value));
            error = true;
        }
        if(ownerId<=0){
            Snackbar.make(getView(), getString(R.string.add_edit_pet_no_owner), Snackbar.LENGTH_LONG).show();
            error = true;
        }

        if(!error) {
            petDB.registerPet(pet);
            mListener.addPet();
        }
        petDB.close();
    }
}
