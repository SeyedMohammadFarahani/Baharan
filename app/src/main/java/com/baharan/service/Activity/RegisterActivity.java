package com.baharan.service.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.baharan.service.Database.AppDatabase;
import com.baharan.service.Database.Current.Code;
import com.baharan.service.Database.User.User;
import com.baharan.service.R;
import com.baharan.service.Util.Const;
import com.baharan.service.Widget.Btn;
import com.baharan.service.Widget.EditTxt;
import com.baharan.service.Widget.Text;

import java.util.List;

import se.aaro.gustav.passwordstrengthmeter.PasswordStrengthLevel;
import se.aaro.gustav.passwordstrengthmeter.PasswordStrengthMeter;

import static com.baharan.service.R.id;
import static com.baharan.service.R.layout;
import static com.baharan.service.R.string;

public class RegisterActivity extends AppCompatActivity {

    private PasswordStrengthMeter meter;
    private EditTxt phoneEditTxt;
    private EditTxt passwordEditTxt;
    private EditTxt codeEditTxt;
    private Text loginText;
    private Btn registerButton;

    private String phoneString;
    private String codeString;
    private String passString;

    private boolean success;
    private List<Code> codes;

    private PasswordStrengthLevel[] myStrengthLevels = {
            new PasswordStrengthLevel("کوتاه", android.R.color.darker_gray),
            new PasswordStrengthLevel("ضعیف", android.R.color.holo_red_dark),
            new PasswordStrengthLevel("متوسط", android.R.color.holo_orange_dark),
            new PasswordStrengthLevel("خوب", android.R.color.holo_orange_light),
            new PasswordStrengthLevel("قوی", android.R.color.holo_blue_light),
            new PasswordStrengthLevel("خیلی\u200Cقوی", android.R.color.holo_green_dark)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_register);

        //TODO ID
        phoneEditTxt = findViewById(id.phoneEditText);
        loginText = findViewById(id.loginText);
        registerButton = findViewById(id.registerButton);
        codeEditTxt = findViewById(id.codeEditText);
        passwordEditTxt = findViewById(id.passEditText);
        meter = findViewById(id.passwordInputMeter);

        //TODO init
        meter.setEditText(passwordEditTxt);
        meter.setStrengthLevels(myStrengthLevels);
        passwordEditTxt.setTextDirection(View.TEXT_DIRECTION_RTL);
        passwordEditTxt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneString = phoneEditTxt.getText().toString();
                codeString = codeEditTxt.getText().toString();
                passString = passwordEditTxt.getText().toString();
                success = true;

                //TODO check mobile
                if (phoneString.equals("")) {
                    phoneEditTxt.setError(getResources().getString(string.register1));
                    success = false;
                } else if (phoneString.length() < 11 || phoneString.charAt(0) != '0' || phoneString.charAt(1) != '9') {
                    phoneEditTxt.setError(getResources().getString(string.register3));
                    success = false;
                } else if (AppDatabase.getAppDatabase(getApplicationContext()).userDao().findUserByPhone(phoneString) != null) {
                    phoneEditTxt.setError(getResources().getString(string.register10));
                    success = false;
                }

                //TODO check codeMelli
                if (codeString.equals("")) {
                    codeEditTxt.setError(getResources().getString(string.register4));
                    success = false;
                } else if (codeString.length() < 10) {
                    codeEditTxt.setError(getResources().getString(string.register5));
                    success = false;
                } else if (!Const.IsValidCode(codeString)) {
                    codeEditTxt.setError(getResources().getString(string.register9));
                    success = false;
                } else if (AppDatabase.getAppDatabase(getApplicationContext()).userDao().findUserByCodeMelli(codeString) != null) {
                    codeEditTxt.setError(getResources().getString(string.register11));
                    success = false;
                }

                //TODO check password
                if (passString.equals("")) {
                    passwordEditTxt.setError(getResources().getString(string.register6));
                    success = false;
                } else if (passString.length() < 8) {
                    passwordEditTxt.setError(getResources().getString(string.register7));
                    success = false;
                }

                if (success) {
                    //TODO Current Code
                    codes = AppDatabase.getAppDatabase(getApplicationContext()).currentDao().getAllCodes();
                    if (codes.size() == 0) {
                        Code code = new Code(codeString);
                        AppDatabase.getAppDatabase(getApplicationContext()).currentDao().insertCode(code);
                        codes = AppDatabase.getAppDatabase(getApplicationContext()).currentDao().getAllCodes();
                        Const.Code = codes.get(0).getCodeMelli();
                    } else {
                        AppDatabase.getAppDatabase(getApplicationContext()).currentDao().updateCode(codeString);
                        codes = AppDatabase.getAppDatabase(getApplicationContext()).currentDao().getAllCodes();
                        Const.Code = codes.get(0).getCodeMelli();
                    }

                    //TODO Create User
                    User user = new User(codeString, passString, phoneString);
                    AppDatabase.getAppDatabase(getApplicationContext()).userDao().insertUser(user);

                    Intent intent = new Intent(RegisterActivity.this, ConfirmedActivity.class);
                    intent.putExtra("phone", phoneString);
                    startActivity(intent);

                    overridePendingTransition(R.anim.push_left_enter, R.anim.push_left_exit);
                    RegisterActivity.this.finish();
                }
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
