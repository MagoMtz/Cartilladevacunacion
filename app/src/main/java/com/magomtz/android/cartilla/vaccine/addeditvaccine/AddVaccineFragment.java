package com.magomtz.android.cartilla.vaccine.addeditvaccine;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import com.magomtz.android.cartilla.R;

public class AddVaccineFragment extends Fragment {
    private static final String ARG_PET_ID = "pet_id";

    private int petId;

    private CalendarView calendarDate;
    private CalendarView calendarNextAppointment;
    private ImageView imgTicket;
    private Button btnAddVaccine;

    private String date;
    private String nextAppointment;
    private String pathTicket;

    private AddVaccineFragmentListener mListener;

    public AddVaccineFragment() {
    }

    public static AddVaccineFragment newInstance(int petId){
        AddVaccineFragment fragment = new AddVaccineFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PET_ID, petId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            petId = getArguments().getInt(ARG_PET_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_vaccine, container, false);

        calendarDate = (CalendarView)view.findViewById(R.id.vaccine_application_date);
        calendarNextAppointment = (CalendarView)view.findViewById(R.id.vaccine_next_appointment);
        imgTicket = (ImageView)view.findViewById(R.id.vaccine_ticket);
        btnAddVaccine = (Button)view.findViewById(R.id.btn_register_vaccine);

        final String path ="path";

        imgTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.updateTicket(path);
            }
        });

        btnAddVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.addVaccine();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  AddVaccineFragmentListener){
            mListener = (AddVaccineFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString() + " must implement AddVaccineFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void insertVaccine(){
        Toast.makeText(getActivity(), "pet id "+petId, Toast.LENGTH_SHORT).show();
    }

}
