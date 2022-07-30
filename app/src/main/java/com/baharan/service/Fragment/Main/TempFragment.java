package com.baharan.service.Fragment.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baharan.service.Activity.MainActivity;
import com.baharan.service.R;
import com.baharan.service.Util.Const;

public class TempFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.temp_fragment, container, false);

        ((MainActivity) getActivity()).setTitle(getResources().getString(R.string.tempTitle));
        ((MainActivity) getActivity()).showMenu();
        Const.number = 0;

        return view;
    }
}
