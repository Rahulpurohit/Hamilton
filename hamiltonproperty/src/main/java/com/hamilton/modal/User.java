package com.hamilton.modal;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*{
        "result": {
        "status": "true",
        "data": {
        "ValidUser": "yes",
        "userId": 1,
        "user_email": "jaiagarwal28@gmail.com",
        "user_registered": "2016-12-27 04:29:40",
        "display_name": "cogency"
        },
        "msg": "Login Successfully"
        }
        }*/

public class User implements Serializable{
    @SerializedName("result")
    @Expose
    public Result result;

    public class Result {

        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("data")
        @Expose
        public Data data;
        @SerializedName("msg")
        @Expose
        public String msg;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public class Data {

            @SerializedName("ValidUser")
            @Expose
            public String validUser;
            @SerializedName("userId")
            @Expose
            public int userId;
            @SerializedName("user_email")
            @Expose
            public String userEmail;
            @SerializedName("user_registered")
            @Expose
            public String userRegistered;
            @SerializedName("display_name")
            @Expose
            public String displayName;

            public String getValidUser() {
                return validUser;
            }

            public void setValidUser(String validUser) {
                this.validUser = validUser;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserEmail() {
                return userEmail;
            }

            public void setUserEmail(String userEmail) {
                this.userEmail = userEmail;
            }

            public String getUserRegistered() {
                return userRegistered;
            }

            public void setUserRegistered(String userRegistered) {
                this.userRegistered = userRegistered;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }
        }

    }


}
