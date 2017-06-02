package com.hamilton;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.hamilton.application.MyApplication;
import com.hamilton.modal.LikeUnlikeProperty;
import com.hamilton.modal.PropertiesList;
import com.hamilton.modal.SearchFilter;
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
        getApiDataPropertyLike("1", "493");
    }

    private void getApiDataLogin(String username, String password) {
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

    private void getApiDataSearchFilter() {
        mDialog = Utils.getLoadingDialog(this);

        if (!mDialog.isShowing())
            mDialog.show();

        Log.e("getApiData :- ", "" + "getApiData");
        Call<SearchFilter> userCall = MyApplication.getApplication().getClient().getSearchFilter(Constants.key);
        userCall.enqueue(new Callback<SearchFilter>() {
            @Override
            public void onResponse(Call<SearchFilter> call, Response<SearchFilter> response) {
                Log.e("res body :- ", "" + response.body());
                if (mDialog.isShowing())
                    mDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.body() + "", Toast.LENGTH_SHORT).show();
                } else {
                    final String errorResponse = Utils.convertStreamToString(response.errorBody().byteStream());
                    BaseError.ErrorType errorType = BaseError.ErrorType.fromErrorCode(response.code());
                    BaseError baseError = new BaseError(errorResponse, errorType);
                    Toast.makeText(getApplicationContext(), "" + baseError.getErrorModel().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SearchFilter> call, Throwable t) {

                if (mDialog.isShowing())
                    mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                Log.e("User data", "Error");
            }
        });
    }

    private void getApiDataProperties() {
        mDialog = Utils.getLoadingDialog(this);

        if (!mDialog.isShowing())
            mDialog.show();

        Log.e("getApiData :- ", "" + "getApiData");
        Call<PropertiesList> userCall = MyApplication.getApplication().getClient().getPropertieseList(Constants.key);
        userCall.enqueue(new Callback<PropertiesList>() {
            @Override
            public void onResponse(Call<PropertiesList> call, Response<PropertiesList> response) {
                Log.e("res body :- ", "" + response.body());
                if (mDialog.isShowing())
                    mDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.body() + "", Toast.LENGTH_SHORT).show();
                } else {
                    final String errorResponse = Utils.convertStreamToString(response.errorBody().byteStream());
                    BaseError.ErrorType errorType = BaseError.ErrorType.fromErrorCode(response.code());
                    BaseError baseError = new BaseError(errorResponse, errorType);
                    Toast.makeText(getApplicationContext(), "" + baseError.getErrorModel().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PropertiesList> call, Throwable t) {

                if (mDialog.isShowing())
                    mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                Log.e("User data", "Error");
            }
        });
    }

    private void getApiDataPropertyLike(String userId, String propertyId) {
        mDialog = Utils.getLoadingDialog(this);

        if (!mDialog.isShowing())
            mDialog.show();

        Log.e("getApiData :- ", "" + "getApiData");
        Call<LikeUnlikeProperty> userCall = MyApplication.getApplication().getClient().likeProperty(Constants.key, userId, propertyId);
        userCall.enqueue(new Callback<LikeUnlikeProperty>() {
            @Override
            public void onResponse(Call<LikeUnlikeProperty> call, Response<LikeUnlikeProperty> response) {
                Log.e("res body :- ", "" + response.body());
                if (mDialog.isShowing())
                    mDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.body() + "", Toast.LENGTH_SHORT).show();
                } else {
                    final String errorResponse = Utils.convertStreamToString(response.errorBody().byteStream());
                    BaseError.ErrorType errorType = BaseError.ErrorType.fromErrorCode(response.code());
                    BaseError baseError = new BaseError(errorResponse, errorType);
                    Toast.makeText(getApplicationContext(), "" + baseError.getErrorModel().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LikeUnlikeProperty> call, Throwable t) {

                if (mDialog.isShowing())
                    mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                Log.e("User data", "Error");
            }
        });
    }

    private void getApiDataPropertyUnlike(String userId, String propertyId) {
        mDialog = Utils.getLoadingDialog(this);

        if (!mDialog.isShowing())
            mDialog.show();

        Log.e("getApiData :- ", "" + "getApiData");
        Call<LikeUnlikeProperty> userCall = MyApplication.getApplication().getClient().unlikeProperty(Constants.key, userId, propertyId);
        userCall.enqueue(new Callback<LikeUnlikeProperty>() {
            @Override
            public void onResponse(Call<LikeUnlikeProperty> call, Response<LikeUnlikeProperty> response) {
                Log.e("res body :- ", "" + response.body());
                if (mDialog.isShowing())
                    mDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.body() + "", Toast.LENGTH_SHORT).show();
                } else {
                    final String errorResponse = Utils.convertStreamToString(response.errorBody().byteStream());
                    BaseError.ErrorType errorType = BaseError.ErrorType.fromErrorCode(response.code());
                    BaseError baseError = new BaseError(errorResponse, errorType);
                    Toast.makeText(getApplicationContext(), "" + baseError.getErrorModel().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LikeUnlikeProperty> call, Throwable t) {

                if (mDialog.isShowing())
                    mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                Log.e("User data", "Error");
            }
        });
    }


}
