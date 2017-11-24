package com.magomtz.android.cartilla.vaccine.vaccineinfo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.magomtz.android.cartilla.R;
import com.magomtz.android.cartilla.model.Vaccine;

import java.util.ArrayList;

/**
 * Created by CPU-Margaro on 23/11/2017.
 */

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.VaccineListViewHolder> implements View.OnClickListener{
    private ArrayList<Vaccine> items;
    private View.OnClickListener clickListener;

    public VaccineAdapter(ArrayList<Vaccine> items){
        this.items = items;
    }

    public static class VaccineListViewHolder extends RecyclerView.ViewHolder{
        private TextView applicationDate;
        private TextView nextAppointment;
        private ImageView ticket;

        public VaccineListViewHolder(View itemView) {
            super(itemView);
            applicationDate = (TextView)itemView.findViewById(R.id.vaccine_application_date);
            nextAppointment = (TextView)itemView.findViewById(R.id.vaccine_next_appointment);
            ticket = (ImageView)itemView.findViewById(R.id.vaccine_ticket);
            ticket.setOnClickListener((View.OnClickListener) this);
        }
    }

    @Override
    public VaccineListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vaccine, parent, false);
        return new VaccineListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VaccineListViewHolder holder, int position) {
        holder.applicationDate.setText(items.get(position).getVaccineDate());
        holder.nextAppointment.setText(items.get(position).getVaccineNextAppointment());

        //Glide

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(View v) {
        if(clickListener != null){
            clickListener.onClick(v);
        }
    }

    public void setTicketClickListener(View.OnClickListener clickListener){
        this.clickListener = clickListener;
    }

}
