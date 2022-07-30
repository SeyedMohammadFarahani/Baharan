package com.baharan.service.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.baharan.service.App;
import com.baharan.service.Database.AppDatabase;
import com.baharan.service.Database.Current.Code;
import com.baharan.service.Database.Reserve.Reserve;
import com.baharan.service.R;
import com.baharan.service.Util.Const;
import com.baharan.service.Util.Utils;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        addReservesToDataBase();
        //AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().deleteAllReserves();

        //set Current Value
        List<Code> codes = AppDatabase.getAppDatabase(getApplicationContext()).currentDao().getAllCodes();
        if (codes.size() != 0) {
            Const.Code = codes.get(0).getCodeMelli();
        }

        new CountDownTimer(1500, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {

                if (App.getMyApplication().getSharedPreferences(Const.App, Const.Flag, getApplicationContext()).equals("1")) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            }

        }.start();
    }

    private void addReservesToDataBase() {
        //TODO test reservation

        if (AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().getAllReserves().size() == 0) {

            Reserve reserve1 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "ارتوپدی",
                    "دکتر سلطانی", "98/12/20", "13:00");
            Reserve reserve2 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "ارتوپدی",
                    "دکتر سلطانی", "98/12/20", "14:00");
            Reserve reserve3 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "ارتوپدی",
                    "دکتر سلطانی", "98/12/20", "15:00");
            Reserve reserve4 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "دندان پزشکی",
                    "دکتر هاشمی", "98/12/23", "17:00");
            Reserve reserve5 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "دندان پزشکی",
                    "دکتر هاشمی", "98/12/24", "18:00");
            Reserve reserve6 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "دندان پزشکی",
                    "دکتر هاشمی", "98/12/23", "19:00");
            Reserve reserve61 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "دندان پزشکی",
                    "دکتر آهنگر", "98/12/23", "17:00");
            Reserve reserve62 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "دندان پزشکی",
                    "دکتر آهنگر", "98/12/24", "18:00");
            Reserve reserve63 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "دندان پزشکی",
                    "دکتر آهنگر", "98/12/23", "19:00");
            Reserve reserve7 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "قلب و عروق",
                    "دکتر سهرابی", "98/11/18", "12:00");
            Reserve reserve8 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "قلب و عروق",
                    "دکتر سهرابی", "98/11/20", "15:00");
            Reserve reserve9 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "قلب و عروق",
                    "دکتر سهرابی", "98/11/25", "16:00");
            Reserve reserve10 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "کلیه و مجاری ادرار",
                    "دکتر رضایی", "98/12/28", "12:00");
            Reserve reserve11 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "کلیه و مجاری ادرار",
                    "دکتر رضایی", "98/12/28", "15:00");
            Reserve reserve12 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "کلیه و مجاری ادرار",
                    "دکتر رضایی", "98/12/28", "16:00");
            Reserve reserve64 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "کلیه و مجاری ادرار",
                    "دکتر آهنگران", "98/12/28", "12:00");
            Reserve reserve65 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "کلیه و مجاری ادرار",
                    "دکتر آهنگران", "98/12/28", "15:00");
            Reserve reserve66 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "کلیه و مجاری ادرار",
                    "دکتر آهنگران", "98/12/28", "16:00");
            Reserve reserve13 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "مغز و اعصاب",
                    "دکتر رحمانی", "99/01/15", "12:00");
            Reserve reserve14 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "مغز و اعصاب",
                    "دکتر رحمانی", "99/01/17", "15:00");
            Reserve reserve15 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان مفید", "مغز و اعصاب",
                    "دکتر رحمانی", "99/01/17", "16:00");

            Reserve reserve16 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "ارتوپدی",
                    "دکتر فراهانی", "98/12/20", "13:00");
            Reserve reserve17 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "ارتوپدی",
                    "دکتر فراهانی", "98/12/20", "14:00");
            Reserve reserve18 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "ارتوپدی",
                    "دکتر فراهانی", "98/12/20", "15:00");
            Reserve reserve67 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "ارتوپدی",
                    "دکتر سیادت", "98/12/20", "13:00");
            Reserve reserve68 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "ارتوپدی",
                    "دکتر سیادت", "98/12/20", "14:00");
            Reserve reserve69 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "ارتوپدی",
                    "دکتر سیادت", "98/12/20", "15:00");
            Reserve reserve19 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "دندان پزشکی",
                    "دکتر شبیری", "98/12/23", "17:00");
            Reserve reserve20 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "دندان پزشکی",
                    "دکتر شبیری", "98/12/24", "18:00");
            Reserve reserve21 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "دندان پزشکی",
                    "دکتر شبیری", "98/12/23", "19:00");
            Reserve reserve22 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "قلب و عروق",
                    "دکتر فهیم پور", "98/11/18", "12:00");
            Reserve reserve23 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "قلب و عروق",
                    "دکتر فهیم پور", "98/11/20", "15:00");
            Reserve reserve24 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "قلب و عروق",
                    "دکتر فهیم پور", "98/11/25", "16:00");
            Reserve reserve25 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "کلیه و مجاری ادرار",
                    "دکتر احمدی", "98/12/28", "12:00");
            Reserve reserve26 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "کلیه و مجاری ادرار",
                    "دکتر احمدی", "98/12/28", "15:00");
            Reserve reserve27 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "کلیه و مجاری ادرار",
                    "دکتر احمدی", "98/12/28", "16:00");
            Reserve reserve28 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "مغز و اعصاب",
                    "دکتر پازوکی", "99/01/15", "12:00");
            Reserve reserve29 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "مغز و اعصاب",
                    "دکتر پازوکی", "99/01/17", "15:00");
            Reserve reserve30 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "مغز و اعصاب",
                    "دکتر پازوکی", "99/01/17", "16:00");
            Reserve reserve70 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "مغز و اعصاب",
                    "دکتر نور", "99/01/15", "12:00");
            Reserve reserve71 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "مغز و اعصاب",
                    "دکتر نور", "99/01/17", "15:00");
            Reserve reserve72 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان امام رضا(ع)", "مغز و اعصاب",
                    "دکتر نور", "99/01/17", "16:00");

            Reserve reserve31 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "ارتوپدی",
                    "دکتر نوری", "98/12/20", "13:00");
            Reserve reserve32 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "ارتوپدی",
                    "دکتر نوری", "98/12/20", "14:00");
            Reserve reserve33 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "ارتوپدی",
                    "دکتر نوری", "98/12/20", "15:00");
            Reserve reserve34 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "دندان پزشکی",
                    "دکتر جمالی", "98/12/23", "17:00");
            Reserve reserve35 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "دندان پزشکی",
                    "دکتر جمالی", "98/12/24", "18:00");
            Reserve reserve36 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "دندان پزشکی",
                    "دکتر جمالی", "98/12/23", "19:00");
            Reserve reserve37 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "قلب و عروق",
                    "دکتر اردستانی", "98/11/18", "12:00");
            Reserve reserve38 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "قلب و عروق",
                    "دکتر اردستانی", "98/11/20", "15:00");
            Reserve reserve39 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "قلب و عروق",
                    "دکتر اردستانی", "98/11/25", "16:00");
            Reserve reserve73 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "قلب و عروق",
                    "دکتر شریفی", "98/11/18", "12:00");
            Reserve reserve74 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "قلب و عروق",
                    "دکتر شریفی", "98/11/20", "15:00");
            Reserve reserve75 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "قلب و عروق",
                    "دکتر شریفی", "98/11/25", "16:00");
            Reserve reserve40 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "کلیه و مجاری ادرار",
                    "دکتر محمدی", "98/12/28", "12:00");
            Reserve reserve41 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "کلیه و مجاری ادرار",
                    "دکتر محمدی", "98/12/28", "15:00");
            Reserve reserve42 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "کلیه و مجاری ادرار",
                    "دکتر محمدی", "98/12/28", "16:00");
            Reserve reserve76 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "کلیه و مجاری ادرار",
                    "دکتر انسانی", "98/12/28", "12:00");
            Reserve reserve77 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "کلیه و مجاری ادرار",
                    "دکتر انسانی", "98/12/28", "15:00");
            Reserve reserve78 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "کلیه و مجاری ادرار",
                    "دکتر انسانی", "98/12/28", "16:00");
            Reserve reserve43 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "مغز و اعصاب",
                    "دکتر دارابی", "99/01/15", "12:00");
            Reserve reserve44 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "مغز و اعصاب",
                    "دکتر دارابی", "99/01/17", "15:00");
            Reserve reserve45 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان بعثت", "مغز و اعصاب",
                    "دکتر دارابی", "99/01/17", "16:00");

            Reserve reserve46 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "ارتوپدی",
                    "دکتر رضاییان", "98/12/20", "13:00");
            Reserve reserve47 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "ارتوپدی",
                    "دکتر رضاییان", "98/12/20", "14:00");
            Reserve reserve48 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "ارتوپدی",
                    "دکتر رضاییان", "98/12/20", "15:00");
            Reserve reserve49 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "دندان پزشکی",
                    "دکتر علیپور", "98/12/23", "17:00");
            Reserve reserve50 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "دندان پزشکی",
                    "دکتر علیپور", "98/12/24", "18:00");
            Reserve reserve51 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "دندان پزشکی",
                    "دکتر علیپور", "98/12/23", "19:00");
            Reserve reserve79 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "دندان پزشکی",
                    "دکتر شجاعیان", "98/12/23", "17:00");
            Reserve reserve80 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "دندان پزشکی",
                    "دکتر شجاعیان", "98/12/24", "18:00");
            Reserve reserve81 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "دندان پزشکی",
                    "دکتر شجاعیان", "98/12/23", "19:00");
            Reserve reserve52 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "قلب و عروق",
                    "دکتر احمدزاده", "98/11/18", "12:00");
            Reserve reserve53 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "قلب و عروق",
                    "دکتر احمدزاده", "98/11/20", "15:00");
            Reserve reserve54 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "قلب و عروق",
                    "دکتر احمدزاده", "98/11/25", "16:00");
            Reserve reserve55 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "کلیه و مجاری ادرار",
                    "دکتر همتی", "98/12/28", "12:00");
            Reserve reserve56 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "کلیه و مجاری ادرار",
                    "دکتر همتی", "98/12/28", "15:00");
            Reserve reserve57 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "کلیه و مجاری ادرار",
                    "دکتر همتی", "98/12/28", "16:00");
            Reserve reserve58 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "مغز و اعصاب",
                    "دکتر حجابی", "99/01/15", "12:00");
            Reserve reserve59 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "مغز و اعصاب",
                    "دکتر حجابی", "99/01/17", "15:00");
            Reserve reserve60 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "مغز و اعصاب",
                    "دکتر حجابی", "99/01/17", "16:00");
            Reserve reserve82 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "مغز و اعصاب",
                    "دکتر خیراندیش", "99/01/15", "12:00");
            Reserve reserve83 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "مغز و اعصاب",
                    "دکتر خیراندیش", "99/01/17", "15:00");
            Reserve reserve84 = new Reserve(String.valueOf(Utils.randomCode()), "بیمارستان فجر", "مغز و اعصاب",
                    "دکتر خیراندیش", "99/01/17", "16:00");

            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve1);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve2);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve3);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve4);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve5);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve6);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve7);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve8);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve9);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve10);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve11);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve12);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve13);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve14);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve15);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve16);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve17);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve18);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve19);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve20);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve21);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve22);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve23);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve24);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve25);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve26);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve27);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve28);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve29);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve30);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve31);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve32);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve33);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve34);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve35);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve36);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve37);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve38);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve39);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve40);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve41);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve42);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve43);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve44);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve45);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve46);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve47);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve48);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve49);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve50);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve51);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve52);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve53);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve54);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve55);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve56);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve57);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve58);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve59);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve60);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve61);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve62);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve63);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve64);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve65);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve66);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve67);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve68);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve69);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve70);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve71);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve72);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve73);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve74);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve75);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve76);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve77);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve78);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve79);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve80);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve81);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve82);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve83);
            AppDatabase.getAppDatabase(getApplicationContext()).reserveDao().insertReserve(reserve84);

        }

    }
}
