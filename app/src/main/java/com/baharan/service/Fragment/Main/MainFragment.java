package com.baharan.service.Fragment.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.baharan.service.Activity.MainActivity;
import com.baharan.service.R;
import com.baharan.service.Util.Const;

public class MainFragment extends Fragment {

    private ImageButton familyButton;
    private ImageButton reserveButton;
    private ImageButton documentButton;
    private ImageButton restaurantButton;
    private ImageButton hotelButton;
    private ImageButton parkButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.main_fragment, container, false);

        //TODO init
        Const.number = 1;
        ((MainActivity) getActivity()).setTitle(getResources().getString(R.string.mainTitle));
        ((MainActivity) getActivity()).doNotShowMenu();

        //TODO ID
        familyButton = view.findViewById(R.id.mainButton2);
        reserveButton = view.findViewById(R.id.mainButton1);
        documentButton = view.findViewById(R.id.mainButton3);
        restaurantButton = view.findViewById(R.id.mainButton4);
        hotelButton = view.findViewById(R.id.mainButton5);
        parkButton = view.findViewById(R.id.mainButton6);

        familyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFrag(((MainActivity) getActivity()).getFamilyFragment());
            }
        });

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFrag(((MainActivity) getActivity()).getDoctorsFragment());
            }
        });

        documentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFrag(((MainActivity) getActivity()).getDocumentFragment());
            }
        });

        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFrag(((MainActivity) getActivity()).getTempFragment());
            }
        });

        hotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFrag(((MainActivity) getActivity()).getTempFragment());
            }
        });

        parkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFrag(((MainActivity) getActivity()).getTempFragment());
            }
        });

        return view;
    }
}
