package com.bing.lan.inke.yingke.http;

import com.bing.lan.inke.yingke.Constance;
import com.bing.lan.inke.yingke.bean.IndexImage;

import retrofit2.Call;
import retrofit2.http.GET;


public interface IndexClient  {

    @GET(Constance.INDEX_BANNER)
    Call<IndexImage> contributors();

}
