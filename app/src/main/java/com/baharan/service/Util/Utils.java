package com.baharan.service.Util;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.baharan.service.Database.AppDatabase;
import com.baharan.service.R;
import com.baharan.service.Widget.Btn;

import java.util.Random;

public class Utils {

    public static void deleteFamilyDialog(final View view, final String temp, final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.delete_family_dialog);
        //TODO
        final Btn cancel = dialog.findViewById(R.id.cancelActionDialog);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Btn delete = dialog.findViewById(R.id.deleteActionDialog);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AppDatabase.getAppDatabase(context).actionDao().deleteTransAction(AppDatabase.getAppDatabase(context).actionDao().findTransActionByCode(temp));
                view.setVisibility(View.GONE);
                AppDatabase.getAppDatabase(context).familyDao().deleteFamily(AppDatabase.getAppDatabase(context).familyDao().findFamilyByCodeMelli(temp));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static int randomCode() {
        int number = 0;

        while (number < 1000) {
            Random random = new Random();
            number = (number * 10) + random.nextInt(10);
        }
        return number;
    }



}
