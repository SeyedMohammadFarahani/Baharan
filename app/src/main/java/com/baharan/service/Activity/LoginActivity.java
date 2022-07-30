package com.baharan.service.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baharan.service.App;
import com.baharan.service.Database.AppDatabase;
import com.baharan.service.Database.Current.Code;
import com.baharan.service.R;
import com.baharan.service.Util.Const;
import com.baharan.service.Widget.Btn;
import com.baharan.service.Widget.EditTxt;
import com.baharan.service.Widget.Text;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Btn login;
    private EditTxt codeLogin;
    private EditTxt passLogin;
    private Text forgotPassword;

    private String codeLoginString;
    private String passLoginString;

    private boolean success;
    private List<Code> codes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //TODO ID
        codeLogin = findViewById(R.id.codeLogin);
        passLogin = findViewById(R.id.passLogin);
        forgotPassword = findViewById(R.id.forgotText);
        login = findViewById(R.id.loginButton);

        codeLogin.requestFocus();

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);

                overridePendingTransition(R.anim.push_left_enter, R.anim.push_left_exit);
                LoginActivity.this.finish();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeLoginString = codeLogin.getText().toString();
                passLoginString = passLogin.getText().toString();
                success = true;

                //TODO check codeMelli
                if (codeLoginString.equals("")) {
                    codeLogin.setError(getResources().getString(R.string.register4));
                    success = false;
                } else if (codeLoginString.length() < 10) {
                    codeLogin.setError(getResources().getString(R.string.register5));
                    success = false;
                } else if (!Const.IsValidCode(codeLoginString)) {
                    codeLogin.setError(getResources().getString(R.string.register9));
                    success = false;
                } else if (AppDatabase.getAppDatabase(getApplicationContext()).userDao()
                        .findUserByCodeMelli(codeLoginString) == null) {
                    codeLogin.setError(getResources().getString(R.string.login));
                    success = false;
                }

                //TODO check codeMelli
                if (passLoginString.equals("")) {
                    passLogin.setError(getResources().getString(R.string.register8));
                    success = false;
                }

                //TODO Username & Password not match
                if (AppDatabase.getAppDatabase(getApplicationContext()).userDao()
                        .findUserByCodeMelli(codeLoginString) != null) {
                    if (!(AppDatabase.getAppDatabase(getApplicationContext()).userDao()
                            .findUserByCodeMelli(codeLoginString).getPassword().equals(passLoginString))) {
                        codeLogin.setError(getResources().getString(R.string.register12));
                        success = false;
                    }
                }

                if (success) {
                    AppDatabase.getAppDatabase(getApplicationContext()).currentDao().updateCode(codeLoginString);
                    codes = AppDatabase.getAppDatabase(getApplicationContext()).currentDao().getAllCodes();
                    Const.Code = codes.get(0).getCodeMelli();

                    App.getMyApplication().saveSharedPreferences(Const.App, Const.Flag, "1", getApplicationContext());

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_enter, R.anim.push_left_exit);
                    LoginActivity.this.finish();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

}
