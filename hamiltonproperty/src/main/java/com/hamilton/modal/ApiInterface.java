package com.hamilton.modal;

import com.hamilton.modal.requestmodal.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("/web/login.php")
    Call<User> getUser(@Body LoginRequest loginRequest);

    @POST("/web/login.php")
    Call<String> submitAssignment(@Query("key") String key, @Query("username") String username, @Query("password") String password);


}
