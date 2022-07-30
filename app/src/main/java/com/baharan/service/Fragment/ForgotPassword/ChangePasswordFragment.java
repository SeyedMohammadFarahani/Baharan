package com.baharan.service.Fragment.ForgotPassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baharan.service.Activity.ForgotActivity;
import com.baharan.service.Activity.LoginActivity;
import com.baharan.service.Database.AppDatabase;
import com.baharan.service.R;
import com.baharan.service.Widget.Btn;
import com.baharan.service.Widget.EditTxt;

import se.aaro.gustav.passwordstrengthmeter.PasswordStrengthLevel;
import se.aaro.gustav.passwordstrengthmeter.PasswordStrengthMeter;

public class ChangePasswordFragment extends Fragment {

    private PasswordStrengthMeter meter;

    private EditTxt changePass;
    private EditTxt confirmedChangePass;
    private Btn changePassBtn;

    private String newPass;
    private String reEnterPass;
    private boolean success;

    private PasswordStrengthLevel[] myStrengthLevels = {
            new PasswordStrengthLevel("کوتاه", android.R.color.darker_gray),
            new PasswordStrengthLevel("ضعیف", android.R.color.holo_red_dark),
            new PasswordStrengthLevel("متوسط", android.R.color.holo_orange_dark),
            new PasswordStrengthLevel("خوب", android.R.color.holo_orange_light),
            new PasswordStrengthLevel("قوی", android.R.color.holo_blue_light),
            new PasswordStrengthLevel("خیلی\u200Cقوی", android.R.color.holo_green_dark)};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.change_password_fragment, container, false);

        //TODO ID

        changePass = view.findViewById(R.id.changePassEditText);
        confirmedChangePass = view.findViewById(R.id.confirmedPassEditText);
        changePassBtn = view.findViewById(R.id.changePassButton);
        meter = view.findViewById(R.id.newPassMeter);

        meter.setEditText(changePass);
        meter.setStrengthLevels(myStrengthLevels);


        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPass = changePass.getText().toString();
                reEnterPass = confirmedChangePass.getText().toString();
                success = true;

                //TODO check password
                if (newPass.equals("")) {
                    changePass.setError(getResources().getString(R.string.newPass));
                    success = false;
                } else if (newPass.length() < 8) {
                    changePass.setError(getResources().getString(R.string.register7));
                    success = false;
                }

                if (reEnterPass.equals("")) {
                    confirmedChangePass.setError(getResources().getString(R.string.newPass2));
                    success = false;
                } else if (!reEnterPass.equals(newPass)) {
                    confirmedChangePass.setError(getResources().getString(R.string.newPass3));
                    success = false;
                }

                if (success) {

                    AppDatabase.getAppDatabase(getContext()).userDao().updatePass(((ForgotActivity) getActivity()).getPhoneString(), newPass);
                    Toast.makeText(getContext(), getResources().getString(R.string.newPass4), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);

                    getActivity().overridePendingTransition(R.anim.push_left_enter, R.anim.push_left_exit);
                    getActivity().finish();

                }


            }
        });

        return view;
    }
}
