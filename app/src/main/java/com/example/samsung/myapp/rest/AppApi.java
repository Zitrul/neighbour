package com.example.samsung.myapp.rest;

import android.util.Log;

import com.example.samsung.myapp.domain.Login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppApi {
    @GET("hello/v1")
    Call<Login> getLogin(@Query("name") String name,@Query("password") String password);
}
