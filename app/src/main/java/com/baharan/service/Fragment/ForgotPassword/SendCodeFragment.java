package com.baharan.service.Fragment.ForgotPassword;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baharan.service.Activity.ForgotActivity;
import com.baharan.service.R;
import com.baharan.service.Service.smsService;
import com.baharan.service.Widget.Btn;
import com.baharan.service.Widget.EditTxt;
import com.baharan.service.Widget.Text;
import com.tapadoo.alerter.Alerter;

public class SendCodeFragment extends Fragment {

    private smsService sms;
    private EditTxt verifyEditTxt;
    private Btn confirmedBtn;
    private Text confirmedText;
    private String phone;
    private String verification;
    private int getCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.send_code_fragment, container, false);
        final Typeface typeface = Typeface.createFromAsset(getResources().getAssets(), "isans.ttf");

        //TODO ID
        confirmedText = view.findViewById(R.id.forgotPhone);
        verifyEditTxt = view.findViewById(R.id.forgotCodeEditText);
        confirmedBtn = view.findViewById(R.id.confirmedForgotButton);

        phone = ((ForgotActivity) getActivity()).getPhoneString();

        //send message
        final Intent intent3 = new Intent(getContext(), smsService.class);
        getActivity().startService(intent3);
        Intent intent2 = new Intent(getContext(), smsService.class);
        getActivity().bindService(intent2, serviceConnection, Context.BIND_AUTO_CREATE);

        confirmedText.setText(" " + getResources().getString(R.string.code1) + " " + phone + " " + getResources().getString(R.string.code2) + " ");

        confirmedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification = verifyEditTxt.getText().toString();

                //check null
                if (verification.equals("")) {
                } else {
                    getCode = Integer.parseInt(verifyEditTxt.getText().toString());
                }

                //check correctness
                if (verification.equals("")) {
                    verifyEditTxt.setError(getResources().getString(R.string.enterCode));
                } else if (getCode == confirmed()) {
                    ((ForgotActivity) getActivity()).changeFragment(((ForgotActivity) getActivity()).getChangePasswordFragment());
                } else {
                    Alerter.create(getActivity())
                            .setText(R.string.error)
                            .setDismissable(true)
                            .setTextTypeface(typeface)
                            .setTextAppearance(14)
                            .setBackgroundColorRes(R.color.pink)
                            .setIcon(R.drawable.error)
                            .show();
                }
            }
        });

        return view;

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            smsService.MyBinder b = (smsService.MyBinder) service;
            sms = b.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            sms = null;
        }
    };

    private int confirmed() {
        if (sms != null)
            return sms.getCode();

        return 0;
    }
}
