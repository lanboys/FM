package com.bing.lan.inke.yingke.http;

import com.bing.lan.inke.yingke.Constance;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kay on 16/11/13.
 */
public class ServiceGenerator {

    public  static final String API_BASE_URL = Constance.SERVER_IP;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    public static <HttpBaseRequest> HttpBaseRequest createService(Class<HttpBaseRequest> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
