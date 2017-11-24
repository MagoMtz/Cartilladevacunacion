package com.magomtz.android.cartilla.owner;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.magomtz.android.cartilla.R;
import com.magomtz.android.cartilla.db.SQLiteVaccinationCard;
import com.magomtz.android.cartilla.model.Owner;


public class AddEditOwnerFragment extends Fragment {
    private static final String ARG_ADD_EDIT = "add_edit";
    public static final int ADD_OWNER = 0;
    public static final int EDIT_OWNER = 1;
    private int argAddEdit;

    private TextInputLayout ownerFirstNameLayout;
    private TextInputEditText txtOwnerFirstName;
    private TextInputLayout ownerLastNameLayout;
    private TextInputEditText txtOwnerLastName;
    private TextInputEditText txtAddressName;
    private TextInputEditText txtPhoneName;
    private Button btnRegister;

    private String ownerFirstName;
    private String ownerLastName;
    private String ownerAddress;
    private String ownerPhone;

    private AddEditOwnerListener mListener;

    public AddEditOwnerFragment() {
        // Required empty public constructor
    }

    public static AddEditOwnerFragment newInstance(int addEddit) {
        AddEditOwnerFragment fragment = new AddEditOwnerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ADD_EDIT, addEddit);
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
        View view = inflater.inflate(R.layout.fragment_add_edit_owner, container, false);

        ownerFirstNameLayout = (TextInputLayout)view.findViewById(R.id.txt_owner_first_name_layout);
        txtOwnerFirstName=(TextInputEditText)view.findViewById(R.id.txt_owner_first_name);
        ownerLastNameLayout=(TextInputLayout)view.findViewById(R.id.txt_owner_last_name_layout);
        txtOwnerLastName=(TextInputEditText)view.findViewById(R.id.txt_owner_last_name);
        txtAddressName=(TextInputEditText)view.findViewById(R.id.txt_owner_address);
        txtPhoneName=(TextInputEditText)view.findViewById(R.id.txt_owner_phone);
        btnRegister = (Button)view.findViewById(R.id.btn_register_owner);

        initializeView(argAddEdit);

        return view;
    }

    private void initializeView(int addEdit){
        switch (addEdit){
            case ADD_OWNER:
                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        insertOwner();
                    }
                });
                break;
            case EDIT_OWNER:
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddEditOwnerListener) {
            mListener = (AddEditOwnerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AddEditOwnerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void insertOwner(){
        boolean error = false;
        SQLiteVaccinationCard ownerDB = new SQLiteVaccinationCard(getActivity());

        ownerFirstName = txtOwnerFirstName.getText().toString();
        ownerLastName = txtOwnerLastName.getText().toString();
        ownerAddress = txtAddressName.getText().toString();
        ownerPhone = txtPhoneName.getText().toString();

        Owner owner = new Owner();
        owner.setOwnerFirstName(ownerFirstName);
        owner.setOwnerLastName(ownerLastName);
        owner.setOwnerAddres(ownerAddress);
        owner.setOwnerPhone(ownerPhone);

        if(ownerFirstName.equals("")){
            ownerFirstNameLayout.setError(getString(R.string.add_edit_owner_error_null_value));
            error=true;
        }
        if(ownerLastName.equals("")){
            ownerLastNameLayout.setError(getString(R.string.add_edit_owner_error_null_value));
            error=true;
        }

        if(!error){
            ownerDB.registerOwner(owner);
            mListener.addOwner();
        }
        ownerDB.close();
    }

}
