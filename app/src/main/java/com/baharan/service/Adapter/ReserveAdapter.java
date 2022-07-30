package com.baharan.service.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.baharan.service.Database.AppDatabase;
import com.baharan.service.Database.Reserve.Reserve;
import com.baharan.service.R;
import com.baharan.service.Widget.Text;

import java.util.ArrayList;
import java.util.List;

public class ReserveAdapter extends RecyclerView.Adapter<ReserveAdapter.RecycleViewHolder> {

    private Context context;
    private List<Reserve> reserves;
    private String temp;
    private String reserveCodeString;
    private int count = 0;

    public ReserveAdapter(Context context, ArrayList<Reserve> reserves) {
        this.context = context;
        this.reserves = reserves;
    }

    @NonNull
    @Override
    public ReserveAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reserve_item, viewGroup, false);
        RecycleViewHolder recycleViewHolder = new RecycleViewHolder(view);
        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ReserveAdapter.RecycleViewHolder recycleViewHolder, int i) {
        recycleViewHolder.hospitalName.setText(reserves.get(i).getHospital());
        recycleViewHolder.expertise.setText(reserves.get(i).getField());
        recycleViewHolder.doctorName.setText(reserves.get(i).getDoctorName());
        recycleViewHolder.reserveDate.setText(reserves.get(i).getDate());
        recycleViewHolder.reserveTime.setText(reserves.get(i).getTime());
        recycleViewHolder.reserveCode.setText(reserves.get(i).getCode());

        recycleViewHolder.selectReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = recycleViewHolder.reserveCode.getText().toString();
                if (recycleViewHolder.selectReserve.isChecked()) {
                    reserveCodeString = temp;
                    count++;
                } else if (!recycleViewHolder.selectReserve.isChecked()) {
                    if (count > 0) {
                        count--;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return reserves.size();
    }

    public void setArray(List<Reserve> changeFamilies) {
        reserves = changeFamilies;
    }

    public String findReserve() {

        return reserveCodeString;
    }

    public int reserveCounter() {

        return count;
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        Text hospitalName;
        Text expertise;
        Text doctorName;
        Text reserveDate;
        Text reserveTime;
        Text reserveCode;
        CheckBox selectReserve;

        public RecycleViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemView.findViewById(R.id.reserveRow);
            hospitalName = itemView.findViewById(R.id.hospitalName);
            expertise = itemView.findViewById(R.id.fieldName);
            doctorName = itemView.findViewById(R.id.doctorName);
            reserveDate = itemView.findViewById(R.id.reserveDate);
            reserveTime = itemView.findViewById(R.id.reserveTime);
            reserveCode = itemView.findViewById(R.id.reserveCode);
            selectReserve = itemView.findViewById(R.id.selectReserve);

        }
    }

}






