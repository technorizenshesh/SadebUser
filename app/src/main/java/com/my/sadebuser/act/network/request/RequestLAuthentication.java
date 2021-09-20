package com.my.sadebuser.act.network.request;

import com.google.gson.JsonElement;
import com.my.sadebuser.act.model.ChangePasswordResponse;
import com.my.sadebuser.act.model.ResponseAuthentication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestLAuthentication {

    @GET("login")
    Call<JsonElement> getlogIn(@Query("email") String email,
                               @Query("password") String password,
                               @Query("type") String type
    );

    @FormUrlEncoded
    @POST("signup")
    Call<JsonElement> getSignUp(@Field("user_name") String user_name,
                                @Field("password") String password,
                                @Field("email") String email,
                                @Field("type") String type,
                                @Field("mobile") String mobile,
                                @Field("description") String description,
                                @Field("register_id") String register_id);


    @POST("forgot_password")
    @FormUrlEncoded
    Call<JsonElement> getForgetPass(@Field("email") String email);


    @FormUrlEncoded
    @POST("update_profile")
    Call<ResponseAuthentication> getProfileUpdate(@Field("user_name") String user_name,
                                                  @Field("email") String email,
                                                  @Field("mobile") String mobile,
                                                  @Field("user_id") String id,
                                                  @Field("gender") String gender

    );



    @FormUrlEncoded
    @POST("change_password")
    Call<JsonElement> changePassword(@Field("user_id") String id,
                                                @Field("old_password") String old_pass,
                                                @Field("new_password") String new_password
    );


}
