package com.bing.lan.inke.yingke.http;

import com.bing.lan.inke.yingke.Constance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;


public interface GiftAllClient {
    @GET(Constance.GIFT_ALL)
    Call<ResponseBody> getAllDate();
}
