package com.baharan.service.Fragment.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.baharan.service.Activity.MainActivity;
import com.baharan.service.Adapter.FamilyAdapter;
import com.baharan.service.Database.AppDatabase;
import com.baharan.service.Database.Family.Family;
import com.baharan.service.R;
import com.baharan.service.Util.Const;
import com.baharan.service.Widget.Text;

import java.util.ArrayList;
import java.util.List;

public class FamilyListFragment extends Fragment {

    private ConstraintLayout familyPlaceholder;
    private Text goToFamily;

    private List<Family> selectFamilies = new ArrayList<>();
    private RecyclerView familyList;
    private FamilyAdapter familyAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.family_list_fragment, container, false);

        //TODO init
        Const.number = 0;
        ((MainActivity) getActivity()).setTitle(getResources().getString(R.string.Title8));
        ((MainActivity) getActivity()).showMenu();

        selectFamilies = AppDatabase.getAppDatabase(getContext()).familyDao().getselectFamilies(Const.Code);
        familyAdapter = new FamilyAdapter(getActivity(), (ArrayList<Family>) selectFamilies);

        //TODO id
        familyPlaceholder = view.findViewById(R.id.familyPlaceholder);
        goToFamily = view.findViewById(R.id.goToFamily);
        familyList = view.findViewById(R.id.familyList);
        swipeRefreshLayout = view.findViewById(R.id.listRefresh);

        //TODO show placeHolder
        if (selectFamilies.size() == 0) {
            familyPlaceholder.setVisibility(View.VISIBLE);
        }

        //TODO go to Add Family
        goToFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFrag(((MainActivity) getActivity()).getFamilyFragment());
            }
        });

        updateView();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                selectFamilies = AppDatabase.getAppDatabase(getContext()).familyDao().getselectFamilies(Const.Code);
                updateView();
            }
        });

        return view;
    }

    public void updateView() {

        //TODO selectFamilies
        familyAdapter.setArray(selectFamilies);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        familyList.setLayoutManager(layoutManager);
        familyList.setAdapter(familyAdapter);
    }

}
