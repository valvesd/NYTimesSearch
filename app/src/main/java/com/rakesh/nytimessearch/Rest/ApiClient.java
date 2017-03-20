package com.rakesh.nytimessearch.Rest;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rparuthi on 3/15/2017.
 */

public class ApiClient {

    public static final String BASE_URL = "https://api.nytimes.com/svc/search/v2/";
    private static Retrofit retrofit = null;

    public static  Retrofit getClient(){
        if(retrofit == null){

            OkHttpClient client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

}
