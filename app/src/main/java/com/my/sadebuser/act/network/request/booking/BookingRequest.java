package com.my.sadebuser.act.network.request.booking;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookingRequest {
    @GET("booking_appointment")
    Call<JsonElement> booking(@Query("user_id") String user_id,
                              @Query("service_id") String service_id,
                              @Query("date") String date,
                              @Query("start_time") String start_time,
                              @Query("end_time") String end_time,
                              @Query("email") String email,
                              @Query("mobile") String mobile,
                              @Query("lat") String lat,
                              @Query("lon") String longitude

                              );
}
