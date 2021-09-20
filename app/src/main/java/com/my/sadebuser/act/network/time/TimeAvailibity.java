package com.my.sadebuser.act.network.time;

import com.my.sadebuser.act.model.timeavailibity.TimeAvailibityResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TimeAvailibity {

    @GET("time_slote")
    Call<TimeAvailibityResponse> getAllTimeSlot (@Query("service_id") String service_id,
                                                 @Query("date") String date);
}
