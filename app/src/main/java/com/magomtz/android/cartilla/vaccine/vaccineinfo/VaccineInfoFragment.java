package com.magomtz.android.cartilla.vaccine.vaccineinfo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.magomtz.android.cartilla.R;
import com.magomtz.android.cartilla.model.Vaccine;
import com.magomtz.android.cartilla.petlist.ui.PetListActivity;

import java.util.ArrayList;

public class VaccineInfoFragment extends Fragment {
    private static final String ARG_PET_ID = "pet_id";
    private static final String ARG_OWNER_ID = "owner_id";

    private int petPosition;
    private int ownerId;

    private RecyclerView vaccineList;
    private VaccineAdapter adapter;

    private Button btnNewVaccine;

    private VaccineFragmentListener mListener;

    public VaccineInfoFragment() {
        // Required empty public constructor
    }

    public static VaccineInfoFragment newInstance(int petPosition, int ownerId) {
        VaccineInfoFragment fragment = new VaccineInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_vaccine_info, container, false);
        adapter = new VaccineAdapter(new ArrayList<>(PetListActivity.vaccineMap.values()));
        vaccineList =(RecyclerView)view.findViewById(R.id.vaccine_recycler_view);
        vaccineList.setHasFixedSize(true);
        vaccineList.setLayoutManager(new LinearLayoutManager(getContext()));
        vaccineList.setAdapter(adapter);
        adapter.setTicketClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.updateTicket("path");
            }
        });
        btnNewVaccine = view.findViewById(R.id.btn_add_vaccine);
        btnNewVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.newVaccine(PetListActivity.arrayListPet.get(petPosition).getPetId());
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof VaccineFragmentListener) {
            mListener = (VaccineFragmentListener) context;
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

}
