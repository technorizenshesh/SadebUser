package com.my.sadebuser.act.network.request.service;

import com.google.gson.JsonElement;
import com.my.sadebuser.act.model.servicelist.AllServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Serviecs {
    @GET("get_service")
    Call<JsonElement> getServices(@Query("user_id") String user_id );
}
