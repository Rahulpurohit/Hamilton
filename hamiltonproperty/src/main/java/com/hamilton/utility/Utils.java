package com.hamilton.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.hamilton.R;
import com.hamilton.application.MyApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.Request;

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

    public static Dialog getLoadingDialog(Activity context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        dialog.setContentView(R.layout.alert_loading);
        ImageView imgLoad = (ImageView) dialog.findViewById(R.id.imgLoading);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotationanim);
        imgLoad.setAnimation(animation);

        dialog.setCancelable(false);
        return dialog;
    }

    public static Boolean isEditTextEmpty(EditText... editTexts) {
        for (EditText editText : editTexts) {
            String editTextValue = editText.getText().toString().trim();
            String hintString = "";
            if (editText.getHint() != null) {
                hintString = editText.getHint().toString();
            }
            if ((editTextValue == null || editTextValue.length() == 0 || (editTextValue.compareTo(hintString) == 0))) {
                return true;
            }
        }
        return false;
    }

    public static Request.Builder addHeaderValues(Request.Builder builder, Context context) {
        String authenticationToken = null;//MyApplication.getApplication().getUser() != null ? MyApplication.getApplication().getUser().getAuthToken() : null;
        if (builder != null) {
           /* builder.addHeader("content-type", "application/x-www-form-urlencoded;charset=utf-8");
            builder.addHeader("cache-control", "no-cache");
*/
            if (!TextUtils.isEmpty(authenticationToken)) {
                builder.addHeader("authorization", authenticationToken);
            }
        }
        return builder;
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
