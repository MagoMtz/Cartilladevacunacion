package com.magomtz.android.cartilla.petlist.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magomtz.android.cartilla.R;
import com.magomtz.android.cartilla.petlist.ui.adapters.PetListAdapter;

import static com.magomtz.android.cartilla.petlist.ui.PetListActivity.arrayListPet;

public class PetListFragment extends Fragment {
    private RecyclerView petList;
    public static PetListAdapter adapter;
    private PetListFragmentListener mListener;

    public PetListFragment() {
        // Required empty public constructor
    }

    public static PetListFragment newInstance() {
        PetListFragment fragment = new PetListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_list, container, false);
        adapter= new PetListAdapter(getActivity(), arrayListPet);
        petList = (RecyclerView)view.findViewById(R.id.pet_list);
        petList.setHasFixedSize(true);
        petList.setLayoutManager(new LinearLayoutManager(getContext()));
        petList.setAdapter(adapter);
        adapter.setOnListClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemListClick(petList.getChildAdapterPosition(v));
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PetListFragmentListener) {
            mListener = (PetListFragmentListener) context;
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
