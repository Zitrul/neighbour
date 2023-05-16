package com.example.samsung.myapp.domain;

public class UserInfo {
    private String login;
    private String phone;
    private String email;
    private String tg;
    public UserInfo (String login,String tg,String email, String phone){
        this.login = login;
        this.tg = tg;
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPhone() {
        return phone;
    }

    public String getTg() {
        return tg;
    }

}
