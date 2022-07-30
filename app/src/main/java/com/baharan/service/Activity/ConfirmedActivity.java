package com.baharan.service.Activity;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.baharan.service.App;
import com.baharan.service.R;
import com.baharan.service.Service.smsService;
import com.baharan.service.Util.Const;
import com.baharan.service.Widget.Btn;
import com.baharan.service.Widget.EditTxt;
import com.baharan.service.Widget.Text;
import com.tapadoo.alerter.Alerter;

public class ConfirmedActivity extends AppCompatActivity {

    private smsService sms;
    private EditTxt verifyEditTxt;
    private Btn confirmedBtn;
    private Text confirmedText;
    private Text again;
    private Text timer;
    private String phone;
    private String verification;
    private int getCode;
    private int time = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirme);

        final Typeface typeface = Typeface.createFromAsset(getAssets(), "isans.ttf");

        phone = getIntent().getExtras().getString("phone");

        //TODO Find ID
        again = findViewById(R.id.again);
        timer = findViewById(R.id.timer);
        confirmedText = findViewById(R.id.confirmedText);
        confirmedBtn = findViewById(R.id.confirmedButton);
        verifyEditTxt = findViewById(R.id.confirmedEditText);


        //send message
        final Intent intent3 = new Intent(ConfirmedActivity.this, smsService.class);
        startService(intent3);
        Intent intent2 = new Intent(this, smsService.class);
        bindService(intent2, serviceConnection, Context.BIND_AUTO_CREATE);

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
                    //TODO Create Family
                    App.getMyApplication().saveSharedPreferences(Const.App, Const.Flag, "1", getApplicationContext());
                    Intent intent1 = new Intent(ConfirmedActivity.this, MainActivity.class);
                    startActivity(intent1);
                    overridePendingTransition(R.anim.push_left_enter, R.anim.push_left_exit);
                    ConfirmedActivity.this.finish();
                } else {
                    Alerter.create(ConfirmedActivity.this)
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


        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(" " + getResources().getString(R.string.reSend) + " " + time + getResources().getString(R.string.reSend2) + " ");
                time--;
                if ((millisUntilFinished / 1000) == 2) {
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.cancel(0);
                }
            }

            @Override
            public void onFinish() {
                timer.setText("");
                again.setVisibility(View.VISIBLE);
            }
        }.start();

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Resend verification code
                startService(intent3);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ConfirmedActivity.this, RegisterActivity.class);
        startActivity(intent);
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
