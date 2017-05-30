package com.hamilton;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.hamilton.application.MyApplication;
import com.hamilton.modal.User;
import com.hamilton.modal.error.BaseError;
import com.hamilton.utility.Constants;
import com.hamilton.utility.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiResponseActivity extends AppCompatActivity {
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_response);
        getApiData("cogency", "hamiltonpropertygroup_admin");
    }
    
    private void getApiData(String username, String password) {
        mDialog = Utils.getLoadingDialog(ApiResponseActivity.this);

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
                    Toast.makeText(ApiResponseActivity.this, "res==" + user.getResult().getMsg(), Toast.LENGTH_SHORT).show();
                    MyApplication.getApplication().setUser(user);

                } else {
                    final String errorResponse = Utils.convertStreamToString(response.errorBody().byteStream());
                    BaseError.ErrorType errorType = BaseError.ErrorType.fromErrorCode(response.code());
                    BaseError baseError = new BaseError(errorResponse, errorType);
                    Toast.makeText(getApplicationContext(), baseError.getErrorModel().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                if (mDialog.isShowing())
                    mDialog.dismiss();
                Toast.makeText(ApiResponseActivity.this, "error==" + t, Toast.LENGTH_SHORT).show();

                Log.e("User data", "Error");
            }
        });
    }


}
