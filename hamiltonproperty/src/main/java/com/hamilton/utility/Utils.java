package com.hamilton.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;

/**
 * Created by HP on 07-05-2017.
 */

public class Utils {
    public static void showErrorBox(Activity context, String title, String msg) {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null)
            builder.setTitle(title);

        builder.setMessage(msg);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        // Create the AlertDialog object and return it
        builder.create();

        builder.show();
    }

    public static void showAlertBox(Activity context, String title, String msg, boolean isCancalable, String positiveText, DialogInterface.OnClickListener positiveListener, String negativeText, DialogInterface.OnClickListener negativeListener) {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null)
            builder.setTitle(title);

        builder.setMessage(msg);

        if (isCancalable) {
            builder.setCancelable(isCancalable);
        }

        if (!TextUtils.isEmpty(positiveText)) {
            builder.setPositiveButton(positiveText, positiveListener);
        }

        if (!TextUtils.isEmpty(negativeText)) {
            builder.setPositiveButton(negativeText, negativeListener);
        }

        // Create the AlertDialog object and return it
        builder.create();

        builder.show();
    }

}
