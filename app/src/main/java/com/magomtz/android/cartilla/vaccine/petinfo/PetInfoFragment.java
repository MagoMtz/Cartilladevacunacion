package com.magomtz.android.cartilla.vaccine.petinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magomtz.android.cartilla.R;
import com.magomtz.android.cartilla.petlist.ui.PetListActivity;

public class PetInfoFragment extends Fragment {
    private static final String ARG_PET_ID = "pet_id";
    private static final String ARG_OWNER_ID = "owner_id";

    private int petPosition;
    private int ownerId;

    private TextView petName;
    private TextView petKind;
    private TextView petSex;
    private TextView petBirthday;
    private TextView petBreed;
    private TextView petColor;
    private TextView petIdNumber;
    private TextView petParticularSigns;
    private TextView petMicrochip;
    private TextView petTattoo;
    private TextView ownerName;
    private TextView ownerAddress;
    private TextView ownerPhone;

    //private OnFragmentInteractionListener mListener;

    public PetInfoFragment() {
        // Required empty public constructor
    }

    public static PetInfoFragment newInstance(int petPosition, int ownerId) {
        PetInfoFragment fragment = new PetInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PET_ID, petPosition);
        args.putInt(ARG_OWNER_ID, ownerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            petPosition = getArguments().getInt(ARG_PET_ID);
            ownerId = getArguments().getInt(ARG_OWNER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_info, container, false);

        petName = (TextView)view.findViewById(R.id.pet_info_pet_name);
        petKind = (TextView)view.findViewById(R.id.pet_info_pet_kind);
        petSex = (TextView)view.findViewById(R.id.pet_info_pet_sex);
        petBirthday = (TextView)view.findViewById(R.id.pet_info_pet_birthday);
        petBreed = (TextView)view.findViewById(R.id.pet_info_pet_breed);
        petColor = (TextView)view.findViewById(R.id.pet_info_pet_color);
        petIdNumber = (TextView)view.findViewById(R.id.pet_info_pet_id_number);
        petParticularSigns = (TextView)view.findViewById(R.id.pet_info_pet_particular_signs);
        petMicrochip = (TextView)view.findViewById(R.id.pet_info_pet_micro_chip);
        petTattoo = (TextView)view.findViewById(R.id.pet_info_pet_tattoo);
        ownerName = (TextView)view.findViewById(R.id.owner_info_owner_name);
        ownerAddress = (TextView)view.findViewById(R.id.owner_info_owner_address);
        ownerPhone = (TextView)view.findViewById(R.id.owner_info_owner_phone);

        petName.setText(PetListActivity.arrayListPet.get(petPosition).getPetName());
        petKind.setText(PetListActivity.arrayListPet.get(petPosition).getPetKind());
        petSex.setText(PetListActivity.arrayListPet.get(petPosition).getPetSex());
        String birthday = PetListActivity.arrayListPet.get(petPosition).getPetBirthday().equals("null") ? getString(R.string.pet_info_not_given) :PetListActivity.arrayListPet.get(petPosition).getPetBirthday();
        petBirthday.setText(birthday);
        petBreed.setText(PetListActivity.arrayListPet.get(petPosition).getPetBreed());
        petColor.setText(PetListActivity.arrayListPet.get(petPosition).getPetColor());
        petIdNumber.setText(PetListActivity.arrayListPet.get(petPosition).getPetIdNumber());
        petParticularSigns.setText(PetListActivity.arrayListPet.get(petPosition).getPetParticularSigns());
        String microChip = PetListActivity.arrayListPet.get(petPosition).getPetMicroChip().equals("t") ? getString(R.string.pet_info_yes) : getString(R.string.pet_info_no);
        petMicrochip.setText(microChip);
        String tattoo = PetListActivity.arrayListPet.get(petPosition).getPetTattoo().endsWith("t") ? getString(R.string.pet_info_yes) : getString(R.string.pet_info_no);
        petTattoo.setText(tattoo);
        String oName = PetListActivity.ownerMap.get(ownerId).getOwnerFirstName() + " "+ PetListActivity.ownerMap.get(ownerId).getOwnerLastName();
        ownerName.setText(oName);
        ownerAddress.setText(PetListActivity.ownerMap.get(ownerId).getOwnerAddres());
        ownerPhone.setText(PetListActivity.ownerMap.get(ownerId).getOwnerPhone());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

}
