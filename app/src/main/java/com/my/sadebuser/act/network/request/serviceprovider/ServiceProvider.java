package com.my.sadebuser.act.network.request.serviceprovider;

import com.my.sadebuser.act.model.serviceprovider.ServiceProviderListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceProvider {
    @GET("get_service_by_provider")
    Call<ServiceProviderListResponse> getServiceProviderList();

}
