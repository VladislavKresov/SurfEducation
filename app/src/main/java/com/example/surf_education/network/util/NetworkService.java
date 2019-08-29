package com.example.surf_education.network.util;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService Instance;
    private static final String BASE_URL = "https://demo3161256.mockable.io/";
    private Retrofit mRetrofit;


    private NetworkService(){
        /*
         * отображение всех запросов в логах
         */
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);


        /*
         * Строим Ретрофит
         */
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance(){
        if(Instance == null){
            Instance = new NetworkService();
        }
        return Instance;
    }

    public JSONPlaceHolderApi getJSONApi(){ //Предоставление реализации интерфейса JSONPlaceHolderApi
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }

}
