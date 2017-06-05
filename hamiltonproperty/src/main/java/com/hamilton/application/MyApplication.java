package com.hamilton.application;

import android.support.multidex.MultiDexApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hamilton.modal.ApiInterface;
import com.hamilton.modal.User;
import com.hamilton.utility.Constants;
import com.hamilton.utility.ShareData;
import com.hamilton.utility.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 24-05-2017.
 */

public class MyApplication extends MultiDexApplication {
    public static final String BASE_URL = "http://hamiltonpropertygroup.com.au/";
    private static MyApplication myApplication;
    public User loginData;
    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest = Utils.addHeaderValues(chain.request().newBuilder(), MyApplication.this).build();
            return chain.proceed(newRequest);
        }
    };

    public static MyApplication getApplication() {
        return myApplication;
    }

    public static void setApplication(MyApplication myApplication) {
        MyApplication.myApplication = myApplication;
    }

    public static int getUserId() {
        try {
            return MyApplication.getApplication().getUser().getData().getUserId();
        } catch (NullPointerException e) {
            return -1;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);
    }

    public ApiInterface getClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        builder.interceptors().add(logging);
        OkHttpClient client = builder.build();

        Retrofit retrofit;

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit.create(ApiInterface.class);
    }

    public User getUser() {
        /*try {
            String userData = null;
            if (loginData == null
                    && (userData = new ShareData(getApplicationContext()).getDataFromSharedPref(Constants.KEY_LOGIN_DATA)) != null) {
                loginData = (User) Serializer.deserialize(userData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return new Gson().fromJson(ShareData.getInstance(getApplicationContext()).getDataFromSharedPref(Constants.KEY_LOGIN_DATA), User.class);
    }

    public void setUser(User loginData) {
        this.loginData = loginData;
        initUser(loginData, true);
    }

    public void initUser(User currentUser, boolean forceWrite) {
        ShareData.getInstance(getApplicationContext()).addToSharedPref(Constants.KEY_LOGIN_DATA, new Gson().toJson(currentUser));
        /*if (new ShareData(getApplicationContext()).getDataFromSharedPref(Constants.KEY_LOGIN_DATA) == null || new ShareData(getApplicationContext()).getDataFromSharedPref(Constants.KEY_LOGIN_DATA).equals("") || forceWrite) {
            new ShareData(getApplicationContext()).clearSharedPref(Constants.KEY_LOGIN_DATA);
            new ShareData(getApplicationContext()).addToSharedPref(Constants.KEY_LOGIN_DATA, Serializer.serialize(currentUser));
        }
        this.loginData = currentUser;*/
    }
}
