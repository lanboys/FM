package com.bing.lan.inke.yingke.http;

import com.bing.lan.inke.yingke.Constance;
import com.bing.lan.inke.yingke.bean.LiveDates;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kay on 16/11/15.
 */
public interface IndexAllClient {
    @GET(Constance.INDEX_LIVEALL_DATE)
    Call<LiveDates> getAllDate();
}
