package com.my.sadebuser.act.network.request.category;


import com.my.sadebuser.act.model.category.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryRequest {


    @GET("get_category")
    Call<CategoryResponse> getCategory();

}
