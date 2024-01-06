package com.prometheus.egp_tpv.network;

import com.prometheus.egp_tpv.model.Programa;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EGPGloboService {

    private static EGPGloboApi epgGloboApi;
    private static final String BASE_URL = "https://epg-api.video.globo.com";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build();

    public static synchronized EGPGloboApi getInstance() {
        if (epgGloboApi == null) {
            epgGloboApi = retrofit.create(EGPGloboApi.class);
        }
        return epgGloboApi;
    }

    private static OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
