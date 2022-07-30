package com.baharan.service.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import com.baharan.service.Database.AppDatabase;
import com.baharan.service.Database.Family.Family;
import com.baharan.service.R;
import com.baharan.service.Util.Utils;
import com.baharan.service.Widget.Text;

import java.util.ArrayList;
import java.util.List;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.RecycleViewHolder> {

    private Context context;
    private List<Family> families;
    private String temp;

    public FamilyAdapter(Context context, ArrayList<Family> families) {
        this.context = context;
        this.families = families;
    }

    @NonNull
    @Override
    public FamilyAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.family_row, viewGroup, false);
        RecycleViewHolder recycleViewHolder = new RecycleViewHolder(view);
        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FamilyAdapter.RecycleViewHolder recycleViewHolder, int i) {
        recycleViewHolder.text1.setText(families.get(i).getFirstName() + " " + families.get(i).getLastName());
        recycleViewHolder.text2.setText(families.get(i).getRelative());
        recycleViewHolder.text3.setText(families.get(i).getCodeMelli());
        recycleViewHolder.text4.setText(families.get(i).getInsuranceCode());

        recycleViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = recycleViewHolder.text3.getText().toString();
                View childView = (View) v.getParent();
                View mainView = (View) childView.getParent();
                Family family = AppDatabase.getAppDatabase(context).familyDao().findFamilyByCodeMelli(temp);
                if (family != null) {
                    Utils.deleteFamilyDialog(mainView, temp, context);
                }
            }
        });
    }

    public void setArray(List<Family> changeFamilies) {
        families = changeFamilies;
    }

    @Override
    public int getItemCount() {
        return families.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        Text text1;
        Text text2;
        Text text3;
        Text text4;
        ImageButton delete;

        public RecycleViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemView.findViewById(R.id.familyRow);
            text1 = itemView.findViewById(R.id.familyName);
            text2 = itemView.findViewById(R.id.familyRelative);
            text3 = itemView.findViewById(R.id.familyCodeMelli);
            text4 = itemView.findViewById(R.id.familyInsuranceCode);
            delete = itemView.findViewById(R.id.deleteFamily);

        }
    }

}






