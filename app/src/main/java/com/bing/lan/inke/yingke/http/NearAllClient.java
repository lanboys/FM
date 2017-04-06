package com.bing.lan.inke.yingke.http;

import com.bing.lan.inke.yingke.Constance;
import com.bing.lan.inke.yingke.bean.NearDates;

import retrofit2.Call;
import retrofit2.http.GET;


public interface NearAllClient {
    @GET(Constance.NEAR_ALL_DATE)
    Call<NearDates> getAllDate();
}
