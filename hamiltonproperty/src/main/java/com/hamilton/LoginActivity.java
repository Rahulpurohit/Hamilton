package com.hamilton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.hamilton.utility.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txt_signup_username)
    EditText txt_signup_username;

    @BindView(R.id.txt_signup_password)
    EditText txt_signup_password;

    @BindView(R.id.lblForgotPassword)
    TextView lblForgotPassword;

    @BindView(R.id.lblAlreadyHaveAccount)
    TextView lblAlreadyHaveAccount;

    @OnClick(R.id.btnLoginUser)
    public void LoginButtonClicked() {
        if (TextUtils.isEmpty(txt_signup_username.getText())) {
            Utils.showErrorBox(this, getResources().getString(R.string.error), getResources().getString(R.string.err_username));
            //setError(getString(R.string.err_username));
            return;
        } else if (TextUtils.isEmpty(txt_signup_password.getText())) {
            //setError(getString(R.string.err_password));
            Utils.showErrorBox(this, getResources().getString(R.string.error), getResources().getString(R.string.err_password));

            return;
        } else {
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    @OnClick(R.id.lblAlreadyHaveAccount)
    public void AlreadyHaveAccount() {
        startActivity(new Intent(this, SignupActivity.class));

    }

    @OnClick(R.id.lblForgotPassword)
    public void ForgotPassword() {
        startActivity(new Intent(this, ForgotPasswordActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        lblForgotPassword.setText(Html.fromHtml(getResources().getString(R.string.str_forgot_password)));
        lblAlreadyHaveAccount.setText(Html.fromHtml(getResources().getString(R.string.str_dont_have_account)));


    }
}
