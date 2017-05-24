package com.hamilton.modal.requestmodal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("key")
    @Expose
    private String key;


    public LoginRequest(String key, String username, String password) {
        this.key = key;
        this.username = username;
        this.password = password;
    }
}
