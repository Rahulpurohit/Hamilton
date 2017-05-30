package com.hamilton.modal;

import com.hamilton.modal.requestmodal.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers("content-type:application/x-www-form-urlencoded")
    @POST("/web/login.php")
    Call<User> getUser(@Header("content-type") String contentType, @Body LoginRequest loginRequest);

    @POST("/web/login.php")
    Call<String> submitAssignment(@Query("key") String key, @Query("username") String username, @Query("password") String password);


}
