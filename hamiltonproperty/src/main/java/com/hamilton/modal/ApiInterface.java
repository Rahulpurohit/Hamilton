package com.hamilton.modal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("web/login.php")
    Call<User> getUser(@Field("key") String key, @Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("web/loginallfilters.php")
    Call<SearchFilter> getSearchFilter(@Field("key") String key);

    @FormUrlEncoded
    @POST("web/loginPropertyList.php")
    Call<PropertiesList> getPropertiesList(@Field("key") String key, @Field("userId") int userId);

    @FormUrlEncoded
    @POST("web/loginLikeList.php")
    Call<PropertiesList> getShortlistedPropertiesList(@Field("key") String key, @Field("userId") int userId);

    @FormUrlEncoded
    @POST("web/loginLike.php")
    Call<LikeUnlikeProperty> likeProperty(@Field("key") String key, @Field("userId") int userId, @Field("propertyId") int propertyId);

    @FormUrlEncoded
    @POST("web/loginUnlike.php")
    Call<LikeUnlikeProperty> unlikeProperty(@Field("key") String key, @Field("userId") int userId, @Field("propertyId") int propertyId);

    @FormUrlEncoded
    @POST("web/loginPropertyFilterList.php")
    Call<PropertiesList> getPropertyFilterList(@Field("key") String key
            , @Field("keywords") String keywords
            , @Field("bordering") String bordering
            , @Field("residential") String residential
            , @Field("Commercial") String Commercial
            , @Field("minprice") String minprice
            , @Field("maxprice") String maxprice
            , @Field("bed") String bed
            , @Field("bath") String bath
            , @Field("car") String car
            , @Field("toilets") String toilets
            , @Field("landsize") String landsize
            , @Field("type") String type
    );


}
