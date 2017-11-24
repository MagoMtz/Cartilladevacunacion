package com.magomtz.android.cartilla.petlist.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.magomtz.android.cartilla.R;
import com.magomtz.android.cartilla.model.Pet;
import com.magomtz.android.cartilla.petlist.ui.PetListActivity;

import java.util.ArrayList;

/**
 * Created by Mago on 19/11/2017.
 */

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.PetListViewHolder> implements View.OnClickListener{
    private Context context;
    private ArrayList<Pet> items;
    private View.OnClickListener clickListener;

    public PetListAdapter(Context context, ArrayList<Pet> items) {
        this.context = context;
        this.items = items;
    }

    public static class PetListViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgPet;
        private TextView petName;
        private TextView petOwner;

        public PetListViewHolder(View itemView) {
            super(itemView);
            imgPet = (ImageView)itemView.findViewById(R.id.img_pet);
            petName = (TextView)itemView.findViewById(R.id.pet_name);
            petOwner = (TextView)itemView.findViewById(R.id.pet_owner);
        }
    }

    @Override
    public PetListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pet_list, parent, false);
        view.setOnClickListener(this);
        return new PetListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PetListViewHolder holder, int position) {
        holder.petName.setText(items.get(position).getPetName());
        holder.petOwner.setText(PetListActivity.ownerMap.get(items.get(position).getOwnerId()).getOwnerFirstName() + " " + PetListActivity.ownerMap.get(items.get(position).getOwnerId()).getOwnerLastName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(View v) {
        if(clickListener!=null){
            clickListener.onClick(v);
        }
    }

    public void setOnListClickListener(View.OnClickListener clickListener){
        this.clickListener = clickListener;
    }

}
