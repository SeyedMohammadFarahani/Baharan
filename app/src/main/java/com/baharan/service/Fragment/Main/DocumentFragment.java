package com.baharan.service.Fragment.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baharan.service.Activity.MainActivity;
import com.baharan.service.Adapter.DocumentAdapter;
import com.baharan.service.Adapter.FamilyAdapter;
import com.baharan.service.Database.AppDatabase;
import com.baharan.service.Database.Family.Family;
import com.baharan.service.Database.Reserve.Reserve;
import com.baharan.service.R;
import com.baharan.service.Util.Const;
import com.baharan.service.Widget.Text;

import java.util.ArrayList;
import java.util.List;

public class DocumentFragment extends Fragment {


    private ConstraintLayout documentPlaceholder;
    private Text goToDoctor;

    private List<Reserve> selectReserves = new ArrayList<>();
    private RecyclerView reserveList;
    private DocumentAdapter reserveAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.document_fragment, container, false);

        //TODO init
        Const.number = 0;
        ((MainActivity) getActivity()).setTitle(getResources().getString(R.string.Title4));
        ((MainActivity) getActivity()).showMenu();

        selectReserves = AppDatabase.getAppDatabase(getContext()).reserveDao().getOwnerReserves(Const.Code);
        reserveAdapter = new DocumentAdapter(getActivity(), (ArrayList<Reserve>) selectReserves);

        //TODO id
        reserveList = view.findViewById(R.id.document);
        documentPlaceholder = view.findViewById(R.id.documentPlaceholder);
        goToDoctor = view.findViewById(R.id.goToDoctor);

        //TODO show placeHolder
        if (selectReserves.size() == 0) {
            documentPlaceholder.setVisibility(View.VISIBLE);
        }

        //TODO go to Add Family
        goToDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFrag(((MainActivity) getActivity()).getDoctorsFragment());
            }
        });

        updateView();

        return view;
    }

    public void updateView() {

        //TODO selectFamilies
        reserveAdapter.setArray(selectReserves);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        reserveList.setLayoutManager(layoutManager);
        reserveList.setAdapter(reserveAdapter);
    }
}
