package com.example.drugsearchandtracker.network;

import com.example.drugsearchandtracker.Views.Interface.RxNormRequest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://rxnav.nlm.nih.gov/";
    private static RxNormRequest request;

    public static RxNormRequest getClient() {
        if (request == null) {
            HttpLoggingInterceptor log = new HttpLoggingInterceptor();
            log.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient ok = new OkHttpClient.Builder()
                    .addInterceptor(log)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(ok)
                    .build();

            request = retrofit.create(RxNormRequest.class);
        }
        return request;
    }
}
