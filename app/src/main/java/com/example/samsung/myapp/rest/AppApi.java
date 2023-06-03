package com.example.samsung.myapp.rest;

import android.util.Log;

import com.example.samsung.myapp.domain.Login;
import com.example.samsung.myapp.domain.Order;
import com.example.samsung.myapp.domain.Spot;
import com.example.samsung.myapp.domain.UserInfo;

import java.util.ArrayList;
import java.util.List;

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

    @GET("getorder/v1")
    Call<List<Order>> getOrderwith();

    @GET("getlastorderid/v1")
    Call<Login> getlastOrder();
    @GET("getpoints/v1")
    Call<List<Spot>> getPoint();
    @GET("getuserinfo/v1")
    Call<UserInfo> geUserInfo(@Query("id") int id);

    @GET("market/v1")
    Call<List<Order>> getAllmarket(@Query("id") int id);
    @GET("imadethiswork/v1")
    Call<List<Order>> getMyWorks(@Query("id") int id);

    @GET("getcardadd/v1")
    Call<Login> getaddcard(@Query("author_id") int author_id,@Query("id") int id,@Query("my_id") int my_id,@Query("status") String status);
    @GET("checkme/v1")
    Call<List<UserInfo>> checkmepls(@Query("id") int id);

}
