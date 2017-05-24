package com.hamilton.modal;
import com.hamilton.modal.requestmodal.LoginRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("/api/v1/user/sign_in")
    Call<User> getUser(@Body LoginRequest loginRequest);

}
