package com.example.samsung.myapp.domain;

import java.io.Serializable;

public class Order implements Serializable {
    private String msg;
    private String status;
    private String desc;
    private String name;
    private String data;
    private String price;
    private String tg;
    private String phone;
    private String email;
    private String name_author;

    public Order(){}

    public Order(String msg, String name, String desc, String price, String status, String data, String email, String tg, String phone, String name_author) {
        this.msg = msg;
        this.status = status;
        this.desc = desc;
        this.name = name;
        this.data = data;
        this.price = price;
        this.tg = tg;
        this.phone = phone;
        this.email = email;
        this.name_author = name_author;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public String getDesc() {
        return desc;
    }

    public String getEmail() {
        return email;
    }

    public String getMsg() {
        return msg;
    }

    public String getName_author() {
        return name_author;
    }

    public String getPrice() {
        return price;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public String getTg() {
        return tg;
    }

}