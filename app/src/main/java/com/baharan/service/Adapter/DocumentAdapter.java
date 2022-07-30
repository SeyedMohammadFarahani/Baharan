package com.baharan.service.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baharan.service.Database.Family.Family;
import com.baharan.service.Database.Reserve.Reserve;
import com.baharan.service.R;
import com.baharan.service.Widget.Text;

import java.util.ArrayList;
import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.RecycleViewHolder> {
    private Context context;
    private List<Reserve> reserves;

    public DocumentAdapter(Context context, ArrayList<Reserve> cards) {
        this.context = context;
        this.reserves = cards;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reserve_row, viewGroup, false);
        RecycleViewHolder recycleViewHolder = new RecycleViewHolder(view1);
        return recycleViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final RecycleViewHolder recycleViewHolder, int i) {

        recycleViewHolder.date.setText(reserves.get(i).getDate());
        recycleViewHolder.time.setText(reserves.get(i).getTime());
        recycleViewHolder.name.setText(reserves.get(i).getOwnerName());
        recycleViewHolder.doctor.setText(reserves.get(i).getDoctorName());
        recycleViewHolder.expertise.setText(reserves.get(i).getField());
        recycleViewHolder.hospital.setText(reserves.get(i).getHospital());
        recycleViewHolder.code.setText(reserves.get(i).getCode());

    }

    public void setArray(List<Reserve> changeReserves) {
        reserves = changeReserves;
    }

    @Override
    public int getItemCount() {
        return reserves.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        Text date;
        Text time;
        Text name;
        Text doctor;
        Text expertise;
        Text hospital;
        Text code;


        public RecycleViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemView.findViewById(R.id.reserve);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            name = itemView.findViewById(R.id.owner);
            doctor = itemView.findViewById(R.id.doctor);
            expertise = itemView.findViewById(R.id.expertise);
            hospital = itemView.findViewById(R.id.hospital);
            code = itemView.findViewById(R.id.myCode);

        }
    }
}




