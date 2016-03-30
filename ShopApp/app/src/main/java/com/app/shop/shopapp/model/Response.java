package com.app.shop.shopapp.model;


import com.google.gson.annotations.SerializedName;

public class Response<T> {

    @SerializedName("code")
    public int code;

    @SerializedName("msg")
    public String msg;

    @SerializedName("data")
    public T data;

    public boolean isSuccess() {
        return code == 200;
    }


}
