package com.hamilton.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hamilton.HomeActivity;
import com.hamilton.R;
import com.hamilton.WebviewActivity;
import com.hamilton.application.MyApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.Request;

public class Utils {
    private static ConnectivityManager mCM;

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

    /**
     * @param tv
     * @param title
     * @param url   Make Bullet with web link without underline.
     */
    public static void addBulletStyle(final TextView tv, String title, String url) {
        String content = (tv.getContext().getResources().getString(R.string.str_bullet, "<font color='#000000'>" + "<a href=" + url + ">" + title + "</a></font>"));
        Spannable s = (Spannable) Html.fromHtml(content);
        for (URLSpan u : s.getSpans(0, s.length(), URLSpan.class)) {
            s.setSpan(new UnderlineSpan() {
                public void updateDrawState(TextPaint tp) {
                    tp.setUnderlineText(false);
                }
            }, s.getSpanStart(u), s.getSpanEnd(u), 0);
        }
        tv.setLineSpacing(0f, 1.3f);
        tv.append(s);
        tv.append("\n");
        tv.setLinkTextColor(tv.getResources().getColor(R.color.black));
        //tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setMovementMethod(new TextViewClickMovement(new TextViewClickMovement.OnTextViewClickMovementListener() {


            @Override
            public void onLinkClicked(String linkText, String linkUrl, TextViewClickMovement.LinkType linkType) {
                tv.getContext().startActivity(new Intent(tv.getContext(), WebviewActivity.class)
                        .putExtra(Constants.KEY_TITLE, linkText)
                        .putExtra(Constants.KEY_WEB_URL, linkUrl));
            }

            @Override
            public void onLongClick(String text) {

            }
        }, tv.getContext()));


    }

    public static boolean hasInternetAccess(Context context) {
        if (mCM == null) {
            mCM = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo netInfo = mCM.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus()) {
            inputMethodManager.hideSoftInputFromWindow(activity
                    .getCurrentFocus().getWindowToken(), 0);
        }
    }


    public static void performLogout(Activity act) {
        MyApplication.getApplication().setUser(null);
        act.startActivity(new Intent(act, HomeActivity.class));
        act.finish();
    }
}
