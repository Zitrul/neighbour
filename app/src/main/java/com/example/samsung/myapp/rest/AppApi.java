package com.example.samsung.myapp.rest;

import android.util.Log;

import com.example.samsung.myapp.domain.Login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppApi {
    @GET("hello/v1")
    Call<Login> getLogin(@Query("name") String name,@Query("password") String password);
    @GET("registration/v1")
    Call<Login> getReg(@Query("login") String login,@Query("password") String password,   @Query("x") Double x,@Query("y") Double y,@Query("email") String email,@Query("phone") String phone,@Query("tg") String tg);
    @GET("addorder/v1")
    Call<Login> getOrder(@Query("id") int id,@Query("img") String img,   @Query("name") String name,@Query("price") String price,@Query("desc") String desc,@Query("data") String data, @Query("status") String status,@Query("author") int author);
}