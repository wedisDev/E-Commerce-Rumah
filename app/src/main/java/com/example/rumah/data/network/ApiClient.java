package com.example.rumah.data.network;

import com.example.rumah.data.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofitJson = null;

    public static Retrofit getClient(){
        if (retrofitJson ==null) {
            HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
            logger.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logger)
                    .build();
            retrofitJson = new Retrofit.Builder()
                    .baseUrl(Constant.baseURL)
//                    .client(new OkHttpClient())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitJson;
    }

}
