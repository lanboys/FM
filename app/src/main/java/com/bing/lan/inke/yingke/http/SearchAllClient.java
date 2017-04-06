package com.bing.lan.inke.yingke.http;

import com.bing.lan.inke.yingke.Constance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;


public interface SearchAllClient {
    @GET(Constance.SEARCH_ALL)
    Call<ResponseBody> getRecomed();
}
