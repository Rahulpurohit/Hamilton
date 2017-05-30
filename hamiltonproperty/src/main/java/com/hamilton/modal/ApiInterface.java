package com.hamilton.modal;

import com.hamilton.modal.requestmodal.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("web/login.php")
    Call<User> getUser(@Field("key") String key, @Field("username") String username, @Field("password") String password);

    @POST("web/login.php")
    Call<String> submitAssignment(@Query("key") String key, @Query("username") String username, @Query("password") String password);


}
