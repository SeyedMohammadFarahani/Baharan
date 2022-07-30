package com.baharan.service.Util;

import com.baharan.service.R;

import java.util.ArrayList;

public class Const {

    public static final String App = "App";
    public static String Flag = "Flag";
    public static String Code = "Code" ;

    public static int number = 0;

    public static boolean IsValidCode(String code) {

        int sum = 0;

        int[] digit = new int[code.length()];
        for (int i = 0; i < code.length(); i++) {
            digit[i] = code.charAt(i) - '0';
        }

        int j = 0;
        for (int i = 10; i > 1; i--) {
            sum += i * digit[j];
            j++;
        }

        if (sum % 11 < 2) {
            if ((sum % 11) == digit[9])
                return true;
            else
                return false;
        } else {
            if ((11 - (sum % 11)) == digit[9])
                return true;
            else
                return false;
        }
    }

    public static ArrayList<Row> getList() {

        ArrayList<Row> rows = new ArrayList<>();

        Row row1 = new Row("صفحه\u200Cی اصلی", R.drawable.ic_homepage);
        Row row2 = new Row("صفحه\u200Cی کاربری", R.drawable.ic_add_user);
        Row row3 = new Row("لیست بستگان", R.drawable.ic_contact_book);
        Row row4 = new Row("خروج از حساب\u200Cکاربری", R.drawable.ic_log_out);

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);

        return rows;
    }
}
