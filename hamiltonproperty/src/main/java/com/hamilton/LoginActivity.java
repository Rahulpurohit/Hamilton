package com.hamilton;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hamilton.application.MyApplication;
import com.hamilton.modal.User;
import com.hamilton.modal.error.BaseError;
import com.hamilton.utility.Constants;
import com.hamilton.utility.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN";
    Dialog mDialog;
    @BindView(R.id.txt_signup_username)
    EditText txt_signup_username;

    @BindView(R.id.txt_signup_password)
    EditText txt_signup_password;

    @BindView(R.id.lblForgotPassword)
    TextView lblForgotPassword;

    @BindView(R.id.lblAlreadyHaveAccount)
    TextView lblAlreadyHaveAccount;

    @OnClick(R.id.btnLoginUser)
    public void loginButtonClicked() {
        if (TextUtils.isEmpty(txt_signup_username.getText())) {
            Utils.showErrorBox(this, getResources().getString(R.string.error), getResources().getString(R.string.err_username));
            //setError(getString(R.string.err_username));
            return;
        } else if (TextUtils.isEmpty(txt_signup_password.getText())) {
            //setError(getString(R.string.err_password));
            Utils.showErrorBox(this, getResources().getString(R.string.error), getResources().getString(R.string.err_password));

            return;
        } else {
            getApiData(txt_signup_username.getText().toString(), txt_signup_password.getText().toString());

        }
    }

    @OnClick(R.id.lblAlreadyHaveAccount)
    public void AlreadyHaveAccount() {
        startActivity(new Intent(this, SignupActivity.class));

    }

    @OnClick(R.id.lblForgotPassword)
    public void ForgotPassword() {
        if (TextUtils.isEmpty(txt_signup_username.getText())) {
            Toast.makeText(getApplicationContext(), getString(R.string.str_enter_email_first), Toast.LENGTH_LONG).show();
        } else {
            startActivity(new Intent(this, ForgotPasswordActivity.class).putExtra(Constants.KEY_USERNAME, txt_signup_username.getText().toString()));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        lblForgotPassword.setText(Html.fromHtml(getResources().getString(R.string.str_forgot_password)));
        lblAlreadyHaveAccount.setText(Html.fromHtml(getResources().getString(R.string.str_dont_have_account)));
    }

    private void getApiData(String username, String password) {
        mDialog = Utils.getLoadingDialog(this);

        if (!mDialog.isShowing())
            mDialog.show();

        Log.e("getApiData :- ", "" + "getApiData");
        Call<User> userCall = MyApplication.getApplication().getClient().getUser(Constants.key, username, password);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("res body :- ", "" + response.body());
                if (mDialog.isShowing())
                    mDialog.dismiss();
                if (response.isSuccessful()) {
                    User user = response.body();
                    MyApplication.getApplication().setUser(user);
                    callHomeScreen();
                } else {
                    final String errorResponse = Utils.convertStreamToString(response.errorBody().byteStream());
                    BaseError.ErrorType errorType = BaseError.ErrorType.fromErrorCode(response.code());
                    BaseError baseError = new BaseError(errorResponse, errorType);
                    Toast.makeText(getApplicationContext(), "" + baseError.getErrorModel().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                if (mDialog.isShowing())
                    mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                Log.e("User data", "Error");
            }
        });
    }

    private void callHomeScreen() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra(Constants.KEY_PAGER_LOC, getIntent().getIntExtra("fromtab", 0));
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed() called");
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

        if (MyApplication.getUserId() != -1)
            intent.putExtra(Constants.KEY_PAGER_LOC, 3);


        startActivity(intent);
        finish();


    }
}
