package com.hamilton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.hamilton.utility.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.txt_signup_password)
    EditText txt_signup_password;

    @BindView(R.id.txt_signup_confirm_password)
    EditText txt_signup_confirm_password;

    @OnClick(R.id.btnLoginUser)
    public void LoginButtonClicked() {
        startActivity(new Intent(this, ApiResponseActivity.class));
        String email = getIntent().getStringExtra(Constants.KEY_USERNAME);
        /*if (TextUtils.isEmpty(txt_signup_password.getText())) {
            //setError(getString(R.string.err_password));
            Utils.showErrorBox(this, getResources().getString(R.string.error), getResources().getString(R.string.err_password));

            return;
        } else if (txt_signup_password.getText().toString().equals(txt_signup_confirm_password.getText().toString())) {
            //setError(getString(R.string.err_password));
            Utils.showErrorBox(this, getResources().getString(R.string.error), getResources().getString(R.string.err_password_confirm));

            return;
        } else {
            startActivity(new Intent(this, HomeActivity.class));
        }*/
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
    }
}
