package com.xolo.weipulashi.utils.http;

import com.xolo.weipulashi.configuration.ApiService;
import com.xolo.weipulashi.configuration.UrlConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/1/4.
 */
public class RetrofitManager {

    private static OkHttpClient okHttpClient;

    private static Retrofit mRetrofit;

    private static ApiService apiService;

    public static ApiService getApiInstant() {
        if (mRetrofit == null){
            synchronized (RetrofitManager.class){
                if (okHttpClient == null){
                    new RetrofitManager();
                }
                mRetrofit = creatRetrofit(UrlConfig.BASE_URL);
                apiService =  mRetrofit.create(ApiService.class);
            }
        }
        return apiService;
    }

    private RetrofitManager() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    private static  Retrofit  creatRetrofit(String baseUrl){
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        return builder.build();
    }

}
