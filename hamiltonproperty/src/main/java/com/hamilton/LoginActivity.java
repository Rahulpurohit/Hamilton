package com.hamilton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txt_signup_username)
    EditText txt_signup_username;

    @BindView(R.id.txt_signup_password)
    EditText txt_signup_password;

    @BindView(R.id.lblForgotPassword)
    TextView lblForgotPassword;

    @BindView(R.id.lblAlreadyHaveAccount)
    TextView lblAlreadyHaveAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        lblForgotPassword.setText(Html.fromHtml(getResources().getString(R.string.str_forgot_password)));
        lblAlreadyHaveAccount.setText(Html.fromHtml(getResources().getString(R.string.str_dont_have_account)));


    }
}
