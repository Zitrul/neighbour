package com.example.samsung.myapp.rest;

public class LoginApiService {
    private static AppApi helloApi;
    public static AppApi getInstance(){
        if(helloApi == null) return create();
        return helloApi;

    }

    private static AppApi create() {
        return  RetrofitService.getInstance().create(AppApi.class);
    }
}
