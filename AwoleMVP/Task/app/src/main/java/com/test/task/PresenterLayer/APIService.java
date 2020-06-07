package com.test.task.PresenterLayer;

import com.test.task.Model.Flash;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIService {


    @GET("api/flash/")
    Call<Flash> getFlashData();

    @FormUrlEncoded
    @POST("api/home/")
    Call<Flash> getHomeData(@FieldMap HashMap<String,String> pageNum);
}
