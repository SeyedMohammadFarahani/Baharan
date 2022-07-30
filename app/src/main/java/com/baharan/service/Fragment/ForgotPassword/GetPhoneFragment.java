package com.baharan.service.Fragment.ForgotPassword;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baharan.service.Activity.ForgotActivity;
import com.baharan.service.Database.AppDatabase;
import com.baharan.service.R;
import com.baharan.service.Widget.Btn;
import com.baharan.service.Widget.EditTxt;

public class GetPhoneFragment extends Fragment {

    private EditTxt phone;
    private Btn sendCode;
    private String phoneString;
    private boolean successPhone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.get_phone_fragment, container, false);

        //TODO ID
        phone = view.findViewById(R.id.forgotPhoneEditText);
        sendCode = view.findViewById(R.id.sendCodeButton);

        //TODO send code
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneString = phone.getText().toString();
                successPhone = true;

                //TODO check mobile
                if (phoneString.equals("")) {
                    phone.setError(getResources().getString(R.string.register1));
                    successPhone = false;
                } else if (phoneString.length() < 11 || phoneString.charAt(0) != '0' || phoneString.charAt(1) != '9') {
                    phone.setError(getResources().getString(R.string.register3));
                    successPhone = false;
                } else if (AppDatabase.getAppDatabase(getContext()).userDao().findUserByPhone(phoneString) == null) {
                    phone.setError(getResources().getString(R.string.forgot2));
                    successPhone = false;
                }

                if (successPhone) {
                    ((ForgotActivity) getActivity()).setPhoneString(phoneString);
                    ((ForgotActivity) getActivity()).changeFragment(((ForgotActivity) getActivity()).getSendCodeFragment());
                }
            }
        });

        return view;

    }
}
