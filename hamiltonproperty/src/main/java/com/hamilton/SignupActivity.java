package com.hamilton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HP on 07-05-2017.
 */

public class SignupActivity extends AppCompatActivity {


    @BindView(R.id.txt_signup_name)
    EditText txt_signup_name;

    @BindView(R.id.txt_signup_username)
    EditText txt_signup_username;

    @BindView(R.id.txt_signup_phone)
    EditText txt_signup_phone;

    @BindView(R.id.txt_signup_password)
    EditText txt_signup_password;

    @BindView(R.id.lblAlreadyHaveAccount)
    TextView lblAlreadyHaveAccount;

    @OnClick(R.id.btnLoginUser)
    public void LoginButtonClicked() {
        if (TextUtils.isEmpty(txt_signup_username.getText())) {
            //setError(getString(R.string.err_username));
            return;
        } else if (TextUtils.isEmpty(txt_signup_password.getText())) {
            //setError(getString(R.string.err_password));
            return;
        } else {
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    @OnClick(R.id.lblAlreadyHaveAccount)
    public void AlreadyHaveAccount() {
        startActivity(new Intent(this, LoginActivity.class));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        lblAlreadyHaveAccount.setText(Html.fromHtml(getResources().getString(R.string.str_already_have_account)));


    }
}
