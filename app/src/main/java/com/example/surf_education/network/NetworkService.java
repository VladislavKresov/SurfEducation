package com.example.surf_education.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService Instance;
    private static final String BASE_URL = "http://demo3161256.mockable.io/";
    private Retrofit mRetrofit;


    private NetworkService(){
        /////////////////////////////////////////////////////////////////////////
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                                                                            // Все запросы отображаются в ЛОГАХ
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        /////////////////////////////////////////////////////////////////////////


        mRetrofit = new Retrofit.Builder()                              //Строим Ретрофит
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
