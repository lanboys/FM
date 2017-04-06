package com.bing.lan.inke.yingke.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;



public interface ISearchKeyWord {
    @GET
    public Call<ResponseBody> doSearch(@Url String ur);
}
