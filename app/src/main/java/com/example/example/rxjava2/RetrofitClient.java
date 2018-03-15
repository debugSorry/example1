package com.example.example.rxjava2;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/11$ 10:58$
 * <p/>
 */
public class RetrofitClient {
    public static String baseUrl="http://v.juhe.cn/weather/";
    public static Retrofit crete(){
        OkHttpClient.Builder builder=new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9,TimeUnit.SECONDS);
        return new Retrofit.Builder().baseUrl(baseUrl)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
