package com.baharan.service.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baharan.service.Fragment.ForgotPassword.ChangePasswordFragment;
import com.baharan.service.Fragment.ForgotPassword.GetPhoneFragment;
import com.baharan.service.Fragment.ForgotPassword.SendCodeFragment;
import com.baharan.service.R;

public class ForgotActivity extends AppCompatActivity {

    private String phoneString;

    private GetPhoneFragment getPhoneFragment;
    private SendCodeFragment sendCodeFragment;
    private ChangePasswordFragment changePasswordFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        //TODO init
        getPhoneFragment = new GetPhoneFragment();
        sendCodeFragment = new SendCodeFragment();
        changePasswordFragment = new ChangePasswordFragment();

        changeFragment(getPhoneFragment);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.push_left_enter, R.anim.push_left_exit).replace(R.id.fragment, fragment).commit();
    }

    public GetPhoneFragment getGetPhoneFragment() {
        return getPhoneFragment;
    }

    public SendCodeFragment getSendCodeFragment() {
        return sendCodeFragment;
    }

    public ChangePasswordFragment getChangePasswordFragment() {
        return changePasswordFragment;
    }

    public String getPhoneString() {
        return phoneString;
    }

    public void setPhoneString(String phoneString) {
        this.phoneString = phoneString;
    }
}
