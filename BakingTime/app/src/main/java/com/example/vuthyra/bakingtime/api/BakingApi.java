package com.example.vuthyra.bakingtime.api;

import java.util.List;

import com.example.vuthyra.bakingtime.model.ItemPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface BakingApi {

    @Headers("Content-Type: application/json")
    @GET("baking.json")
    Call<List<ItemPojo>> getRecicpes();

}
